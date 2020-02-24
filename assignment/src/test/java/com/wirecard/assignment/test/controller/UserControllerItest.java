package com.wirecard.assignment.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.wirecard.assignment.dto.UserDto;
import com.wirecard.assignment.message.CommonMessage;
import com.wirecard.assignment.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class UserControllerItest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/*
	 * Test: get user record for the first three rows 
	 * Expected: status OK, first record name is John, second is Wick, third is Michael
	 * 
	 */
	@Test
	public void testGetUser() throws Exception {
		mockMvc.perform(get("/user/list?pageNumber=1&pageSize=3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].name").value("John"))
				.andExpect(jsonPath("$.data[1].name").value("Wick"))
				.andExpect(jsonPath("$.data[2].name").value("Michael"));

	}

	/*
	 * Test: add user record 
	 * Expected: status OK, responseCode 0102, responseDescription "New record has been saved"
	 * 
	 */
	@Test
	public void testAddUser() throws Exception {
		mockMvc.perform(post("/user/add")
				.content(JsonUtil.serialize(new UserDto(null, "Angel",
						new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()), Long.valueOf("2"))))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("responseCode").value(CommonMessage.RECORD_SAVED.getResponseCode()))
				.andExpect(jsonPath("responseDescription").value(CommonMessage.RECORD_SAVED.getResponseDescription()));

	}

	/*
	 * Test: update user record for id 5 
	 * Expected: status OK, responseCode 0103, responseDescription "New record has been updated"
	 * 
	 */
	@Test
	public void testUpdateUser() throws Exception {
		mockMvc.perform(put("/user/update")
				.content(JsonUtil.serialize(new UserDto(Long.valueOf("5"), "New User",
						new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()), Long.valueOf("5"))))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("responseCode").value(CommonMessage.RECORD_UPDATED.getResponseCode()))
				.andExpect(jsonPath("responseDescription").value(CommonMessage.RECORD_UPDATED.getResponseDescription()));

	}

	/*
	 * Test: delete user record for id 7 
	 * Expected: status OK, responseCode 0104, responseDescription "New record has been deleted"
	 * 
	 */
	@Test
	public void testDeleteUser() throws Exception {
		mockMvc.perform(delete("/user/delete/3")).andExpect(status().isOk())
				.andExpect(jsonPath("responseCode").value(CommonMessage.RECORD_DELETED.getResponseCode()))
				.andExpect(jsonPath("responseDescription").value(CommonMessage.RECORD_DELETED.getResponseDescription()));

	}

}
