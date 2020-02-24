package com.wirecard.assignment.repo;

import java.util.List;

import com.wirecard.assignment.model.UserType;

public interface UserTypeRepositoryCustom {

	public List<UserType> search(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortType);
}
