package org.first_lab.secureapi.repository;

import org.first_lab.secureapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
        SELECT t FROM Task t
        WHERE LOWER(t.taskTitle) LIKE LOWER(CONCAT('%', :searchText, '%'))
           OR LOWER(t.taskDescription) LIKE LOWER(CONCAT('%', :searchText, '%'))
        """)
    List<Task> findTasksByText(@Param("searchText") String searchText);

    @Query("SELECT t FROM Task t WHERE t.completedStatus = :completed")
    List<Task> findByCompletionStatus(@Param("completed") boolean completed);

    @Query("SELECT t FROM Task t WHERE t.priorityLevel = :priority")
    List<Task> findByPriority(@Param("priority") String priority);
}