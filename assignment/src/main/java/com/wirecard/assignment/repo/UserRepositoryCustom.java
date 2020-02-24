package com.wirecard.assignment.repo;

import java.util.Date;
import java.util.List;

import com.wirecard.assignment.model.User;

public interface UserRepositoryCustom {

	public List<User> search(String name, Date date, Integer pageNumber, Integer pageSize, String sortBy,
			String sortType);
}
