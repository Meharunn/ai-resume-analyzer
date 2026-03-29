package com.ai.resume_analyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.resume_analyzer.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User> findByEmail(String email);

}
