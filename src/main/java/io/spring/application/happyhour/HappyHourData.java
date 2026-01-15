package io.spring.application.happyhour;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HappyHourData {
  private String id;
  private String establishmentId;
  private String establishmentName;
  private int dayOfWeek;
  private String dayName;
  private String startTime;
  private String endTime;
  private String lastVerifiedAt;
  private List<SpecialData> specials;
}
