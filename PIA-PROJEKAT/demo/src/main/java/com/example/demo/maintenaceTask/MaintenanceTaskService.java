package com.example.demo.maintenaceTask;

import com.example.demo.PIAResponse;
import com.example.demo.appointment.AppointmentEntity;
import com.example.demo.appointment.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceTaskService {
     final MaintenanceTaskRepository maintenanceTaskRepository;
     final AppointmentRepository appointmentRepository;
    public PIAResponse<MainOwnerDto> getTasksByOwner(Integer ownerId) {
        List<AppointmentEntity> appointmentEntities = maintenanceTaskRepository.findByAppointmentOwnerId(ownerId);
        MainOwnerDto mainOwnerDto = new MainOwnerDto();
        List<MainOwnerMainDto> mainDtos = new ArrayList<>();

        appointmentEntities.forEach(elem->{
            boolean flag = true;
            MainOwnerMainDto dto = new MainOwnerMainDto();
            MaintenanceTask maintenanceTask = maintenanceTaskRepository.findMainForAppointment(elem.getId());
            if(maintenanceTask == null){
                dto.setFinishedServiceTime(elem.getFinishedDate());
                dto.setFinishedTime(elem.getFinishedDate());
            }
            else{
                if((maintenanceTask.getStatus() == MaintenanceStatus.PENDING) || (maintenanceTask.getEstimatedCompletionTime().isAfter(LocalDateTime.now().minusMonths(6))))flag = false;
                dto.setFinishedServiceTime(maintenanceTask.getEstimatedCompletionTime());
                dto.setFinishedTime(elem.getFinishedDate());
            }
            dto.setAppointmentId(elem.getId());
            dto.setNumFountains(elem.getNumFountains());
            dto.setNumPools(elem.getNumPools());

            if(flag)mainDtos.add(dto);
        });
        mainOwnerDto.setMainDtos(mainDtos);
        mainOwnerDto.setMaintenanceTaskList(maintenanceTaskRepository.findByStatus(ownerId));
        return new PIAResponse<>(mainOwnerDto, "ok", "Success.");
    }

    public PIAResponse<List<MaintenanceTask>> getPendingOrInProgressTasks(Integer userId) {
        return new PIAResponse<>(maintenanceTaskRepository.findPending(userId), "ok", "success.");
    }

    public void approveTask(Integer taskId, LocalDateTime estimatedCompletion) {
        MaintenanceTask task = maintenanceTaskRepository.findMainById(taskId);
        task.setEstimatedCompletionTime(estimatedCompletion);
        task.setStatus(MaintenanceStatus.APPROVED);
        maintenanceTaskRepository.save(task);
    }

    public void declineTask(Integer taskId) {
        MaintenanceTask maintenanceTask = maintenanceTaskRepository.findMainById(taskId);
        maintenanceTask.setStatus(MaintenanceStatus.FINISHED);
    }

    public PIAResponse<Boolean> addMaintenanceTasks(Integer appointmentId) {
        MaintenanceTask task = maintenanceTaskRepository.findMainForAppointment(appointmentId);
        AppointmentEntity appointmentEntity = appointmentRepository.getAppointmentById(appointmentId);
        if(task == null){
            MaintenanceTask maintenanceTask = new MaintenanceTask();
            maintenanceTask.setAppointment(appointmentEntity);
            maintenanceTask.setNumPools(appointmentEntity.getNumPools());
            maintenanceTask.setNumFountains(appointmentEntity.getNumFountains());
            maintenanceTaskRepository.save(maintenanceTask);
        }
        else{
            task.setStatus(MaintenanceStatus.PENDING);
            task.setEstimatedCompletionTime(null);
            task.setBookingTime(LocalDateTime.now());
            maintenanceTaskRepository.save(task);
        }
        return new PIAResponse<>(true, "ok", "success");
    }
}
