package com.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.configuration.JwtConfiguration;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;

public abstract class BaseClass {

	@Autowired
	protected UserService userService;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected RoleRepository roleRepository;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	protected JwtConfiguration jwtConfiguration;
}
