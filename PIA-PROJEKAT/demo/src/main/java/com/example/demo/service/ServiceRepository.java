package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    @Query("select s from ServiceEntity s where s.firm.firmId = ?1")
    List<ServiceEntity> getAllServiceForFirm(@Param("firmId") Long firmId);
}
