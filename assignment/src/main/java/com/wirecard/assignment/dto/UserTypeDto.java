package com.wirecard.assignment.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class UserTypeDto {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "name", example="superadmin")
	@NotBlank
	@Length(min = 2, max = 100)
	private String name;

	public UserTypeDto(Long id, String name) {
		this.id = id;
		this.name = name;
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

}
