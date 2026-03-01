package io.spring.application.happyhour;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialData {
  private String id;
  private String description;
  private String itemName;
  private String itemType;
  private BigDecimal happyHourPrice;
  private BigDecimal originalPrice;
  private String discountInfo;
  private String formattedPrice;
}
