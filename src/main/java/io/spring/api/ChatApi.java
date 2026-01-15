package io.spring.api;

import io.spring.application.happyhour.ChatRequest;
import io.spring.application.happyhour.ChatResponse;
import io.spring.application.happyhour.EstablishmentData;
import io.spring.application.happyhour.HappyHourQueryService;
import io.spring.core.happyhour.Establishment;
import io.spring.core.happyhour.EstablishmentRepository;
import io.spring.core.happyhour.HappyHour;
import io.spring.core.happyhour.HappyHourRepository;
import io.spring.core.happyhour.Special;
import io.spring.core.happyhour.SpecialRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/chat")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ChatApi {

  private final HappyHourQueryService happyHourQueryService;
  private final EstablishmentRepository establishmentRepository;
  private final HappyHourRepository happyHourRepository;
  private final SpecialRepository specialRepository;

  @PostMapping("/message")
  public ResponseEntity<ChatResponse> processMessage(@RequestBody ChatRequest request) {
    ChatResponse response = happyHourQueryService.processQuery(request.getMessage());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/establishments")
  public ResponseEntity<Map<String, Object>> getAllEstablishments() {
    List<Establishment> establishments = establishmentRepository.findAll();
    Map<String, Object> response = new HashMap<>();
    response.put("establishments", establishments);
    response.put("count", establishments.size());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/establishments/{id}")
  public ResponseEntity<Map<String, Object>> getEstablishment(@PathVariable String id) {
    return establishmentRepository
        .findById(id)
        .map(
            establishment -> {
              List<HappyHour> happyHours =
                  happyHourRepository.findByEstablishmentId(establishment.getId());
              Map<String, Object> response = new HashMap<>();
              response.put("establishment", establishment);
              response.put("happyHours", happyHours);
              return ResponseEntity.ok(response);
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/establishments/{id}/specials")
  public ResponseEntity<Map<String, Object>> getEstablishmentSpecials(@PathVariable String id) {
    return establishmentRepository
        .findById(id)
        .map(
            establishment -> {
              List<HappyHour> happyHours =
                  happyHourRepository.findByEstablishmentId(establishment.getId());
              Map<String, Object> response = new HashMap<>();
              response.put("establishment", establishment);
              response.put("happyHours", happyHours);

              for (HappyHour hh : happyHours) {
                List<Special> specials = specialRepository.findByHappyHourId(hh.getId());
                response.put("specials_" + hh.getId(), specials);
              }

              return ResponseEntity.ok(response);
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/health")
  public ResponseEntity<Map<String, String>> healthCheck() {
    Map<String, String> response = new HashMap<>();
    response.put("status", "ok");
    response.put("service", "Happy Hour Chatbot API");
    response.put("area", "Belmont Shore, Naples, 2nd & PCH - Long Beach");
    return ResponseEntity.ok(response);
  }
}
