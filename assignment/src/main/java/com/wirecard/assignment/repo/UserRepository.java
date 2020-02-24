package com.wirecard.assignment.repo;

import org.springframework.data.repository.CrudRepository;

import com.wirecard.assignment.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

}
