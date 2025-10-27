package com.tasks.app.cqrs.task.command;

import com.tasks.app.cqrs.task.event.TaskCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskCommandHandler {

	private final TaskAggregateRepository aggregateRepository;
	private final ApplicationEventPublisher eventPublisher;

	public TaskCommandHandler(TaskAggregateRepository aggregateRepository, ApplicationEventPublisher eventPublisher) {
		this.aggregateRepository = aggregateRepository;
		this.eventPublisher = eventPublisher;
	}

	@Transactional
	public Long handle(CreateTaskCommand command) {
		TaskAggregate aggregate = TaskAggregate.create(command.getTitle(), command.getDescription());
		TaskAggregate savedAggregate = aggregateRepository.save(aggregate);

		eventPublisher.publishEvent(new TaskCreatedEvent(
				this,
				savedAggregate.getId(),
				savedAggregate.getTitle(),
				savedAggregate.getDescription(),
				savedAggregate.isCompleted()
		));

		return savedAggregate.getId();
	}
}