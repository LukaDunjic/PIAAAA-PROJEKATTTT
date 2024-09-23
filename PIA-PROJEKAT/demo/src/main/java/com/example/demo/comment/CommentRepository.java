package com.example.demo.comment;

import com.example.demo.appointment.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query("select c from CommentEntity c where c.appointment.firm.firmId = ?1")
    List<CommentEntity> getAllCommentsForFirm(@Param("firmId") Long firmId);

    @Query("select c from CommentEntity c where c.appointment.id = ?1")
    List<CommentEntity> getAppointmentComment(@Param("appointment") Integer firmId);
}
