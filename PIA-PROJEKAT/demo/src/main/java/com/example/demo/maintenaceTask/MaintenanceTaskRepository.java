package com.example.demo.maintenaceTask;

import com.example.demo.appointment.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Integer> {
    @Query("select a from AppointmentEntity a where a.owner.userId = ?1 and a.status = 'FINISHED'")
    List<AppointmentEntity> findByAppointmentOwnerId(@Param("ownerId") Integer ownerId);

    @Query("select m from MaintenanceTask m where m.appointment.id = ?1")
    MaintenanceTask findMainForAppointment(@Param("appointmentId") Integer appointmentId);

    @Query("select m from MaintenanceTask m where m.appointment.owner.userId = ?1 and (m.status = 'APPROVED' or m.status = 'FINISHED')")
    List<MaintenanceTask> findByStatus(@Param("ownerId") Integer ownerId);

    @Query("select m from MaintenanceTask m, DecoratedGardenEntity dg " +
            "where m.status = 'PENDING' and m.appointment.garden.id = dg.garden.id and dg.decorator.user.userId = ?1")
    List<MaintenanceTask> findPending(@Param("userId") Integer userId);

    @Query("select m from MaintenanceTask m where m.id = ?1")
    MaintenanceTask findMainById(@Param("mId") Integer mId);
}
