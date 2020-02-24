package com.wirecard.assignment.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.assignment.dto.UserDto;
import com.wirecard.assignment.mapper.UserMapper;
import com.wirecard.assignment.model.User;
import com.wirecard.assignment.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Endpoint for service DB", produces = "application/json")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation("Get user list")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = User.class) })
	@GetMapping("/user/list")
	public ResponseEntity<Object> getUserList(
			@ApiParam("Name") @RequestParam(value = "name", required = false) String name,
			@ApiParam("Date (yyyy-mm-dd)") @RequestParam(value = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@ApiParam("Page number") @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@ApiParam("Maximum record retrieved") @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@ApiParam("name / type") @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@ApiParam("asc / desc") @RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		return userService.getUserList(name, date, pageNumber, pageSize, sortBy, sortType);
	}

	@ApiOperation("Get user by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = User.class) })
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	@ApiOperation("Save new user record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PostMapping("/user/add")
	public ResponseEntity<Object> addCar(@Valid @RequestBody UserDto userDto) {
		User user = UserMapper.convertDtoToUser(userDto);
		return userService.addUser(user);
	}

	@ApiOperation("Update user record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PutMapping("/user/update")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDto) {
		User user = UserMapper.convertDtoToUser(userDto);
		return userService.updateUser(user);
	}

	@ApiOperation("Delete user by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
		return userService.deleteUser(id);
	}

}
