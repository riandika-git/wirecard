package com.wirecard.assignment.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wirecard.assignment.dto.UserTypeDto;
import com.wirecard.assignment.message.CommonMessage;
import com.wirecard.assignment.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class UserTypeControllerItest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	/*
	 * Test: get user type record for the first three rows 
	 * Expected: status OK, first record name is admin, second is student, third is teacher
	 * 
	 */
	@Test
	public void testGetUserType() throws Exception {
		mockMvc.perform(get("/usertype/list?pageNumber=1&pageSize=3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].name").value("admin"))
				.andExpect(jsonPath("$.data[1].name").value("student"))
				.andExpect(jsonPath("$.data[2].name").value("teacher"));

	}

	/*
	 * Test: add user type record 
	 * Expected: status OK, responseCode 0102, responseDescription "New record has been saved"
	 * 
	 */
	@Test
	public void testAddUserType() throws Exception {
		mockMvc.perform(post("/usertype/add").content(JsonUtil.serialize(new UserTypeDto(null, "newtype")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("responseCode").value(CommonMessage.RECORD_SAVED.getResponseCode()))
				.andExpect(jsonPath("responseDescription").value(CommonMessage.RECORD_SAVED.getResponseDescription()));

	}

	/*
	 * Test: update user type record for id 5 
	 * Expected: status OK, responseCode 0103, responseDescription "New record has been updated"
	 * 
	 */
	@Test
	public void testUpdateUserType() throws Exception {
		String json = JsonUtil.serialize(new UserTypeDto(Long.valueOf("5"), "updatedtype"));
		mockMvc.perform(put("/usertype/update").content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("responseCode").value(CommonMessage.RECORD_UPDATED.getResponseCode()))
				.andExpect(jsonPath("responseDescription").value(CommonMessage.RECORD_UPDATED.getResponseDescription()));

	}
	
	/*
	 * Test: delete user type record for id 8 
	 * Expected: status OK, responseCode 0104, responseDescription "New record has been deleted"
	 * 
	 */
	@Test
	public void testDeleteUserType() throws Exception {
		mockMvc.perform(delete("/usertype/delete/8")).andExpect(status().isOk())
				.andExpect(jsonPath("responseCode").value(CommonMessage.RECORD_DELETED.getResponseCode()))
				.andExpect(jsonPath("responseDescription").value(CommonMessage.RECORD_DELETED.getResponseDescription()));

	}

}
