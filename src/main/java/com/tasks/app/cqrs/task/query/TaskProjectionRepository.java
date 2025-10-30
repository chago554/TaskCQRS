package com.tasks.app.cqrs.task.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskProjectionRepository extends JpaRepository<TaskProjection, Long> {
}
 