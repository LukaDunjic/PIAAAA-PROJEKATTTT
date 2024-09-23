package com.example.demo.user.repository;

import com.example.demo.user.model.RequestRegistrationEntity;
import com.example.demo.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestRegistrationRepository extends JpaRepository<RequestRegistrationEntity, Integer> {

    @Query("select r.user from RequestRegistrationEntity r where r.user.userId = ?1")
    UserEntity getRequestByUserId(@Param("userId") Integer userId);

    @Query("select r from RequestRegistrationEntity r where r.user.userId = ?1")
    RequestRegistrationEntity getRequestByUserIdR(@Param("userId") Integer userId);

    @Query("select r from RequestRegistrationEntity r where (r.user.username = ?1 or r.user.email = ?2)")
    RequestRegistrationEntity checkIfExistUser(@Param("username") String username, @Param("email") String email);

    @Query("select r from RequestRegistrationEntity r where (r.status = 'PENDING' or r.status = 'REJECTED') and r.user.username = ?1")
    RequestRegistrationEntity checkUserStatus(@Param("username") String username);
}
