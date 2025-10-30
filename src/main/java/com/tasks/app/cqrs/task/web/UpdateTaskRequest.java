package com.tasks.app.cqrs.task.web;

public class UpdateTaskRequest {
	
	private Long id;
	private String title;
	private String description;
	private boolean isCompleted;

	// Getters y Setters
	
	public Long getId(){
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean completed) {
		isCompleted = completed;
	}
}