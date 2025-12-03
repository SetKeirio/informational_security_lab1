package org.first_lab.secureapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskCreationRequest {

    @NotBlank(message = "Название задачи обязательно")
    @Size(min = 3, max = 200, message = "Название задачи должно содержать от 3 до 200 символов")
    private String taskTitle;

    @NotBlank(message = "Описание задачи обязательно")
    @Size(min = 10, max = 1000, message = "Описание задачи должно содержать от 10 до 1000 символов")
    private String taskDescription;

    private String priority = "MEDIUM";

    public TaskCreationRequest() {
    }

    public TaskCreationRequest(String taskTitle, String taskDescription) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
