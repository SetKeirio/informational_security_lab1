package org.first_lab.secureapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount assignedUser;

    @Column(nullable = false)
    private String taskTitle;

    @Column(nullable = false, length = 1000)
    private String taskDescription;

    @Column(nullable = false)
    private boolean completedStatus = false;

    @Column(nullable = false)
    private String priorityLevel = "MEDIUM";

    @Column(nullable = false)
    private LocalDateTime createdTimestamp;

    private LocalDateTime dueDate;

    public Task() {
        this.createdTimestamp = LocalDateTime.now();
    }

    public Task(String taskTitle, String taskDescription, UserAccount assignedUser) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.assignedUser = assignedUser;
        this.createdTimestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserAccount assignedUser) {
        this.assignedUser = assignedUser;
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

    public boolean isCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}