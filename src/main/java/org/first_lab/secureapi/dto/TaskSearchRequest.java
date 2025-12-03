package org.first_lab.secureapi.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskSearchRequest {

    @NotBlank(message = "Поисковый запрос не может быть пустым")
    private String searchQuery;

    private Boolean completedStatus;
    private String priorityFilter;

    public TaskSearchRequest() {
    }

    public TaskSearchRequest(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Boolean getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(Boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getPriorityFilter() {
        return priorityFilter;
    }

    public void setPriorityFilter(String priorityFilter) {
        this.priorityFilter = priorityFilter;
    }
}
