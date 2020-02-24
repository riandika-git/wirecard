package com.wirecard.assignment.message;

public enum CommonMessage {
	RECORD_RETRIEVED("0101", "Successfully retrieve data"), 
	RECORD_SAVED("0102", "New record has been saved"),
	RECORD_UPDATED("0103", "Record has been updated"), 
	RECORD_DELETED("0104", "Record has been deleted"),
	ID_DOES_NOT_EXIST("0201", "Id does not exist"), 
	INVALID_PARAMETER("0202", "Invalid parameter");

	public final String responseCode;
	public final String responseDescription;

	private CommonMessage(String responseCode, String responseDescription) {
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

}
