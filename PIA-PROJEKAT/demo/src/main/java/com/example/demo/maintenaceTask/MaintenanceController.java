package com.example.demo.maintenaceTask;

import com.example.demo.PIAResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/maintenance")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MaintenanceController {
    final MaintenanceTaskService maintenanceTaskService;

    @GetMapping("/owner/{ownerId}")
    public PIAResponse<MainOwnerDto> getOwnerMaintenanceTasks(@PathVariable Integer ownerId) {
        return maintenanceTaskService.getTasksByOwner(ownerId);
    }

    @GetMapping("/add/{appointmentId}")
    public PIAResponse<Boolean> addMaintenanceTasks(@PathVariable Integer appointmentId) {
        return maintenanceTaskService.addMaintenanceTasks(appointmentId);
    }

    @GetMapping("/pending/{userId}")
    public PIAResponse<List<MaintenanceTask>> getPendingMaintenanceTasks(@PathVariable Integer userId) {
        return maintenanceTaskService.getPendingOrInProgressTasks(userId);
    }

    @PostMapping("/approve/{taskId}/{estimatedCompletionTime}")
    public ResponseEntity<?> approveTask(@PathVariable Integer taskId, @PathVariable LocalDateTime estimatedCompletionTime) {
        maintenanceTaskService.approveTask(taskId, estimatedCompletionTime);
        return ResponseEntity.ok("Task Approved");
    }

    @DeleteMapping("/decline/{taskId}")
    public ResponseEntity<?> declineTask(@PathVariable Integer taskId) {
        maintenanceTaskService.declineTask(taskId);
        return ResponseEntity.ok("Task Declined");
    }
}
