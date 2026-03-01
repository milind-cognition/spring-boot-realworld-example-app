package io.spring.infrastructure.mybatis.mapper;

import io.spring.core.happyhour.Establishment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EstablishmentMapper {
  void insert(@Param("establishment") Establishment establishment);

  void update(@Param("establishment") Establishment establishment);

  Establishment findById(@Param("id") String id);

  Establishment findByName(@Param("name") String name);

  List<Establishment> findAll();

  List<Establishment> findByType(@Param("type") String type);

  List<Establishment> findByNameContaining(@Param("namePart") String namePart);
}
