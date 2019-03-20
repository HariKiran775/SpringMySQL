package com.san.tutorial.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.san.tutorial.spring.dao.UserDao;
import com.san.tutorial.spring.entity.User;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public void add(User user) {
		userDao.add(user);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> listUsers() {
		
		return userDao.listUsers();
	}

	
}
