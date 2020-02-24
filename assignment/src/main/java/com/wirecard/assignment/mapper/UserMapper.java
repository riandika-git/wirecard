package com.wirecard.assignment.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wirecard.assignment.dto.UserDto;
import com.wirecard.assignment.model.User;
import com.wirecard.assignment.model.UserType;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class UserMapper {

	private UserMapper() {
	}

	public static User convertDtoToUser(UserDto userDto) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserDto.class, User.class).field("id", "id").field("name", "name").register();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		User user = mapper.map(userDto, User.class);
		user.setDate(convertToDate(userDto.getDate()));
		user.setType(new UserType(userDto.getType()));

		return user;
	}

	private static Date convertToDate(String dateString) {
		try {
			return new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
