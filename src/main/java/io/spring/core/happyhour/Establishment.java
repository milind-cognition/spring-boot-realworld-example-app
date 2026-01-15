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
public class Establishment {
  private String id;
  private String name;
  private String type;
  private String address;
  private String phone;
  private String website;
  private String instagram;
  private String facebook;
  private String description;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private DateTime lastVerifiedAt;
  private DateTime createdAt;
  private DateTime updatedAt;

  public Establishment(
      String name,
      String type,
      String address,
      String phone,
      String website,
      String instagram,
      String facebook,
      String description,
      BigDecimal latitude,
      BigDecimal longitude) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.type = type;
    this.address = address;
    this.phone = phone;
    this.website = website;
    this.instagram = instagram;
    this.facebook = facebook;
    this.description = description;
    this.latitude = latitude;
    this.longitude = longitude;
    this.createdAt = new DateTime();
    this.updatedAt = new DateTime();
  }

  public void updateLastVerified() {
    this.lastVerifiedAt = new DateTime();
    this.updatedAt = new DateTime();
  }
}
