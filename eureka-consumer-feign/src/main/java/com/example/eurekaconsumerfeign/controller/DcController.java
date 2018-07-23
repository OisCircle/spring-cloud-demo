package com.example.eurekaconsumerfeign.controller;

import com.example.eurekaconsumerfeign.feign_client.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//feign调用
@RestController
public class DcController {
	@Autowired
	EurekaClient eurekaClient;

	@GetMapping("/consumer")
	public String dc() throws Exception{
		return eurekaClient.consumer();
	}

	@PostMapping("/uploadFileFegin")
	public String upload(@RequestPart(value = "file") MultipartFile file) {
		System.out.println("aaaaa");
		return eurekaClient.uploadFile(file);
	}

}