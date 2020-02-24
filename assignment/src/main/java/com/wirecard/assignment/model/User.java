package com.wirecard.assignment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class User {

	private Long id;

	private String name;

	private Date date;

	private UserType type;

	public User() {
	}

	public User(Long id, String name, Date date, UserType type) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.type = type;
	}

	@ApiModelProperty(value = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToOne
	@JoinColumn(name = "type")
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
