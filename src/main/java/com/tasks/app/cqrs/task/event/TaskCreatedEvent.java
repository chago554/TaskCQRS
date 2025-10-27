package com.tasks.app.cqrs.task.event;

import org.springframework.context.ApplicationEvent;

public class TaskCreatedEvent extends ApplicationEvent {

	private final Long taskId;
	private final String title;
	private final String description;
	private final boolean isCompleted;

	public TaskCreatedEvent(Object source, Long taskId, String title, String description, boolean isCompleted) {
		super(source);
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.isCompleted = isCompleted;
	}

	public Long getTaskId() {
		return taskId;
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