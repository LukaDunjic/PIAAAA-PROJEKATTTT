package com.example.demo.appointment;

import com.example.demo.PIAResponse;
import com.example.demo.appointment.dtos.AppointmentDTO;
import com.example.demo.appointment.dtos.AvgJobsPerDayOfWeekDto;
import com.example.demo.appointment.dtos.JobsDistributionDto;
import com.example.demo.appointment.dtos.JobsPerMonthDto;
import com.example.demo.comment.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/appointment")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AppointmentController {
    final AppointmentService appointmentService;

    @PostMapping(value = "/addAppointment")
    public PIAResponse<AppointmentEntity> addAppointment(@RequestBody AppointmentEntity appointmentEntity){
        return appointmentService.addAppointment(appointmentEntity);
    }


    @GetMapping("/currentAppointments/{ownerId}")
    public PIAResponse<List<AppointmentEntity>> getCurrentAppointments(@PathVariable Integer ownerId) {
        return appointmentService.getCurrentAppointments(ownerId);
    }

    @GetMapping("/archivedAppointments/{ownerId}")
    public PIAResponse<List<AppointmentDTO>> getArchivedAppointments(@PathVariable Integer ownerId) {
        return appointmentService.getArchivedAppointments(ownerId);
    }

    @PostMapping("/addCommentAndRating/{appointmentId}")
    public PIAResponse<AppointmentEntity> addCommentAndRating(@PathVariable Integer appointmentId, @RequestBody CommentDto commentDto) {
        return appointmentService.addCommentAndRating(appointmentId, commentDto);
    }

    @PostMapping("/cancelAppointment/{appointmentId}")
    public PIAResponse<String> cancelAppointment(@PathVariable Integer appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    @GetMapping("/unprocessed/{userId}")
    public PIAResponse<List<AppointmentEntity>> currentAppointmentsFirm(@PathVariable Integer userId) {
        return appointmentService.currentAppointmentsFirm(userId);
    }

    @GetMapping("/accepted/{userId}")
    public PIAResponse<List<AppointmentEntity>> getAllAppointmentsForDec(@PathVariable Integer userId) {
        return appointmentService.getAllAppointmentsForDec(userId);
    }

    @PostMapping(value = "/addPicture/{appointmentId}", consumes = "multipart/form-data")
    public PIAResponse<AppointmentEntity> addPictureToAppointment(
            @PathVariable Integer appointmentId,
            @RequestParam("picture") MultipartFile picture) throws IOException {
        return appointmentService.addPictureToAppointment(appointmentId, picture);
    }

    @GetMapping(value = "/get3MostRecent")
    public PIAResponse<List<AppointmentEntity>> get3MostRecent(){
        return appointmentService.get3MostRecent();
    }

    @GetMapping(value = "/rejectAppointment/{appointmentId}/{rejection}")
    public PIAResponse<AppointmentEntity> rejectAppointment(@PathVariable Integer appointmentId,@PathVariable String rejection){
        return appointmentService.rejectAppointment(appointmentId, rejection);
    }

    @GetMapping(value = "/acceptAppointment/{appointmentId}/{userId}")
    public PIAResponse<AppointmentEntity> acceptAppointment(@PathVariable Integer appointmentId,@PathVariable Integer userId){
        return appointmentService.acceptAppointment(appointmentId, userId);
    }

    @GetMapping(value = "/jobsPerMonth/{userId}")
    public PIAResponse<List<JobsPerMonthDto>> jobsPerMonth(@PathVariable Integer userId){
        return appointmentService.jobsPerMonth(userId);
    }

    @GetMapping(value = "/jobsPerFirm/{userId}")
    public PIAResponse<List<JobsDistributionDto>> getJobsDistribution(@PathVariable Integer userId){
        return appointmentService.getJobsDistribution(userId);
    }

    @GetMapping(value = "/avgJobsPerDayOfWeekDto")
    public PIAResponse<List<AvgJobsPerDayOfWeekDto>> getAvgJobsPerDayOfWeek(){
        return appointmentService.getAvgJobsPerDayOfWeek();
    }

    @GetMapping(value = "/saveCanvas/{appointmentId}/{numFountains}/{numPools}")
    public PIAResponse<AppointmentEntity> saveCanvas(@PathVariable Integer appointmentId, @PathVariable Integer numFountains, @PathVariable Integer numPools){
        return appointmentService.saveCanvas(numFountains, numPools, appointmentId);
    }
}
