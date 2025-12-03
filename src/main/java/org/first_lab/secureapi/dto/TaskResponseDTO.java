package org.first_lab.secureapi.dto;

import java.time.LocalDateTime;

public class TaskResponseDTO {

    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private boolean isCompleted;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private String assignedUserLogin;
    private String assignedUserFullName;

    public TaskResponseDTO() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime deadline) {
        this.dueDate = deadline;
    }

    public String getAssignedUserLogin() {
        return assignedUserLogin;
    }

    public void setAssignedUserLogin(String assignedUserLogin) {
        this.assignedUserLogin = assignedUserLogin;
    }

    public String getAssignedUserFullName() {
        return assignedUserFullName;
    }

    public void setAssignedUserFullName(String assignedUserFullName) {
        this.assignedUserFullName = assignedUserFullName;
    }
}
