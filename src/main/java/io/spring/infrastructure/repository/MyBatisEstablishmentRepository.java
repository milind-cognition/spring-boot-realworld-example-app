package io.spring.infrastructure.repository;

import io.spring.core.happyhour.Establishment;
import io.spring.core.happyhour.EstablishmentRepository;
import io.spring.infrastructure.mybatis.mapper.EstablishmentMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyBatisEstablishmentRepository implements EstablishmentRepository {
  private final EstablishmentMapper establishmentMapper;

  public MyBatisEstablishmentRepository(EstablishmentMapper establishmentMapper) {
    this.establishmentMapper = establishmentMapper;
  }

  @Override
  @Transactional
  public void save(Establishment establishment) {
    if (establishmentMapper.findById(establishment.getId()) == null) {
      establishmentMapper.insert(establishment);
    } else {
      establishmentMapper.update(establishment);
    }
  }

  @Override
  public Optional<Establishment> findById(String id) {
    return Optional.ofNullable(establishmentMapper.findById(id));
  }

  @Override
  public Optional<Establishment> findByName(String name) {
    return Optional.ofNullable(establishmentMapper.findByName(name));
  }

  @Override
  public List<Establishment> findAll() {
    return establishmentMapper.findAll();
  }

  @Override
  public List<Establishment> findByType(String type) {
    return establishmentMapper.findByType(type);
  }

  @Override
  public List<Establishment> findByNameContaining(String namePart) {
    return establishmentMapper.findByNameContaining(namePart);
  }
}
