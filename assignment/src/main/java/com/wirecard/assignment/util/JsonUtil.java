package com.wirecard.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private JsonUtil() {
	}

	private static ObjectMapper mapper = new ObjectMapper();

	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	public static String serialize(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T extends Object> T deserialize(Object object, Class<T> clazz) {
		try {
			return mapper.convertValue(object, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
