package com.urs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urs.dto.UserDTO;

public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {
	UserDTO findByName(String name);
}
