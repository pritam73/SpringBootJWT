package com.demo.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.bean.BaseClass;
import com.demo.bean.LoginRequest;
import com.demo.bean.UserSignupRequest;
import com.demo.model.Role;
import com.demo.model.User;
import com.demo.utils.Constants;

@Service
public class AuthService extends BaseClass {

	public Object addRole(Role reqData) {
		roleRepository.save(reqData);
		return null;
	}

	public Object signUpUser(UserSignupRequest reqData) {
		User user = new User(reqData.getName(), reqData.getEmail(), reqData.getMobileNo(), Constants.welcomeCoins,
				Constants.getDateAndTime(), Constants.getDateAndTime());
		user.setPassword(passwordEncoder.encode(reqData.getPassword()));
		Role role = roleRepository.findByRoleName("USER");
		user.setRoleId(role);
		userRepository.save(user);
		return null;
	}

	public Object loginUser(LoginRequest loginRequest) {
		Optional<User> userdata = userRepository.findByEmail(loginRequest.getEmail());
		if (userdata.isPresent()) {
			User user = userdata.get();
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtConfiguration.generateJwtTokenVendor(authentication);
			HashMap<String, Object> userData = new HashMap<>();
			userData.put("userId", user.getUuid());
			userData.put("name", user.getName());
			userData.put("email", user.getEmail());
			userData.put("mobileNo", user.getMobileNo());
			userData.put("token", jwt);
			user.setLoginStatus(true);
			userRepository.save(user);
			return userData;
		} else {
			throw new RuntimeException("Invalid Credentials");
		}
	}
}
