package com.wirecard.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.assignment.dto.UserTypeDto;
import com.wirecard.assignment.mapper.UserTypeMapper;
import com.wirecard.assignment.model.UserType;
import com.wirecard.assignment.service.UserTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Endpoint for service DB", produces = "application/json")
@RestController
public class UserTypeController {

	@Autowired
	private UserTypeService userTypeService;

	@ApiOperation("Get user type list")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserType.class) })
	@GetMapping("/usertype/list")
	public ResponseEntity<Object> getUserTypeList(
			@ApiParam("Name") @RequestParam(value = "name", required = false) String name,
			@ApiParam("Page number") @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@ApiParam("Maximum record retrieved") @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@ApiParam("name / address") @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@ApiParam("asc / desc") @RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType

	) {
		return userTypeService.getUserTypeList(name, pageNumber, pageSize, sortBy, sortType);

	}

	@ApiOperation("Get user type by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserType.class) })
	@GetMapping("/usertype/{id}")
	public ResponseEntity<Object> getUserTypeById(@PathVariable("id") Long id) {
		return userTypeService.getUserTypeById(id);
	}

	@ApiOperation("Save new user type record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PostMapping("/usertype/add")
	public ResponseEntity<Object> addUserType(@Valid @RequestBody UserTypeDto userTypeDto) {
		UserType userType = UserTypeMapper.convertDtoToUserType(userTypeDto);
		return userTypeService.addUserType(userType);
	}

	@ApiOperation("Update user type record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PutMapping("/usertype/update")
	public ResponseEntity<Object> updateUserType(@Valid @RequestBody UserTypeDto userTypeDto) {
		UserType userType = UserTypeMapper.convertDtoToUserType(userTypeDto);
		return userTypeService.updateUserType(userType);
	}

	@ApiOperation("Delete user type by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@DeleteMapping("/usertype/delete/{id}")
	public ResponseEntity<Object> deleteUserType(@PathVariable("id") Long id) {
		return userTypeService.deleteUserType(id);
	}

}
