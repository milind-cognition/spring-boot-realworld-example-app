package io.spring.application.happyhour;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentData {
  private String id;
  private String name;
  private String type;
  private String address;
  private String phone;
  private String website;
  private String instagram;
  private String facebook;
  private String description;
  private String lastVerifiedAt;
  private List<HappyHourData> happyHours;
}
