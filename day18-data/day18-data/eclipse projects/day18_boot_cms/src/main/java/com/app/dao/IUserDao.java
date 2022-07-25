package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.User;

public interface IUserDao extends JpaRepository<User, Long> {
	// User validateUser(String email, String password);
	// custom finder method
	Optional<User> findByEmailAndPassword(String em, String pass);

}
