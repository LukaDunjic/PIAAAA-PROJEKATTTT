package com.example.demo.user.repository;

import com.example.demo.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.userId = ?1")
    UserEntity getUserById(@Param("userId") Integer userId);

    @Query("select u from UserEntity u where u.username = ?1 and u.password = ?2")
    UserEntity loginUser(@Param("username") String username, @Param("password") String password);


}
