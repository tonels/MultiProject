package io.github.knes1.todo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author knesek
 * Created on: 07/07/15
 */
@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dateCreated;
	private String description;
	private boolean completed;

	public Todo() {
		dateCreated = new Date();
		completed = false;
	}

	public Todo(String description) {
		this();
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
