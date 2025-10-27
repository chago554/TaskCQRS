package com.tasks.app.cqrs.task.web;

import com.tasks.app.cqrs.task.command.CreateTaskCommand;
import com.tasks.app.cqrs.task.command.TaskCommandHandler;
import com.tasks.app.cqrs.task.query.GetAllTasksQuery;
import com.tasks.app.cqrs.task.query.TaskDTO;
import com.tasks.app.cqrs.task.query.TaskQueryHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskCommandHandler commandHandler;
	private final TaskQueryHandler queryHandler;

	public TaskController(TaskCommandHandler commandHandler, TaskQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}


	@PostMapping
	public ResponseEntity<Long> createTask(@RequestBody CreateTaskRequest request) {
		// Mapear Request a Command
		CreateTaskCommand command = new CreateTaskCommand(request.getTitle(), request.getDescription());

		// Delegar la ejecución al CommandHandler
		Long taskId = commandHandler.handle(command);
		return new ResponseEntity<>(taskId, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		GetAllTasksQuery query = new GetAllTasksQuery();
		List<TaskDTO> tasks = queryHandler.handle(query);
		return ResponseEntity.ok(tasks);
	}
	
}