package com.wirecard.assignment.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "name", example="Leonardo")
	@NotBlank
	@Length(min = 2, max = 100)
	private String name;

	@ApiModelProperty(value = "date", example="2020-02-25 14:10:15")
	@Length(min = 10, max = 19)
	private String date;

	@ApiModelProperty(value = "type", example="1")
	private Long type;

	public UserDto(Long id, String name, String date, Long type) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

}
