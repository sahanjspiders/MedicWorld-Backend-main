package com.jsp.medicworld.service;


import com.jsp.medicworld.entity.User;

public interface UserService {
	
	Object signupUser(User u);
	
	Object loginUser(String email,String password);
	
	Object getAllUser();
	
	String resetPassword(String email,String pwd,String conformpwd);
	
	Object forgotPasswordVerifyWithEmail(String email);
	
	String otpCrossVerficationWithEmail(String email,int otp);
	
	Object sendOtptoEmail(String email);

}
