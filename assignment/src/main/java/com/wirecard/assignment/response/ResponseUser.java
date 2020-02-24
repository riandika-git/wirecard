package com.wirecard.assignment.response;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.wirecard.assignment.model.User;

public class ResponseUser extends ResponseCommon {

	private List<User> data;
	private Map<String, Integer> pageInfo;

	public ResponseUser(HttpStatus status, String reponseCode, String responseDescription, List<User> data,
			Map<String, Integer> recordInfo) {
		super(status, reponseCode, responseDescription);
		setData(data);
		setPageInfo(recordInfo);
	}

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

	public Map<String, Integer> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(Map<String, Integer> pageInfo) {
		this.pageInfo = pageInfo;
	}

}
