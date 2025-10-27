package com.tasks.app.cqrs.task.command;

import com.tasks.app.cqrs.task.event.TaskCreatedEvent;
import com.tasks.app.cqrs.task.query.TaskProjection;
import com.tasks.app.cqrs.task.query.TaskProjectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;


@Service
public class TaskProjectionService {

	private final TaskProjectionRepository projectionRepository;

	public TaskProjectionService(TaskProjectionRepository projectionRepository) {
		this.projectionRepository = projectionRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleTaskCreatedEvent(TaskCreatedEvent event) {
		
		TaskProjection projection = new TaskProjection();
		projection.setId(event.getTaskId());
		projection.setTitle(event.getTitle());
		projection.setDescription(event.getDescription());
		projection.setCompleted(event.isCompleted());

		projectionRepository.save(projection);
	}
}