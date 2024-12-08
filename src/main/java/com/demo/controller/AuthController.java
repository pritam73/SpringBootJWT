package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.BaseClass;
import com.demo.bean.LoginRequest;
import com.demo.bean.ResultDTO;
import com.demo.bean.UserSignupRequest;
import com.demo.model.Role;
import com.demo.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseClass {

	@Autowired
	private AuthService authService;

	@PostMapping("/addRole")
	public ResponseEntity<?> addRole(@RequestBody Role reqData) {
		ResultDTO<?> responsePacket = null;
		try {
			responsePacket = new ResultDTO<>(authService.addRole(reqData), "Role Added Successfully", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signUpUser")
	public ResponseEntity<?> signUpUser(@RequestBody UserSignupRequest reqData) {
		System.err.println("::: AuthController.signUpUser :::");
		ResultDTO<?> responsePacket = null;
		try {
			boolean isEmail = userRepository.existByEmailOrMobile(reqData.getEmail(), reqData.getMobileNo());
			if (isEmail) {
				responsePacket = new ResultDTO<>("Email Or Mobile No is already exist", false);
				return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
			} else {
				responsePacket = new ResultDTO<>(authService.signUpUser(reqData), "User Registered Successfully", true);
				return new ResponseEntity<>(responsePacket, HttpStatus.OK);
			}
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/userLogin")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {
		System.err.println("::: AuthController.userLogin :::");
		ResultDTO<?> responsePacket = null;
		try {
			responsePacket = new ResultDTO<>(authService.loginUser(loginRequest), "Login Successfully", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(null, e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/test")
	public @ResponseBody String test() {
		System.err.println("::: Executing Test Method :::");
		return "Test Method";
	}
}
