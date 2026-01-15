package io.spring.infrastructure.repository;

import io.spring.core.happyhour.HappyHour;
import io.spring.core.happyhour.HappyHourRepository;
import io.spring.infrastructure.mybatis.mapper.HappyHourMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyBatisHappyHourRepository implements HappyHourRepository {
  private final HappyHourMapper happyHourMapper;

  public MyBatisHappyHourRepository(HappyHourMapper happyHourMapper) {
    this.happyHourMapper = happyHourMapper;
  }

  @Override
  @Transactional
  public void save(HappyHour happyHour) {
    if (happyHourMapper.findById(happyHour.getId()) == null) {
      happyHourMapper.insert(happyHour);
    } else {
      happyHourMapper.update(happyHour);
    }
  }

  @Override
  public Optional<HappyHour> findById(String id) {
    return Optional.ofNullable(happyHourMapper.findById(id));
  }

  @Override
  public List<HappyHour> findByEstablishmentId(String establishmentId) {
    return happyHourMapper.findByEstablishmentId(establishmentId);
  }

  @Override
  public List<HappyHour> findByDayOfWeek(int dayOfWeek) {
    return happyHourMapper.findByDayOfWeek(dayOfWeek);
  }

  @Override
  public List<HappyHour> findActiveNow(int dayOfWeek, String currentTime) {
    return happyHourMapper.findActiveNow(dayOfWeek, currentTime);
  }
}
