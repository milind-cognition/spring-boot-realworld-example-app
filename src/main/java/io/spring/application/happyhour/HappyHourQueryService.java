package io.spring.application.happyhour;

import io.spring.core.happyhour.Establishment;
import io.spring.core.happyhour.EstablishmentRepository;
import io.spring.core.happyhour.HappyHour;
import io.spring.core.happyhour.HappyHourRepository;
import io.spring.core.happyhour.Special;
import io.spring.core.happyhour.SpecialRepository;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HappyHourQueryService {

  private static final List<String> DAY_NAMES =
      Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

  private final EstablishmentRepository establishmentRepository;
  private final HappyHourRepository happyHourRepository;
  private final SpecialRepository specialRepository;
  private final QueryParser queryParser;

  public ChatResponse processQuery(String userMessage) {
    QueryParser.QueryIntent intent = queryParser.parseQuery(userMessage);

    switch (intent.getType()) {
      case CURRENT_HAPPY_HOURS:
        return getCurrentHappyHours();
      case HAPPY_HOURS_BY_DAY:
        return getHappyHoursByDay(intent.getDayOfWeek(), intent.getEstablishmentType());
      case ESTABLISHMENT_SPECIALS:
        return getEstablishmentSpecials(intent.getEstablishmentName());
      case ESTABLISHMENTS_BY_TYPE:
        return getEstablishmentsByType(intent.getEstablishmentType());
      case LIST_ALL:
        return listAllEstablishments();
      case DRINK_SPECIALS:
        return getSpecialsByType("drink");
      case FOOD_SPECIALS:
        return getSpecialsByType("food");
      default:
        return getDefaultResponse();
    }
  }

  private ChatResponse getCurrentHappyHours() {
    LocalDateTime now = LocalDateTime.now();
    int dayOfWeek = now.getDayOfWeek().getValue() % 7;
    String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));

    List<HappyHour> activeHappyHours = happyHourRepository.findActiveNow(dayOfWeek, currentTime);

    if (activeHappyHours.isEmpty()) {
      return new ChatResponse(
          "There are no happy hours happening right now in Belmont Shore, Naples, or 2nd & PCH. "
              + "Try asking about happy hours on a specific day!",
          new ArrayList<>(),
          "CURRENT_HAPPY_HOURS");
    }

    List<EstablishmentData> establishments = new ArrayList<>();
    StringBuilder response = new StringBuilder();
    response.append("Here are the places with happy hours right now:\n\n");

    for (HappyHour hh : activeHappyHours) {
      Optional<Establishment> estOpt = establishmentRepository.findById(hh.getEstablishmentId());
      if (estOpt.isPresent()) {
        Establishment est = estOpt.get();
        List<Special> specials = specialRepository.findByHappyHourId(hh.getId());

        EstablishmentData estData = toEstablishmentData(est, Arrays.asList(hh), specials);
        establishments.add(estData);

        response.append("**").append(est.getName()).append("**\n");
        response.append("Happy Hour: ").append(hh.getStartTime()).append(" - ").append(hh.getEndTime()).append("\n");

        if (!specials.isEmpty()) {
          response.append("Specials:\n");
          for (Special special : specials) {
            response.append("  - ").append(special.getDescription());
            if (special.getHappyHourPrice() != null && special.getOriginalPrice() != null) {
              response.append(" ($").append(special.getHappyHourPrice())
                  .append(", normally $").append(special.getOriginalPrice()).append(")");
            }
            response.append("\n");
          }
        }

        if (est.getLastVerifiedAt() != null) {
          response.append("Last verified: ").append(est.getLastVerifiedAt().toString("MMM d, yyyy")).append("\n");
        }
        response.append("\n");
      }
    }

    return new ChatResponse(response.toString(), establishments, "CURRENT_HAPPY_HOURS");
  }

  private ChatResponse getHappyHoursByDay(int dayOfWeek, String establishmentType) {
    String dayName = DAY_NAMES.get(dayOfWeek);
    List<HappyHour> happyHours = happyHourRepository.findByDayOfWeek(dayOfWeek);

    if (happyHours.isEmpty()) {
      return new ChatResponse(
          "No happy hours found on " + dayName + " in Belmont Shore, Naples, or 2nd & PCH.",
          new ArrayList<>(),
          "HAPPY_HOURS_BY_DAY");
    }

    List<EstablishmentData> establishments = new ArrayList<>();
    StringBuilder response = new StringBuilder();
    response.append("Happy hours on ").append(dayName).append(":\n\n");

    for (HappyHour hh : happyHours) {
      Optional<Establishment> estOpt = establishmentRepository.findById(hh.getEstablishmentId());
      if (estOpt.isPresent()) {
        Establishment est = estOpt.get();

        if (establishmentType != null && !est.getType().toLowerCase().contains(establishmentType.toLowerCase())) {
          continue;
        }

        List<Special> specials = specialRepository.findByHappyHourId(hh.getId());
        EstablishmentData estData = toEstablishmentData(est, Arrays.asList(hh), specials);
        establishments.add(estData);

        response.append("**").append(est.getName()).append("** (").append(est.getType()).append(")\n");
        response.append("Time: ").append(hh.getStartTime()).append(" - ").append(hh.getEndTime()).append("\n");

        if (!specials.isEmpty()) {
          for (Special special : specials) {
            response.append("  - ").append(special.getDescription());
            if (special.getHappyHourPrice() != null && special.getOriginalPrice() != null) {
              response.append(" ($").append(special.getHappyHourPrice())
                  .append(", normally $").append(special.getOriginalPrice()).append(")");
            }
            response.append("\n");
          }
        }
        response.append("\n");
      }
    }

    if (establishments.isEmpty()) {
      String typeMsg = establishmentType != null ? establishmentType + "s" : "places";
      return new ChatResponse(
          "No " + typeMsg + " with happy hours found on " + dayName + ".",
          new ArrayList<>(),
          "HAPPY_HOURS_BY_DAY");
    }

    return new ChatResponse(response.toString(), establishments, "HAPPY_HOURS_BY_DAY");
  }

  private ChatResponse getEstablishmentSpecials(String establishmentName) {
    List<Establishment> matches = establishmentRepository.findByNameContaining(establishmentName);

    if (matches.isEmpty()) {
      return new ChatResponse(
          "I couldn't find an establishment named \"" + establishmentName + "\" in Belmont Shore, Naples, or 2nd & PCH. "
              + "Try asking \"list all places\" to see available establishments.",
          new ArrayList<>(),
          "ESTABLISHMENT_SPECIALS");
    }

    List<EstablishmentData> establishments = new ArrayList<>();
    StringBuilder response = new StringBuilder();

    for (Establishment est : matches) {
      List<HappyHour> happyHours = happyHourRepository.findByEstablishmentId(est.getId());

      if (happyHours.isEmpty()) {
        response.append("**").append(est.getName()).append("** doesn't appear to have happy hour specials listed.\n\n");
        continue;
      }

      List<Special> allSpecials = new ArrayList<>();
      for (HappyHour hh : happyHours) {
        allSpecials.addAll(specialRepository.findByHappyHourId(hh.getId()));
      }

      EstablishmentData estData = toEstablishmentData(est, happyHours, allSpecials);
      establishments.add(estData);

      response.append("**").append(est.getName()).append("**\n");
      response.append("Address: ").append(est.getAddress()).append("\n");

      if (est.getPhone() != null) {
        response.append("Phone: ").append(est.getPhone()).append("\n");
      }

      response.append("\nHappy Hour Schedule:\n");
      for (HappyHour hh : happyHours) {
        response.append("  ").append(DAY_NAMES.get(hh.getDayOfWeek())).append(": ")
            .append(hh.getStartTime()).append(" - ").append(hh.getEndTime()).append("\n");
      }

      if (!allSpecials.isEmpty()) {
        response.append("\nSpecials:\n");
        for (Special special : allSpecials) {
          response.append("  - ").append(special.getDescription());
          if (special.getHappyHourPrice() != null && special.getOriginalPrice() != null) {
            response.append(" ($").append(special.getHappyHourPrice())
                .append(", normally $").append(special.getOriginalPrice()).append(")");
          }
          response.append("\n");
        }
      }

      if (est.getLastVerifiedAt() != null) {
        response.append("\nLast verified: ").append(est.getLastVerifiedAt().toString("MMM d, yyyy")).append("\n");
      }
      response.append("\n");
    }

    return new ChatResponse(response.toString(), establishments, "ESTABLISHMENT_SPECIALS");
  }

  private ChatResponse getEstablishmentsByType(String type) {
    List<Establishment> establishments = establishmentRepository.findByType(type);

    if (establishments.isEmpty()) {
      return new ChatResponse(
          "No " + type + "s found in Belmont Shore, Naples, or 2nd & PCH.",
          new ArrayList<>(),
          "ESTABLISHMENTS_BY_TYPE");
    }

    StringBuilder response = new StringBuilder();
    response.append("Here are the ").append(type).append("s in the area:\n\n");

    List<EstablishmentData> estDataList = new ArrayList<>();
    for (Establishment est : establishments) {
      List<HappyHour> happyHours = happyHourRepository.findByEstablishmentId(est.getId());
      List<Special> specials = new ArrayList<>();
      for (HappyHour hh : happyHours) {
        specials.addAll(specialRepository.findByHappyHourId(hh.getId()));
      }

      estDataList.add(toEstablishmentData(est, happyHours, specials));

      response.append("**").append(est.getName()).append("**\n");
      response.append("Address: ").append(est.getAddress()).append("\n");
      if (!happyHours.isEmpty()) {
        response.append("Has happy hour: Yes\n");
      }
      response.append("\n");
    }

    return new ChatResponse(response.toString(), estDataList, "ESTABLISHMENTS_BY_TYPE");
  }

  private ChatResponse listAllEstablishments() {
    List<Establishment> establishments = establishmentRepository.findAll();

    if (establishments.isEmpty()) {
      return new ChatResponse(
          "No establishments found in the database yet. Check back soon!",
          new ArrayList<>(),
          "LIST_ALL");
    }

    StringBuilder response = new StringBuilder();
    response.append("Here are all the bars and restaurants in Belmont Shore, Naples, and 2nd & PCH:\n\n");

    List<EstablishmentData> estDataList = new ArrayList<>();
    for (Establishment est : establishments) {
      List<HappyHour> happyHours = happyHourRepository.findByEstablishmentId(est.getId());
      List<Special> specials = new ArrayList<>();
      for (HappyHour hh : happyHours) {
        specials.addAll(specialRepository.findByHappyHourId(hh.getId()));
      }

      estDataList.add(toEstablishmentData(est, happyHours, specials));

      response.append("- **").append(est.getName()).append("** (").append(est.getType()).append(")");
      if (!happyHours.isEmpty()) {
        response.append(" - Has happy hour");
      }
      response.append("\n");
    }

    response.append("\nAsk about a specific place to see their happy hour details!");

    return new ChatResponse(response.toString(), estDataList, "LIST_ALL");
  }

  private ChatResponse getSpecialsByType(String itemType) {
    List<Special> specials = specialRepository.findByItemType(itemType);

    if (specials.isEmpty()) {
      return new ChatResponse(
          "No " + itemType + " specials found. Try asking about happy hours at a specific place!",
          new ArrayList<>(),
          itemType.toUpperCase() + "_SPECIALS");
    }

    StringBuilder response = new StringBuilder();
    response.append("Here are the ").append(itemType).append(" specials during happy hour:\n\n");

    for (Special special : specials) {
      response.append("- ").append(special.getDescription());
      if (special.getHappyHourPrice() != null && special.getOriginalPrice() != null) {
        response.append(" ($").append(special.getHappyHourPrice())
            .append(", normally $").append(special.getOriginalPrice()).append(")");
      }
      response.append("\n");
    }

    return new ChatResponse(response.toString(), new ArrayList<>(), itemType.toUpperCase() + "_SPECIALS");
  }

  private ChatResponse getDefaultResponse() {
    return new ChatResponse(
        "I can help you find happy hours in Belmont Shore, Naples, and 2nd & PCH! Try asking:\n\n"
            + "- \"What places have happy hours right now?\"\n"
            + "- \"Do any bars have happy hour on Tuesdays?\"\n"
            + "- \"What are the happy hour specials at [restaurant name]?\"\n"
            + "- \"List all places\"\n"
            + "- \"Show me drink specials\"",
        new ArrayList<>(),
        "HELP");
  }

  private EstablishmentData toEstablishmentData(
      Establishment est, List<HappyHour> happyHours, List<Special> specials) {
    List<HappyHourData> hhDataList = new ArrayList<>();

    for (HappyHour hh : happyHours) {
      List<SpecialData> specialDataList =
          specials.stream()
              .filter(s -> s.getHappyHourId().equals(hh.getId()))
              .map(this::toSpecialData)
              .collect(Collectors.toList());

      HappyHourData hhData =
          new HappyHourData(
              hh.getId(),
              hh.getEstablishmentId(),
              est.getName(),
              hh.getDayOfWeek(),
              DAY_NAMES.get(hh.getDayOfWeek()),
              hh.getStartTime(),
              hh.getEndTime(),
              hh.getLastVerifiedAt() != null ? hh.getLastVerifiedAt().toString() : null,
              specialDataList);
      hhDataList.add(hhData);
    }

    return new EstablishmentData(
        est.getId(),
        est.getName(),
        est.getType(),
        est.getAddress(),
        est.getPhone(),
        est.getWebsite(),
        est.getInstagram(),
        est.getFacebook(),
        est.getDescription(),
        est.getLastVerifiedAt() != null ? est.getLastVerifiedAt().toString() : null,
        hhDataList);
  }

  private SpecialData toSpecialData(Special special) {
    String formattedPrice = "";
    if (special.getHappyHourPrice() != null && special.getOriginalPrice() != null) {
      formattedPrice =
          String.format(
              "$%.2f (normally $%.2f)", special.getHappyHourPrice(), special.getOriginalPrice());
    } else if (special.getDiscountInfo() != null) {
      formattedPrice = special.getDiscountInfo();
    }

    return new SpecialData(
        special.getId(),
        special.getDescription(),
        special.getItemName(),
        special.getItemType(),
        special.getHappyHourPrice(),
        special.getOriginalPrice(),
        special.getDiscountInfo(),
        formattedPrice);
  }
}
