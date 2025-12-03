package org.first_lab.secureapi.service;

import org.first_lab.secureapi.dto.TaskCreationRequest;
import org.first_lab.secureapi.dto.TaskResponseDTO;
import org.first_lab.secureapi.dto.TaskSearchRequest;
import org.first_lab.secureapi.entity.Task;
import org.first_lab.secureapi.entity.UserAccount;
import org.first_lab.secureapi.repository.TaskRepository;
import org.first_lab.secureapi.repository.UserAccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskManagementService {
    private final TaskRepository taskRepository;
    private final UserAccountRepository userAccountRepository;

    public TaskManagementService(TaskRepository taskRepository, UserAccountRepository userAccountRepository) {
        this.taskRepository = taskRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional
    public TaskResponseDTO createNewTask(TaskCreationRequest request, Authentication authentication) {
        String userLogin = authentication.getName();
        UserAccount user = userAccountRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        Task task = new Task();
        task.setTaskTitle(request.getTaskTitle());
        task.setTaskDescription(request.getTaskDescription());
        task.setPriorityLevel(request.getPriority());
        task.setAssignedUser(user);

        task = taskRepository.save(task);

        return convertToResponse(task);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TaskResponseDTO> searchTasks(TaskSearchRequest request) {
        List<Task> tasks;

        if (request.getCompletedStatus() != null && request.getPriorityFilter() != null) {
            tasks = taskRepository.findAll().stream()
                    .filter(t -> t.isCompletedStatus() == request.getCompletedStatus())
                    .filter(t -> t.getPriorityLevel().equals(request.getPriorityFilter()))
                    .toList();
        } else if (request.getCompletedStatus() != null) {
            tasks = taskRepository.findByCompletionStatus(request.getCompletedStatus());
        } else if (request.getPriorityFilter() != null) {
            tasks = taskRepository.findByPriority(request.getPriorityFilter());
        } else {
            tasks = taskRepository.findTasksByText(request.getSearchQuery().trim());
        }

        return tasks.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional
    public TaskResponseDTO markTaskAsCompleted(Long taskId, Authentication authentication) {
        String userLogin = authentication.getName();
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Задача не найдена"));

        if (!task.getAssignedUser().getUserLogin().equals(userLogin)) {
            throw new IllegalStateException("Нет доступа к задаче");
        }

        task.setCompletedStatus(true);
        task = taskRepository.save(task);

        return convertToResponse(task);
    }

    private TaskResponseDTO convertToResponse(Task task) {
        TaskResponseDTO response = new TaskResponseDTO();
        response.setTaskId(task.getId());
        response.setTaskTitle(task.getTaskTitle());
        response.setTaskDescription(task.getTaskDescription());
        response.setCompleted(task.isCompletedStatus());
        response.setPriority(task.getPriorityLevel());
        response.setCreatedAt(task.getCreatedTimestamp());
        response.setDueDate(task.getDueDate());
        response.setAssignedUserLogin(task.getAssignedUser().getUserLogin());
        response.setAssignedUserFullName(task.getAssignedUser().getFullUserName());
        return response;
    }
}
