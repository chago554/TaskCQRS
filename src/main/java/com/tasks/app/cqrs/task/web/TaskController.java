package com.tasks.app.cqrs.task.web;

import com.tasks.app.cqrs.task.command.CreateTaskCommand;
import com.tasks.app.cqrs.task.command.TaskAggregateRepository;
import com.tasks.app.cqrs.task.command.TaskCommandHandler;
import com.tasks.app.cqrs.task.command.UpdateTaskCommand;
import com.tasks.app.cqrs.task.query.GetAllTasksQuery;
import com.tasks.app.cqrs.task.query.TaskDTO;
import com.tasks.app.cqrs.task.query.TaskQueryHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	TaskAggregateRepository aggregateRepository;
	
	private final TaskCommandHandler commandHandler;
	private final TaskQueryHandler queryHandler;

	public TaskController(TaskCommandHandler commandHandler, TaskQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@PostMapping("create")
	public ResponseEntity<Long> createTask(@RequestBody CreateTaskRequest request) {
		CreateTaskCommand command = new CreateTaskCommand(request.getTitle(), request.getDescription());
		Long taskId = commandHandler.handle(command);
		return new ResponseEntity<>(taskId, HttpStatus.CREATED);
	}

	@PutMapping("update") 
	public ResponseEntity<String> updateTask(@RequestBody UpdateTaskRequest request) {
		UpdateTaskCommand command = new UpdateTaskCommand(request.getId(), request.getTitle(), request.getDescription(), request.isCompleted());
		commandHandler.handle(command);
		return ResponseEntity.ok("Task updated succefull!");
	}

	@GetMapping("get-all")
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		GetAllTasksQuery query = new GetAllTasksQuery();
		List<TaskDTO> tasks = queryHandler.handle(query);
		return ResponseEntity.ok(tasks);
	}

}
