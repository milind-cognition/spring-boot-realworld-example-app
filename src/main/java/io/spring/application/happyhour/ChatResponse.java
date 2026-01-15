package io.spring.application.happyhour;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
  private String response;
  private List<EstablishmentData> establishments;
  private String queryType;
}
