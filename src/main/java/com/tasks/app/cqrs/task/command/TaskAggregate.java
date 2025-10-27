package com.tasks.app.cqrs.task.command;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_aggregate")
public class TaskAggregate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private boolean isCompleted;

	public TaskAggregate() {
	}

	private TaskAggregate(String title, String description) {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("El titulo no puede estar vacío.");
		}

		this.title = title;
		this.description = description;
		this.isCompleted = false;
	}

	public static TaskAggregate create(String title, String description) {
		return new TaskAggregate(title, description);
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}