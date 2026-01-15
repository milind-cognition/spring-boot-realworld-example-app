package io.spring.core.happyhour;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class HappyHour {
  private String id;
  private String establishmentId;
  private int dayOfWeek;
  private String startTime;
  private String endTime;
  private DateTime lastVerifiedAt;
  private DateTime createdAt;
  private DateTime updatedAt;

  public HappyHour(String establishmentId, int dayOfWeek, String startTime, String endTime) {
    this.id = UUID.randomUUID().toString();
    this.establishmentId = establishmentId;
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
    this.createdAt = new DateTime();
    this.updatedAt = new DateTime();
  }

  public void updateLastVerified() {
    this.lastVerifiedAt = new DateTime();
    this.updatedAt = new DateTime();
  }

  public boolean isActiveAt(int currentDayOfWeek, String currentTime) {
    if (this.dayOfWeek != currentDayOfWeek) {
      return false;
    }
    return currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) <= 0;
  }
}
