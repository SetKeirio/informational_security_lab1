package org.first_lab.secureapi.controller;

import jakarta.validation.Valid;
import org.first_lab.secureapi.dto.TaskCreationRequest;
import org.first_lab.secureapi.dto.TaskResponseDTO;
import org.first_lab.secureapi.dto.TaskSearchRequest;
import org.first_lab.secureapi.service.TaskManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskManagementService taskManagementService;

    public TaskController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(@Valid @RequestBody TaskCreationRequest request, Authentication authentication) {
        return taskManagementService.createNewTask(request, authentication);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskManagementService.getAllTasks();
    }

    @GetMapping("/search")
    public List<TaskResponseDTO> searchTasks(@Valid @RequestBody TaskSearchRequest request) {
        return taskManagementService.searchTasks(request);
    }

    @PutMapping("/{taskId}/complete")
    public TaskResponseDTO markTaskCompleted(@PathVariable Long taskId, Authentication authentication) {
        return taskManagementService.markTaskAsCompleted(taskId, authentication);
    }
}
