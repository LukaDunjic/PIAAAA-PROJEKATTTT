package com.example.demo.appointment;

import com.example.demo.appointment.dtos.AvgJobsPerDayOfWeekDto;
import com.example.demo.appointment.dtos.JobsDistributionDto;
import com.example.demo.appointment.dtos.JobsPerMonthDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    @Query("select a from AppointmentEntity a")
    List<AppointmentEntity> getAllAppointments();

    @Query("select a from AppointmentEntity a where a.id = ?1")
    AppointmentEntity getAppointmentById(@Param("appointmentId") Integer appointmentId);

    @Query("select count(a) from AppointmentEntity a where  a.appointmentDate > ?1")
    Integer countByAppointmentDateAfter(LocalDateTime since);

    @Query("select a from AppointmentEntity a where a.owner.userId = ?1 and a.status != 'REJECTED' order by a.appointmentDate asc")
    List<AppointmentEntity> findCurrentAppointmentsByOwner(Integer ownerId);

    @Query("select a from AppointmentEntity a where a.owner.userId = ?1 and (a.status = 'REJECTED' or a.status = 'FINISHED') order by a.appointmentDate asc ")
    List<AppointmentEntity> findArchivedAppointmentsByOwner(Integer ownerId);

    @Query("select a from AppointmentEntity a, DecoratorEntity d where d.user.userId = ?1" +
            " and d.firm.firmId = a.firm.firmId and a.status = 'PENDING' order by a.appointmentDate desc")
    List<AppointmentEntity> currentAppointmentsFirm(@Param("userId") Integer userId);

    @Query("select a from AppointmentEntity a where a.status = 'FINISHED' order by a.finishedDate desc")
    List<AppointmentEntity> get3MostRecent(Pageable pageable);

    @Query("select new com.example.demo.appointment.dtos.JobsPerMonthDto(month(a.appointmentDate), count(*)) " +
            "from AppointmentEntity a, DecoratedGardenEntity dg " +
            "where a.garden.id = dg.garden.id and dg.decorator.user.userId = ?1 " +
            "group by month(a.appointmentDate)")
    List<JobsPerMonthDto> jobsPerMonth(@Param("userId") Integer userId);

    @Query("SELECT new com.example.demo.appointment.dtos.JobsDistributionDto(dg.decorator.user.userId, COUNT(a)) " +
            "FROM AppointmentEntity a, DecoratedGardenEntity dg " +
            "WHERE a.garden.id = dg.garden.id AND a.firm.firmId = :firmId " +
            "GROUP BY dg.decorator.user.userId")
    List<JobsDistributionDto> getJobsDistribution(@Param("firmId") Long firmId);


    @Query("SELECT new com.example.demo.appointment.dtos.AvgJobsPerDayOfWeekDto(DAYOFWEEK(a.appointmentDate), COUNT(a)) " +
            "FROM AppointmentEntity a " +
            "WHERE a.appointmentDate > ?1 " +
            "GROUP BY DAYOFWEEK(a.appointmentDate)")
    List<AvgJobsPerDayOfWeekDto> getAvgJobsPerDayOfWeek(@Param("cutoffDate") LocalDateTime cutoffDate);
}
