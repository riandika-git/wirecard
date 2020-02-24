package com.wirecard.assignment.mapper;

import com.wirecard.assignment.dto.UserTypeDto;
import com.wirecard.assignment.model.UserType;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class UserTypeMapper {
	
	private UserTypeMapper() {
	}

	public static UserType convertDtoToUserType(UserTypeDto userTypeDto) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserTypeDto.class, UserType.class);
		MapperFacade mapper = mapperFactory.getMapperFacade();

		return mapper.map(userTypeDto, UserType.class);
	}

}
