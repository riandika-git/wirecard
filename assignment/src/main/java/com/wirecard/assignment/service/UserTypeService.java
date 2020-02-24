package com.wirecard.assignment.service;

import org.springframework.http.ResponseEntity;

import com.wirecard.assignment.model.UserType;

public interface UserTypeService {

	public ResponseEntity<Object> getUserTypeList(String name, Integer page, Integer pageSize, String sortBy,
			String sortType);

	public ResponseEntity<Object> addUserType(UserType userType);

	public ResponseEntity<Object> getUserTypeById(Long id);

	public ResponseEntity<Object> updateUserType(UserType userType);

	public ResponseEntity<Object> deleteUserType(Long id);

}
