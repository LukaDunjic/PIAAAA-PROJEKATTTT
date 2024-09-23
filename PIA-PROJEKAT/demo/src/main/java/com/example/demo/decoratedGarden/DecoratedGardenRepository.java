package com.example.demo.decoratedGarden;

import com.example.demo.appointment.AppointmentEntity;
import com.example.demo.garden.GardenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DecoratedGardenRepository extends JpaRepository<DecoratedGardenEntity, Integer> {

    @Query("select count(*) from DecoratedGardenEntity")
    Integer countDecoratedGardens();

    @Query("select dg.garden from DecoratedGardenEntity dg where dg.decorator.firm.firmId = ?1")
    List<GardenEntity> getAllGardensForFirm(@Param("firmId") Long firmId);

    @Query("select distinct a from DecoratedGardenEntity dg, AppointmentEntity a " +
            "where dg.decorator.user.userId = ?1 and a.garden.id = dg.garden.id and" +
            " a.status != 'REJECTED' and a.status != 'PENDING' order by a.finishedDate desc ")
    List<AppointmentEntity> getAllAppointmentsForDec(@Param("userId") Integer userId);
}
