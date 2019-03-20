package com.san.tutorial.spring.service;

import java.util.List;

import com.san.tutorial.spring.entity.User;

public interface UserService {
	void add(User user);
	List<User> listUsers();

}
