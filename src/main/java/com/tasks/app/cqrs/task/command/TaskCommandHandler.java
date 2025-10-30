package com.tasks.app.cqrs.task.command;

import com.tasks.app.cqrs.task.event.TaskCreatedEvent;
import com.tasks.app.cqrs.task.event.TaskUpdatedEvent;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskCommandHandler {

	@Autowired
	TaskAggregateRepository aggregateRepository;
	
	private final ApplicationEventPublisher eventPublisher;

	public TaskCommandHandler(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
	@Transactional
	public Long handle(CreateTaskCommand command) {
		// Crear el Aggregate y guardar
		TaskAggregate aggregate = TaskAggregate.create(command.getTitle(), command.getDescription());
		TaskAggregate savedAggregate = aggregateRepository.save(aggregate);

		// Emitir el Evento de CREACIÓN
		eventPublisher.publishEvent(new TaskCreatedEvent(this,
				savedAggregate.getId(),
				savedAggregate.getTitle(),
				savedAggregate.getDescription(),
				savedAggregate.isCompleted()
		));
		return savedAggregate.getId();
	}
	
	@Transactional
	public void handle(UpdateTaskCommand command) {
		TaskAggregate aggregate = aggregateRepository.findById(command.getId())
				.orElseThrow(() -> new NoSuchElementException("Tarea no encontrada con ID: " + command.getId()));

		aggregate.update(command.getTitle(), command.getDescription(), command.isCompleted());
		aggregateRepository.save(aggregate);

		eventPublisher.publishEvent(new TaskUpdatedEvent (
				this,
				aggregate.getId(),
				aggregate.getTitle(),
				aggregate.getDescription(),
				aggregate.isCompleted()
		));
	}
	
}