package com.tasks.app.cqrs.task.query;

public class TaskDTO {

	private final Long id;
	private final String title;
	private final String description;
	private final boolean completed;


	public TaskDTO(Long id, String title, String description, boolean completed) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public boolean isCompleted() { return completed; }
}