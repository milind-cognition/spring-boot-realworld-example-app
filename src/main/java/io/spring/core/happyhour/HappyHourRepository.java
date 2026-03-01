package io.spring.core.happyhour;

import java.util.List;
import java.util.Optional;

public interface HappyHourRepository {
  void save(HappyHour happyHour);

  Optional<HappyHour> findById(String id);

  List<HappyHour> findByEstablishmentId(String establishmentId);

  List<HappyHour> findByDayOfWeek(int dayOfWeek);

  List<HappyHour> findActiveNow(int dayOfWeek, String currentTime);
}
