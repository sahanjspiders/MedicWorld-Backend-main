package com.jsp.medicworld.serviceimpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.medicworld.dao.UserDao;
import com.jsp.medicworld.entity.User;
import com.jsp.medicworld.exception.OtpMismatch;
import com.jsp.medicworld.exception.PasswordInvalidException;
import com.jsp.medicworld.exception.UserAlreadyExistsException;
import com.jsp.medicworld.exception.UserNotFoundException;
import com.jsp.medicworld.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao rep;

	@Autowired
	private JavaMailSender send;
	
	@Autowired
	private User temp;
		
	private SimpleMailMessage mail = new SimpleMailMessage();

	@Override
	public Object signupUser(User u) {
//		User u1 = rep.findByEmailOrPhone(u.getEmail(),u.getPhone());
//		try {
//			if (u1 instanceof User) {
//				throw new UserAlreadyExistsException("user already exist try different phonenumber "+u.getPhone());		
//			}
//		} catch (UserAlreadyExistsException e) {
//			return e.getMessage();
//		}
		User u1=rep.getByEmail(u.getEmail());
		u.setUid(u1.getUid());;
		u1=u;
		System.out.println(u1);
		return rep.save(u1);
	}

	@Override
	public Object loginUser(String emailorname, String password) {
		User u1=rep.findByEmailOrUsername(emailorname, emailorname);
		if (!(u1 instanceof User)) {
			try {
				throw new UserNotFoundException("user not found for given : " + emailorname);
			} catch (UserNotFoundException e) {
				return e.getMessage();
			}
		} else {
			if (u1.getPassword().equals(password)) {
				return u1;
			}
			try {
				throw new PasswordInvalidException("Invalid passowrd ");
			} catch (PasswordInvalidException e) {
				return e.getMessage();
			}

		}
	}

	@Override
	public String resetPassword(String email, String pwd, String conformpwd) {
		User update = rep.getByEmail(email);
		System.out.println(update);
		if (email.contains("@")) {
			if (update instanceof User) {
				if (pwd.equals(conformpwd)) {
					update.setPassword(conformpwd);
					rep.save(update);
					return "password update successfully";
				}
				try {
					throw new PasswordInvalidException("password mismatch " + " pwd :" + pwd +" conformpassword " + conformpwd);
				}catch (PasswordInvalidException e) {
					return e.getMessage();
				}
			}
			try {
				throw new UserNotFoundException("user not found for the given email : " + email);
			}catch(UserNotFoundException e) {
				return e.getMessage();
			}
		}
		return "enter vaild email exmaple -> abcd@gamil.com ";
	}

	@Override
	public Object getAllUser() {
		List<User> l1 = rep.findAll();
		if (l1.size() > 0) {
			return l1;
		}
		return "No Data in the Data Base";
	}

	@Override
	public Object forgotPasswordVerifyWithEmail(String email) {
		User verify = rep.getByEmail(email);
		int otp = new Random().nextInt(100000, 999999);
		if (!(verify instanceof User)) {

			try {
				throw new UserNotFoundException("user not found for given email : " + email);
			} catch (UserNotFoundException user) {
				return user.getMessage();
			}
		}
		mail.setFrom("lakshmivenkatesh9731@gmail.com");
		mail.setTo(email);
		mail.setSubject("From Medic World");
		mail.setText("One time password"+otp);
		send.send(mail);
		verify.setOtp(otp);
		rep.save(verify);
		return "otp send successfully";
	}

	@Override
	public String otpCrossVerficationWithEmail(String email, int otp) {
		User u5 = rep.getByEmail(email);
		if (u5 != null) {
			if (u5.getOtp() == otp) {
				return "sucess";
			}
		}
		try {
			throw new OtpMismatch("otp mismatch");
		} catch (OtpMismatch m) {
			return m.getMessage();
		}
	}

	@Override
	public Object sendOtptoEmail(String email) {
		 User user =rep.getByEmail(email);
		try {
			if(user!=null) {
				throw new UserAlreadyExistsException("user already exist by : "+email+" try diffrent email");
			}
		}catch (UserAlreadyExistsException e) {
			return e.getMessage();
		}
		int otp=new Random().nextInt(100000, 999999);
		mail.setFrom("lakshmivenkatesh9731@gmail.com");
		mail.setTo(email);
		mail.setText(" One time password "+otp);
		mail.setSubject("From Medic World");
		send.send(mail);
		temp.setOtp(otp);
		temp.setEmail(email);
		rep.save(temp);
		return "otp send sucessfully";
	}


}
