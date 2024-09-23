package com.example.demo.decorator;

import com.example.demo.firm.model.FirmEntity;
import com.example.demo.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DecoratorRepository extends JpaRepository<DecoratorEntity, Integer> {

    @Query("select count(d) from DecoratorEntity d")
    Integer numOfDecorators();

    @Query("select d from DecoratorEntity d where d.firm.firmId = ?1")
    List<DecoratorEntity> getAllDecoratorForFirm(@Param("firmId") Long firmId);

    @Query("select count(d) from DecoratorEntity d where d.firm.firmId = ?1")
    Integer numOfDecoratorsInFirm(@Param("firmId") Long firmId);

    @Query("select d from DecoratorEntity d ")
    List<DecoratorEntity> getAllDecorators();

    @Query("select d from DecoratorEntity d where d.user.userId = ?1")
    DecoratorEntity getDecoratorByUserId(@Param("userId") Integer userId);
}
