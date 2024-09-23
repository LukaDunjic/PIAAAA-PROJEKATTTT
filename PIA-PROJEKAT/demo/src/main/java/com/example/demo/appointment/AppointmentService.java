package com.example.demo.appointment;

import com.example.demo.PIAResponse;
import com.example.demo.appointment.dtos.AppointmentDTO;
import com.example.demo.appointment.dtos.AvgJobsPerDayOfWeekDto;
import com.example.demo.appointment.dtos.JobsDistributionDto;
import com.example.demo.appointment.dtos.JobsPerMonthDto;
import com.example.demo.comment.CommentDto;
import com.example.demo.comment.CommentEntity;
import com.example.demo.comment.CommentRepository;
import com.example.demo.decoratedGarden.DecoratedGardenEntity;
import com.example.demo.decoratedGarden.DecoratedGardenRepository;
import com.example.demo.decorator.DecoratorRepository;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.firm.repository.FirmRepository;
import com.example.demo.garden.GardenEntity;
import com.example.demo.garden.GardenRepository;
import com.example.demo.user.model.UserEntity;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    final AppointmentRepository appointmentRepository;
    final GardenRepository gardenRepository;
    final UserRepository userRepository;
    final FirmRepository firmRepository;
    final CommentRepository commentRepository;
    public PIAResponse<AppointmentEntity> addAppointment(AppointmentEntity appointmentEntity){

        UserEntity user = userRepository.getUserById(appointmentEntity.getOwner().getUserId());
        appointmentEntity.getGarden().setOwner(user);
        GardenEntity garden = gardenRepository.save(appointmentEntity.getGarden());

        FirmEntity firm = firmRepository.getFirmById(appointmentEntity.getFirm().getFirmId());

        appointmentEntity.setGarden(garden);
        appointmentEntity.setFirm(firm);
        appointmentEntity.setOwner(user);

        AppointmentEntity appointment = appointmentRepository.save(appointmentEntity);

        PIAResponse<AppointmentEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(appointment);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<List<AppointmentEntity>> getCurrentAppointments(Integer ownerId) {
        // Get appointments that are not completed
        List<AppointmentEntity> currentAppointments = appointmentRepository.findCurrentAppointmentsByOwner(ownerId);
        return new PIAResponse<>(currentAppointments, "ok", "Success");
    }

    public PIAResponse<List<AppointmentDTO>> getArchivedAppointments(Integer ownerId) {
        // Pronađi arhivirana zakazivanja za korisnika
        List<AppointmentEntity> archivedAppointments = appointmentRepository.findArchivedAppointmentsByOwner(ownerId);

        // Kreiraj DTO liste
        List<AppointmentDTO> appointmentDTOs = archivedAppointments.stream()
                .map(appointment -> {
                    // Pronađi komentar za svako zakazivanje (ako postoji)
                    CommentEntity comment = commentRepository.getAppointmentComment(appointment.getId()).stream().findFirst().orElse(null);

                    return new AppointmentDTO(
                            appointment.getId(),
                            appointment.getAppointmentDate().toString(),
                            appointment.getFirm().getName(),
                            comment != null ? comment.getComment() : null,  // Ako postoji komentar, postavi ga
                            comment != null ? comment.getRating() : null     // Ako postoji ocena, postavi je
                    );
                }).collect(Collectors.toList());

        return new PIAResponse<>(appointmentDTOs, "ok", "Success");
    }

    public PIAResponse<AppointmentEntity> addCommentAndRating(Integer appointmentId, CommentDto commentDto) {
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null && appointment.getStatus() == AppointmentStatus.FINISHED) {
            CommentEntity comment = new CommentEntity();
            comment.setAppointment(appointment);
            comment.setComment(commentDto.getComment());
            comment.setRating(commentDto.getRating());
            commentRepository.save(comment);

            return new PIAResponse<>(appointment, "ok", "Comment added successfully");
        }
        return new PIAResponse<>(null, "error", "Appointment not found or not completed");
    }

    public PIAResponse<String> cancelAppointment(Integer appointmentId) {
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null && appointment.getAppointmentDate().isAfter(LocalDateTime.now().plusDays(1))) {
            appointmentRepository.delete(appointment);
            return new PIAResponse<>(null, "ok", "Appointment canceled successfully");
        }
        return new PIAResponse<>(null, "error", "Appointment cannot be canceled");
    }

    public PIAResponse<List<AppointmentEntity>> currentAppointmentsFirm(Integer userId) {

        return new PIAResponse<>(appointmentRepository.currentAppointmentsFirm(userId), "ok", "Comment added successfully");
    }
    final DecoratedGardenRepository decoratedGardenRepository;
    public PIAResponse<List<AppointmentEntity>> getAllAppointmentsForDec(Integer userId){
        return new PIAResponse<>(decoratedGardenRepository.getAllAppointmentsForDec(userId), "ok", "success.");
    }

    public PIAResponse<AppointmentEntity> addPictureToAppointment(Integer appointmentId, MultipartFile pictureFile) throws IOException {
        AppointmentEntity appointment = appointmentRepository.getAppointmentById(appointmentId);

        if (appointment == null) {
            return new PIAResponse<>(null, "error", "Appointment not found");
        }

        if (pictureFile != null && !pictureFile.isEmpty()) {
            try {
                appointment.setPicture(pictureFile.getBytes());
                appointment.setStatus(AppointmentStatus.FINISHED);
                appointment.setFinishedDate(LocalDateTime.now());
                appointmentRepository.save(appointment);
            } catch (IOException e) {
                return new PIAResponse<>(null, "error", "Failed to save picture");
            }
        }

        return new PIAResponse<>(appointment, "ok", "Picture added successfully");
    }

    public PIAResponse<List<AppointmentEntity>> get3MostRecent(){
        Pageable pageable = (Pageable) PageRequest.of(0, 4);  // Page index (0) i broj rezultata (4)
        List<AppointmentEntity> recentAppointments = appointmentRepository.get3MostRecent(pageable);
        return new PIAResponse<>(recentAppointments, "ok", "Picture added successfully");
    }

    public PIAResponse<AppointmentEntity> rejectAppointment(Integer appointmentId, String rejection){
        AppointmentEntity appointmentEntity = appointmentRepository.getAppointmentById(appointmentId);
        if(appointmentEntity == null){
            return new PIAResponse<>(null ,"ok", "Appointment not founde.");
        }
        appointmentEntity.setStatus(AppointmentStatus.REJECTED);
        appointmentEntity.setRejectComment(rejection);
        AppointmentEntity appointment = appointmentRepository.save(appointmentEntity);
        return new PIAResponse<>(appointment ,"ok", "Success.");
    }
    final DecoratorRepository decoratorRepository;
    public PIAResponse<AppointmentEntity> acceptAppointment(Integer appointmentId, Integer userId) {
        AppointmentEntity appointmentEntity = appointmentRepository.getAppointmentById(appointmentId);
        if(appointmentEntity == null){
            return new PIAResponse<>(null ,"ok", "Appointment not founde.");
        }
        appointmentEntity.setStatus(AppointmentStatus.APPROVED);
        AppointmentEntity appointment = appointmentRepository.save(appointmentEntity);
        DecoratedGardenEntity decoratedGardenEntity = new DecoratedGardenEntity();
        decoratedGardenEntity.setDecorator(decoratorRepository.getDecoratorByUserId(userId));
        decoratedGardenEntity.setGarden(appointment.getGarden());
        decoratedGardenRepository.save(decoratedGardenEntity);

        return new PIAResponse<>(appointment ,"ok", "Success.");

    }

    public PIAResponse<List<JobsPerMonthDto>> jobsPerMonth(Integer userId) {
        List<JobsPerMonthDto>  appointmentEntities = appointmentRepository.jobsPerMonth(userId);

        return new PIAResponse<>(appointmentEntities ,"ok", "Success.");
    }

    public PIAResponse<List<JobsDistributionDto>> getJobsDistribution(Integer userId) {
        FirmEntity firm = firmRepository.getUserByFirm(userId);
        return new PIAResponse<>(appointmentRepository.getJobsDistribution(firm.getFirmId()) ,"ok", "Success.");
    }

    public PIAResponse<List<AvgJobsPerDayOfWeekDto>> getAvgJobsPerDayOfWeek() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusMonths(24);  // Poslednjih 24 meseca
        List<AvgJobsPerDayOfWeekDto> jobsPerDayList = appointmentRepository.getAvgJobsPerDayOfWeek(cutoffDate);

        // Preračunavanje prosečnog broja poslova po danima
        double totalMonths = 24.0;
        jobsPerDayList.forEach(job -> {
            double avgJobCount = job.getAvgJobCount() / totalMonths;
            job.setAvgJobCount(avgJobCount);
        });
        return new PIAResponse<>( jobsPerDayList ,"ok", "Success.");
    }

    public PIAResponse<AppointmentEntity> saveCanvas(Integer numFountains, Integer numPools, Integer appointmentId) {
        AppointmentEntity appointmentEntity = appointmentRepository.getAppointmentById(appointmentId);
        appointmentEntity.setNumFountains(numFountains);
        appointmentEntity.setNumPools(numPools);
        return new PIAResponse<>(appointmentRepository.save(appointmentEntity), "ok", "Success.");
    }
}
