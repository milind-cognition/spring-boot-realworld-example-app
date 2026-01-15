package io.spring.infrastructure.mybatis.mapper;

import io.spring.core.happyhour.Special;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpecialMapper {
  void insert(@Param("special") Special special);

  void update(@Param("special") Special special);

  Special findById(@Param("id") String id);

  List<Special> findByHappyHourId(@Param("happyHourId") String happyHourId);

  List<Special> findByItemType(@Param("itemType") String itemType);
}
