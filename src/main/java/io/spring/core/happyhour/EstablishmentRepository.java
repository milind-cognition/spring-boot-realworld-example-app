package io.spring.core.happyhour;

import java.util.List;
import java.util.Optional;

public interface EstablishmentRepository {
  void save(Establishment establishment);

  Optional<Establishment> findById(String id);

  Optional<Establishment> findByName(String name);

  List<Establishment> findAll();

  List<Establishment> findByType(String type);

  List<Establishment> findByNameContaining(String namePart);
}
