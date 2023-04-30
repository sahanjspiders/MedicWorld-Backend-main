package com.jsp.medicworld.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.medicworld.dao.HomeDao;
import com.jsp.medicworld.entity.Home;
import com.jsp.medicworld.service.HomeService;

@Service
public class HomeServiceImplementation implements HomeService{

	@Autowired
	private HomeDao homerep;
	@Override
	public Home uploadImage(MultipartFile file, String p1, String p2, String p3) throws IOException {
		String substring=file.getOriginalFilename().substring(0, (file.getOriginalFilename().length()-4));
		System.out.println(substring);
		Home temp=Home.builder().name(file.getOriginalFilename())
		.type(file.getContentType())
		.image(file.getBytes())
		.heading(substring)
		.p1(p1).p2(p2).p3(p3).build();
		return homerep.save(temp);
	}
	@Override
	public List<Home> getAllImage() {
		return homerep.findAll();
	}

	
}
