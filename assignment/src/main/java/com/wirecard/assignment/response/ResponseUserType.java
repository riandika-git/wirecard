package com.wirecard.assignment.response;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.wirecard.assignment.model.UserType;

public class ResponseUserType extends ResponseCommon {

	private List<UserType> data;
	private Map<String, Integer> pageInfo;

	public ResponseUserType(HttpStatus status, String responseCode, String responseDescription, List<UserType> data, Map<String, Integer> recordInfo) {
		super(status, responseCode, responseDescription);
		setData(data);
		setPageInfo(recordInfo);
	}

	public List<UserType> getData() {
		return data;
	}

	public void setData(List<UserType> data) {
		this.data = data;
	}

	public Map<String, Integer> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(Map<String, Integer> pageInfo) {
		this.pageInfo = pageInfo;
	}

}
