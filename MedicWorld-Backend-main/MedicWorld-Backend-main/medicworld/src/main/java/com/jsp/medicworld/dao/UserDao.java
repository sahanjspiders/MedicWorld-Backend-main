package com.jsp.medicworld.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.jsp.medicworld.entity.User;


public interface UserDao extends JpaRepository<User, Integer> {

	@Query(value = "select * from user where email=?1 or phone=?2", nativeQuery = true)
	User findByEmailOrPhone(String email,long phone);

//	@Query(value = "select * from user where email=?1 or username=?2", nativeQuery = true)
	User findByEmailOrUsername(String email,String name);
	
	@Query(value="select * from user where email=?1",nativeQuery = true)
	User getByEmail(String email);
	
//	@Query(value = "select * from user where phone=?1",nativeQuery = true)
//	String getByPhoneNumber(long phone);
}
