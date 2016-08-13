package com.kiesoft.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kiesoft.jpa.user.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByUsername(String username);

}
