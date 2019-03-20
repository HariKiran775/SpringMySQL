package com.san.tutorial.spring.dao;

import java.util.List;

import com.san.tutorial.spring.entity.User;

public interface UserDao {
	
	void add(User user);
	List <User> listUsers();

}
