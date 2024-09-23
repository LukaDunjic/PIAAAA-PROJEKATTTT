package com.example.demo.owner;

import com.example.demo.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

    @Query("select count(*) from OwnerEntity ")
    Integer countAllOwners();

    @Query("select distinct u from UserEntity u where u.userType = 'vlasnik'")
    List<UserEntity> getAllOwners();
}
