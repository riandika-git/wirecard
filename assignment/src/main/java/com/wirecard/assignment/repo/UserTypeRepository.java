package com.wirecard.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wirecard.assignment.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long>, UserTypeRepositoryCustom {

}
