package com.wirecard.assignment.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wirecard.assignment.message.CommonMessage;
import com.wirecard.assignment.model.User;
import com.wirecard.assignment.repo.UserRepository;
import com.wirecard.assignment.response.ResponseCommon;
import com.wirecard.assignment.response.ResponseError;
import com.wirecard.assignment.response.ResponseUser;

import liquibase.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public ResponseEntity<Object> getUserList(String name, Date date, Integer pageNumber, Integer pageSize,
			String sortBy, String sortType) {
		List<User> userList = userRepo.search(name, date, pageNumber, pageSize, sortBy, sortType);

		Map<String, Integer> info = getPageInfoTemplate(pageNumber, userList.size());

		ResponseUser resp = new ResponseUser(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), userList, info);
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addUser(User user) {
		userRepo.save(user);
		ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_SAVED.getResponseCode(),
				CommonMessage.RECORD_SAVED.getResponseDescription());
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getUserById(Long id) {
		User user = userRepo.findById(id).orElse(null);
		Map<String, Integer> info = getPageInfoTemplate(1, user == null ? 0 : 1);
		ResponseUser resp = new ResponseUser(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), Arrays.asList(user), info);
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateUser(User user) {
		User existingUser = userRepo.findById(user.getId()).orElse(null);

		if (existingUser == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed update record for id " + user.getId());
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			if(StringUtils.isEmpty(user.getName())) {
				user.setName(existingUser.getName());
			}
			if(user.getDate() == null) {
				user.setDate(existingUser.getDate());
			}
			if(user.getType().getId() == null) {
				user.setType(existingUser.getType());
			}
			userRepo.save(user);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_UPDATED.getResponseCode(),
					CommonMessage.RECORD_UPDATED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Object> deleteUser(Long id) {
		User existingUser = userRepo.findById(id).orElse(null);

		if (existingUser == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed delete record for id " + id);
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			userRepo.deleteById(id);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_DELETED.getResponseCode(),
					CommonMessage.RECORD_DELETED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}

	}

	private Map<String, Integer> getPageInfoTemplate(Integer pageNumber, Integer totalRecord) {
		Map<String, Integer> pageInfo = new HashMap<>();
		pageInfo.put("pageNumber", pageNumber);
		pageInfo.put("totalRecord", totalRecord);
		return pageInfo;
	}

}
