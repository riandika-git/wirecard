package com.wirecard.assignment.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.wirecard.assignment.message.CommonMessage;
import com.wirecard.assignment.model.UserType;
import com.wirecard.assignment.repo.UserTypeRepository;
import com.wirecard.assignment.response.ResponseCommon;
import com.wirecard.assignment.response.ResponseUserType;
import com.wirecard.assignment.service.UserTypeServiceImpl;
import com.wirecard.assignment.util.JsonUtil;

public class UserTypeServiceTest {

	@InjectMocks
	UserTypeServiceImpl userTypeService;

	@Mock
	UserTypeRepository userTypeRepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * Test: get record for UserType 
	 * Expected: should return 2 results and the first userType name is admin
	 */
	@Test
	public void getUserTypeListTest() {
		List<UserType> list = generateUserTypeList();

		when(userTypeRepo.search(null, 1, 5, null, null)).thenReturn(list);

		ResponseEntity<Object> resp = userTypeService.getUserTypeList(null, 1, 5, null, null);
		ResponseUserType respUserType = JsonUtil.deserialize(resp.getBody(), ResponseUserType.class);

		assertEquals(2, respUserType.getData().size());
		assertEquals("admin", respUserType.getData().get(0).getName());
		verify(userTypeRepo, times(1)).search(null, 1, 5, null, null);

	}

	/*
	 * Test: save new record for UserType 
	 * Expected: should return success response code: 0102
	 */
	@Test
	public void addUserTypetTest() {
		UserType userType = generateUserType();

		when(userTypeRepo.save(userType)).thenReturn(userType);

		ResponseEntity<Object> resp = userTypeService.addUserType(userType);
		ResponseCommon respUserType = JsonUtil.deserialize(resp.getBody(), ResponseCommon.class);

		assertEquals(CommonMessage.RECORD_SAVED.getResponseCode(), respUserType.getResponseCode());
		verify(userTypeRepo, times(1)).save(userType);

	}

	/*
	 * Test: delete a record from UserType 
	 * Expected: should call delete method from repo once
	 */
	@Test
	public void deleteUserTypeTest() {
		UserType userType = generateUserType();
		Optional<UserType> optionalEntityType = Optional.of(userType);

		when(userTypeRepo.findById(1L)).thenReturn(optionalEntityType);

		userTypeService.deleteUserType(1L);

		verify(userTypeRepo, times(1)).deleteById(1L);

	}

	private UserType generateUserType() {
		UserType userType = new UserType(new Long(1), "admin");
		return userType;
	}

	private List<UserType> generateUserTypeList() {
		List<UserType> list = new ArrayList<UserType>();
		UserType userType1 = new UserType(new Long(1), "admin");
		UserType userType2 = new UserType(new Long(2), "student");
		list.add(userType1);
		list.add(userType2);
		return list;
	}

}
