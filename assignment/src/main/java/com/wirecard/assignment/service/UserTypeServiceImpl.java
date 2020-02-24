package com.wirecard.assignment.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wirecard.assignment.message.CommonMessage;
import com.wirecard.assignment.model.UserType;
import com.wirecard.assignment.repo.UserTypeRepository;
import com.wirecard.assignment.response.ResponseCommon;
import com.wirecard.assignment.response.ResponseError;
import com.wirecard.assignment.response.ResponseUserType;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeRepository userTypeRepo;

	@Override
	public ResponseEntity<Object> getUserTypeList(String name, Integer pageNumber, Integer pageSize, String sortBy,
			String sortType) {
		List<UserType> userTypeList = userTypeRepo.search(name, pageNumber, pageSize, sortBy, sortType);

		Map<String, Integer> info = getPageInfoTemplate(pageNumber, userTypeList.size());

		ResponseUserType resp = new ResponseUserType(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), userTypeList, info);
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addUserType(UserType userType) {
		userTypeRepo.save(userType);
		ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_SAVED.getResponseCode(),
				CommonMessage.RECORD_SAVED.getResponseDescription());
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getUserTypeById(Long id) {
		UserType userType = userTypeRepo.findById(id).orElse(null);
		Map<String, Integer> info = getPageInfoTemplate(1, userType == null ? 0 : 1);
		ResponseUserType resp = new ResponseUserType(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), Arrays.asList(userType), info);
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateUserType(UserType userType) {
		UserType existingUserType = userTypeRepo.findById(userType.getId()).orElse(null);

		if (existingUserType == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed update record for id " + userType.getId());
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			userTypeRepo.save(userType);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_UPDATED.getResponseCode(),
					CommonMessage.RECORD_UPDATED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Object> deleteUserType(Long id) {
		UserType existingUserType = userTypeRepo.findById(id).orElse(null);

		if (existingUserType == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed delete record for id " + id);
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			userTypeRepo.deleteById(id);
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
