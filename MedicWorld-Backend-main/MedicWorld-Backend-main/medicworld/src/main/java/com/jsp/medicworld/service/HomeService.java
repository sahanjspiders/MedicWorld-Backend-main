package com.jsp.medicworld.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.medicworld.entity.Home;

public interface HomeService {
	
	Home uploadImage(MultipartFile file,String p1,String p2,String p3) throws IOException ;
	List<Home> getAllImage();

}
