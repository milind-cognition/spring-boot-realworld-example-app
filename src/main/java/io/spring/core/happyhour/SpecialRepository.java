package io.spring.core.happyhour;

import java.util.List;
import java.util.Optional;

public interface SpecialRepository {
  void save(Special special);

  Optional<Special> findById(String id);

  List<Special> findByHappyHourId(String happyHourId);

  List<Special> findByItemType(String itemType);
}
