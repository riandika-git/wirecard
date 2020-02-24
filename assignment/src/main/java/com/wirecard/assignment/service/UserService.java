package com.wirecard.assignment.service;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.wirecard.assignment.model.User;

public interface UserService {

	public ResponseEntity<Object> getUserList(String name, Date date, Integer pageNumber, Integer pageSize,
			String sortBy, String sortType);

	public ResponseEntity<Object> addUser(User cars);

	public ResponseEntity<Object> getUserById(Long id);

	public ResponseEntity<Object> updateUser(User cars);

	public ResponseEntity<Object> deleteUser(Long id);

}
