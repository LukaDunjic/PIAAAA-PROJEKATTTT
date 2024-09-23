package com.example.demo.firm.repository;

import com.example.demo.firm.model.FirmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FirmRepository extends JpaRepository<FirmEntity, Integer> {

    @Query("select f from FirmEntity f")
    List<FirmEntity> getAllFirms();

    @Query("select f from FirmEntity f where f.firmId = ?1")
    FirmEntity getFirmById(@Param("firmId") Long firmId);

    @Query("select f from FirmEntity f where f.name = ?1")
    FirmEntity getFirmByName(@Param("name") String name);

    @Query("select f from FirmEntity f where f.address = ?1")
    List<FirmEntity> getFirmByAddress(@Param("address") String address);

    @Query("select dg.firm from DecoratorEntity dg where dg.user.userId = ?1")
    FirmEntity getUserByFirm(Integer userId);
}
