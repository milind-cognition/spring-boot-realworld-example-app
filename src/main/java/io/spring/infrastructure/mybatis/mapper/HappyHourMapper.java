package io.spring.infrastructure.mybatis.mapper;

import io.spring.core.happyhour.HappyHour;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HappyHourMapper {
  void insert(@Param("happyHour") HappyHour happyHour);

  void update(@Param("happyHour") HappyHour happyHour);

  HappyHour findById(@Param("id") String id);

  List<HappyHour> findByEstablishmentId(@Param("establishmentId") String establishmentId);

  List<HappyHour> findByDayOfWeek(@Param("dayOfWeek") int dayOfWeek);

  List<HappyHour> findActiveNow(
      @Param("dayOfWeek") int dayOfWeek, @Param("currentTime") String currentTime);
}
