package com.tasks.app.cqrs.task.command;

public class UpdateTaskCommand {

	private final Long id;
	private final String title;
	private final String description;
	private final boolean isCompleted;

	public UpdateTaskCommand(Long id, String title, String description, boolean isCompleted) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isCompleted = isCompleted;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}
}