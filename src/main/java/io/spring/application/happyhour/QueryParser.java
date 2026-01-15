package io.spring.application.happyhour;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class QueryParser {

  private static final List<String> DAYS_OF_WEEK =
      Arrays.asList("sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday");

  private static final Map<String, Integer> DAY_NAME_TO_NUMBER = new HashMap<>();

  static {
    DAY_NAME_TO_NUMBER.put("sunday", 0);
    DAY_NAME_TO_NUMBER.put("sundays", 0);
    DAY_NAME_TO_NUMBER.put("monday", 1);
    DAY_NAME_TO_NUMBER.put("mondays", 1);
    DAY_NAME_TO_NUMBER.put("tuesday", 2);
    DAY_NAME_TO_NUMBER.put("tuesdays", 2);
    DAY_NAME_TO_NUMBER.put("wednesday", 3);
    DAY_NAME_TO_NUMBER.put("wednesdays", 3);
    DAY_NAME_TO_NUMBER.put("thursday", 4);
    DAY_NAME_TO_NUMBER.put("thursdays", 4);
    DAY_NAME_TO_NUMBER.put("friday", 5);
    DAY_NAME_TO_NUMBER.put("fridays", 5);
    DAY_NAME_TO_NUMBER.put("saturday", 6);
    DAY_NAME_TO_NUMBER.put("saturdays", 6);
  }

  public QueryIntent parseQuery(String query) {
    String lowerQuery = query.toLowerCase().trim();

    if (isCurrentHappyHourQuery(lowerQuery)) {
      return new QueryIntent(QueryType.CURRENT_HAPPY_HOURS, null, null, null);
    }

    Integer dayOfWeek = extractDayOfWeek(lowerQuery);
    if (dayOfWeek != null) {
      String establishmentType = extractEstablishmentType(lowerQuery);
      return new QueryIntent(QueryType.HAPPY_HOURS_BY_DAY, dayOfWeek, null, establishmentType);
    }

    String establishmentName = extractEstablishmentName(lowerQuery);
    if (establishmentName != null) {
      return new QueryIntent(QueryType.ESTABLISHMENT_SPECIALS, null, establishmentName, null);
    }

    String establishmentType = extractEstablishmentType(lowerQuery);
    if (establishmentType != null) {
      return new QueryIntent(QueryType.ESTABLISHMENTS_BY_TYPE, null, null, establishmentType);
    }

    if (isListAllQuery(lowerQuery)) {
      return new QueryIntent(QueryType.LIST_ALL, null, null, null);
    }

    if (isDrinkSpecialsQuery(lowerQuery)) {
      return new QueryIntent(QueryType.DRINK_SPECIALS, null, null, null);
    }

    if (isFoodSpecialsQuery(lowerQuery)) {
      return new QueryIntent(QueryType.FOOD_SPECIALS, null, null, null);
    }

    return new QueryIntent(QueryType.UNKNOWN, null, null, null);
  }

  private boolean isCurrentHappyHourQuery(String query) {
    return query.contains("right now")
        || query.contains("currently")
        || query.contains("happening now")
        || query.contains("going on now")
        || query.contains("active now")
        || (query.contains("happy hour") && query.contains("now"))
        || query.matches(".*what.*happy hour.*now.*")
        || query.matches(".*where.*happy hour.*now.*")
        || query.matches(".*which.*happy hour.*now.*");
  }

  private Integer extractDayOfWeek(String query) {
    for (Map.Entry<String, Integer> entry : DAY_NAME_TO_NUMBER.entrySet()) {
      if (query.contains(entry.getKey())) {
        return entry.getValue();
      }
    }

    if (query.contains("today")) {
      return null;
    }

    if (query.contains("tomorrow")) {
      return null;
    }

    return null;
  }

  private String extractEstablishmentName(String query) {
    Pattern atPattern = Pattern.compile("(?:at|for|about)\\s+([\\w\\s'&]+?)(?:\\?|$|\\.|,)");
    Matcher matcher = atPattern.matcher(query);
    if (matcher.find()) {
      String name = matcher.group(1).trim();
      if (!name.isEmpty()
          && !name.equals("happy hour")
          && !name.equals("happy hours")
          && !name.equals("bars")
          && !name.equals("restaurants")) {
        return name;
      }
    }

    Pattern specialsPattern =
        Pattern.compile("(?:specials|deals|menu)\\s+(?:at|for)\\s+([\\w\\s'&]+?)(?:\\?|$|\\.|,)");
    matcher = specialsPattern.matcher(query);
    if (matcher.find()) {
      return matcher.group(1).trim();
    }

    return null;
  }

  private String extractEstablishmentType(String query) {
    if (query.contains("bar") && !query.contains("restaurant")) {
      return "bar";
    }
    if (query.contains("restaurant") && !query.contains("bar")) {
      return "restaurant";
    }
    return null;
  }

  private boolean isListAllQuery(String query) {
    return query.contains("list all")
        || query.contains("show all")
        || query.contains("all places")
        || query.contains("all establishments")
        || query.contains("all bars and restaurants")
        || query.contains("everything");
  }

  private boolean isDrinkSpecialsQuery(String query) {
    return query.contains("drink special")
        || query.contains("drink deal")
        || query.contains("cocktail")
        || query.contains("beer special")
        || query.contains("wine special")
        || query.contains("margarita");
  }

  private boolean isFoodSpecialsQuery(String query) {
    return query.contains("food special")
        || query.contains("food deal")
        || query.contains("appetizer")
        || query.contains("taco")
        || query.contains("wing");
  }

  public enum QueryType {
    CURRENT_HAPPY_HOURS,
    HAPPY_HOURS_BY_DAY,
    ESTABLISHMENT_SPECIALS,
    ESTABLISHMENTS_BY_TYPE,
    LIST_ALL,
    DRINK_SPECIALS,
    FOOD_SPECIALS,
    UNKNOWN
  }

  public static class QueryIntent {
    private final QueryType type;
    private final Integer dayOfWeek;
    private final String establishmentName;
    private final String establishmentType;

    public QueryIntent(
        QueryType type, Integer dayOfWeek, String establishmentName, String establishmentType) {
      this.type = type;
      this.dayOfWeek = dayOfWeek;
      this.establishmentName = establishmentName;
      this.establishmentType = establishmentType;
    }

    public QueryType getType() {
      return type;
    }

    public Integer getDayOfWeek() {
      return dayOfWeek;
    }

    public String getEstablishmentName() {
      return establishmentName;
    }

    public String getEstablishmentType() {
      return establishmentType;
    }
  }
}
