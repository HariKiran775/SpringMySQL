package com.san.tutorial.spring;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.san.tutorial.spring.config.AppConfig;
import com.san.tutorial.spring.entity.User;
import com.san.tutorial.spring.service.UserService;

public class MainApp {
	public static void main(String[] args) {
	      AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);

	      UserService userService = context.getBean(UserService.class);

	      // Add Users
	      userService.add(new User("SAN", "SAN@example.com"));
	      userService.add(new User("SAN1", "SAN1@example.com"));
	      
	      // Get Users
	      List<User> users = userService.listUsers();
	      for (User user : users) {
	         System.out.println("Id = "+user.getId());
	         System.out.println("Name = "+user.getName());	         
	         System.out.println("Email = "+user.getEmail());
	         System.out.println();
	      }

	      context.close();
	   }
}
