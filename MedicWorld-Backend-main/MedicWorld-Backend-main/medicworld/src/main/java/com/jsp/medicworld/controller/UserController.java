package com.jsp.medicworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medicworld.entity.User;
import com.jsp.medicworld.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService ser;

	
	@GetMapping("sendotptoemail")
	public ResponseEntity<?> sendOtp(@RequestHeader String email){
		Object obj4=ser.sendOtptoEmail(email);
		if(obj4.equals("otp send sucessfully")) {
			return ResponseEntity.status(200).body(obj4);
		}
		return ResponseEntity.status(400).body(obj4);
	}
	

	@GetMapping("crossverfiyotp")
	public ResponseEntity<String> verfiyOtp(@RequestHeader String email,@RequestHeader int otp) {
		String temp= ser.otpCrossVerficationWithEmail(email, otp);
				if(temp.equals("sucess")) {
					return ResponseEntity.status(200).body(temp);
				}
		return ResponseEntity.status(400).body(temp);
	}
	
	@PostMapping("signup")
	public ResponseEntity<Object> signupUser(@RequestBody User u) {
		Object obj1 = ser.signupUser(u);
		if (obj1 instanceof User) {
			return ResponseEntity.status(HttpStatus.CREATED).body(obj1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj1);
	}

	@GetMapping("login")
	public ResponseEntity<Object> loginUser(@RequestHeader String emailorphone, @RequestHeader String password) {
		Object obj2 = ser.loginUser(emailorphone, password);
		System.out.println(emailorphone);
		if (obj2 instanceof User) {
			return ResponseEntity.status(HttpStatus.OK).body(obj2);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj2);
	}
	
	@PutMapping("resetpassword")
	public ResponseEntity<String> updateUser(@RequestHeader String email, @RequestHeader String password,
			@RequestHeader String conformpassword) {
		String str = ser.resetPassword(email, password, conformpassword);
		if (str.equals("password update successfully")) {
			return new ResponseEntity<String>(str, HttpStatus.OK);
		}
		return new ResponseEntity<String>(str, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("getall")
	public ResponseEntity<Object> getAllUser(){
		if(ser.getAllUser() instanceof User) {
			return ResponseEntity.status(HttpStatus.OK).body(ser.getAllUser());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ser.getAllUser());
	}
	
	@GetMapping("forgotpassword")
	public ResponseEntity<Object> verfiy(@RequestHeader String email)
	{
		Object obj3=ser.forgotPasswordVerifyWithEmail(email);
		if(obj3.equals("otp send successfully")) {
			return ResponseEntity.status(HttpStatus.OK).body(obj3);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj3);
	}
}
