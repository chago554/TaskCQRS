package com.tasks.app.cqrs.task.query;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskQueryHandler {

	private final TaskProjectionRepository projectionRepository;

	public TaskQueryHandler(TaskProjectionRepository projectionRepository) {
		this.projectionRepository = projectionRepository;
	}

	public List<TaskDTO> handle(GetAllTasksQuery query) {
		List<TaskProjection> projections = projectionRepository.findAll();
		
		return projections.stream()
				.map(p -> new TaskDTO(p.getId(), p.getTitle(), p.getDescription(), p.isCompleted()))
				.collect(Collectors.toList());
	}
}