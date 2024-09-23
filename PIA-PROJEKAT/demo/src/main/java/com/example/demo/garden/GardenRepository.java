package com.example.demo.garden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GardenRepository extends JpaRepository<GardenEntity, Integer> {

    @Query("select g from GardenEntity g where g.id = ?1")
    GardenEntity getGardenById(@Param("gardenId") Integer gardenId);

    @Query("select g from GardenEntity g where g.owner.userId = ?1")
    List<GardenEntity> getGardensForOwner(@Param("ownerId") Integer ownerId);
}
