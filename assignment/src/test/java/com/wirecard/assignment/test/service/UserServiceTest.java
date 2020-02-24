package com.wirecard.assignment.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.wirecard.assignment.message.CommonMessage;
import com.wirecard.assignment.model.User;
import com.wirecard.assignment.model.UserType;
import com.wirecard.assignment.repo.UserRepository;
import com.wirecard.assignment.response.ResponseCommon;
import com.wirecard.assignment.response.ResponseUser;
import com.wirecard.assignment.service.UserServiceImpl;
import com.wirecard.assignment.util.JsonUtil;

public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * Test: get record for User
	 * Expected: should return 2 results and the first user name is James Bond
	 */
	@Test
	public void getUserListTest() {
		List<User> list = generateUserList();

		when(userRepo.search(null, null, 1, 5, null, null)).thenReturn(list);

		ResponseEntity<Object> resp = userService.getUserList(null, null, 1, 5, null, null);
		ResponseUser respUser = JsonUtil.deserialize(resp.getBody(), ResponseUser.class);

		assertEquals(2, respUser.getData().size());
		assertEquals("James Bond", respUser.getData().get(0).getName());
		verify(userRepo, times(1)).search(null, null, 1, 5, null, null);

	}

	/*
	 * Test: save new record for User
	 * Expected: should return success response code: 0102
	 */
	@Test
	public void addUserTest() {
		User user = generateUser();

		when(userRepo.save(user)).thenReturn(user);

		ResponseEntity<Object> resp = userService.addUser(user);
		ResponseCommon respUser = JsonUtil.deserialize(resp.getBody(), ResponseCommon.class);

		assertEquals(CommonMessage.RECORD_SAVED.getResponseCode(), respUser.getResponseCode());
		verify(userRepo, times(1)).save(user);

	}

	/*
	 * Test: delete a record from UserType 
	 * Expected: should call delete method from repo once
	 */
	@Test
	public void deleteUserTest() {
		User user = generateUser();
		Optional<User> optionalEntityType = Optional.of(user);

		when(userRepo.findById(1L)).thenReturn(optionalEntityType);

		userService.deleteUser(1L);

		verify(userRepo, times(1)).deleteById(1L);

	}
	
	private UserType generateUserType() {
		UserType userType = new UserType(new Long(1), "admin");
		return userType;
	}

	private User generateUser() {
		User user = new User(new Long(1), "James Bond", new Date(), generateUserType());
		return user;
	}

	private List<User> generateUserList() {
		List<User> list = new ArrayList<User>();
		User user1 = new User(new Long(1), "James Bond", new Date(), generateUserType());
		User user2 = new User(new Long(2), "John Wick", new Date(), generateUserType());
		list.add(user1);
		list.add(user2);
		return list;
	}

}
