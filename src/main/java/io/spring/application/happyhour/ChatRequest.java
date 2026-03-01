package io.spring.application.happyhour;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("chat")
public class ChatRequest {
  private String message;
  private String sessionId;
}
