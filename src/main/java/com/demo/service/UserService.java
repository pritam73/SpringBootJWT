package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.bean.BaseClass;
import com.demo.model.User;

@Service
public class UserService extends BaseClass {

	public Object logoutUser(User user) {
		user.setLoginStatus(false);
		userRepository.save(user);
		return null;
	}

}