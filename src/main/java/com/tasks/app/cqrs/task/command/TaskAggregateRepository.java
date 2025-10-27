package com.tasks.app.cqrs.task.command; 

import org.springframework.data.jpa.repository.JpaRepository; 

public interface TaskAggregateRepository extends JpaRepository<TaskAggregate, Long> { 
} 