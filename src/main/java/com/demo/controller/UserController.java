package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.BaseClass;
import com.demo.bean.ResultDTO;
import com.demo.model.User;

@RequestMapping("/api/v1/user")
@RestController
public class UserController extends BaseClass {

	@GetMapping("/dashboard")
	public ResponseEntity<?> home(Authentication auth) {
		System.err.println("::: UserRestController.home :::");
		ResultDTO<?> responsePacket = null;
		try {
			User user = userRepository.findByEmail(auth.getName()).get();
			responsePacket = new ResultDTO<>(user, "Welcome to the Dashboard: ", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/logoutUser")
	public ResponseEntity<?> logoutUser(Authentication auth) {
		System.err.println("::: UserController.logoutUser :::");
		ResultDTO<?> responsePacket = null;
		try {
			User user = userRepository.findByEmail(auth.getName()).get();
			responsePacket = new ResultDTO<>(userService.logoutUser(user), "User Logout Successfully", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

}
