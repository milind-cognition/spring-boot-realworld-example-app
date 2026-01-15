package io.spring.infrastructure.repository;

import io.spring.core.happyhour.Special;
import io.spring.core.happyhour.SpecialRepository;
import io.spring.infrastructure.mybatis.mapper.SpecialMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyBatisSpecialRepository implements SpecialRepository {
  private final SpecialMapper specialMapper;

  public MyBatisSpecialRepository(SpecialMapper specialMapper) {
    this.specialMapper = specialMapper;
  }

  @Override
  @Transactional
  public void save(Special special) {
    if (specialMapper.findById(special.getId()) == null) {
      specialMapper.insert(special);
    } else {
      specialMapper.update(special);
    }
  }

  @Override
  public Optional<Special> findById(String id) {
    return Optional.ofNullable(specialMapper.findById(id));
  }

  @Override
  public List<Special> findByHappyHourId(String happyHourId) {
    return specialMapper.findByHappyHourId(happyHourId);
  }

  @Override
  public List<Special> findByItemType(String itemType) {
    return specialMapper.findByItemType(itemType);
  }
}
