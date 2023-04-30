package com.jsp.medicworld.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.medicworld.dao.HomeDao;
import com.jsp.medicworld.entity.Home;
import com.jsp.medicworld.service.HomeService;

import jakarta.mail.internet.ContentType;

@RestController
@CrossOrigin
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	private HomeService ser;
	
//	@Autowired
//	private HomeDao homerep;
	
	@PostMapping("uploadimage")
	public ResponseEntity<Home> uploadImage(@RequestParam MultipartFile file,@RequestParam Map<String, String> map) throws IOException {
	return ResponseEntity.status(HttpStatus.OK).body(ser.uploadImage(file, map.get("p1"), map.get("p2"), map.get("p3")));
	}
	
	@GetMapping("getallimage")
	public ResponseEntity<List<Home>> downloadAllImage()
	{
		return ResponseEntity.status(HttpStatus.OK).body(ser.getAllImage());
	}
		
//	@PutMapping("update/{id}")
//	public ResponseEntity<Home> update(@PathVariable int id, @RequestParam MultipartFile file) throws IOException {
//		Home temp = homerep.findById(id).orElse(null);
//		if (temp != null) {
//			temp.setImage(file.getBytes());
//			temp.setType(file.getContentType());
//		}
//		return ResponseEntity.status(200).body(homerep.save(temp));
//	}

}
