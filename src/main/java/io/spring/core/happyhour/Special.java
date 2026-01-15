package io.spring.core.happyhour;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Special {
  private String id;
  private String happyHourId;
  private String description;
  private String itemName;
  private String itemType;
  private BigDecimal happyHourPrice;
  private BigDecimal originalPrice;
  private String discountInfo;
  private DateTime lastVerifiedAt;
  private DateTime createdAt;
  private DateTime updatedAt;

  public Special(
      String happyHourId,
      String description,
      String itemName,
      String itemType,
      BigDecimal happyHourPrice,
      BigDecimal originalPrice,
      String discountInfo) {
    this.id = UUID.randomUUID().toString();
    this.happyHourId = happyHourId;
    this.description = description;
    this.itemName = itemName;
    this.itemType = itemType;
    this.happyHourPrice = happyHourPrice;
    this.originalPrice = originalPrice;
    this.discountInfo = discountInfo;
    this.createdAt = new DateTime();
    this.updatedAt = new DateTime();
  }

  public void updateLastVerified() {
    this.lastVerifiedAt = new DateTime();
    this.updatedAt = new DateTime();
  }

  public String getFormattedPrice() {
    if (happyHourPrice != null && originalPrice != null) {
      return String.format("$%.2f (normally $%.2f)", happyHourPrice, originalPrice);
    } else if (happyHourPrice != null) {
      return String.format("$%.2f", happyHourPrice);
    } else if (discountInfo != null) {
      return discountInfo;
    }
    return "";
  }
}
