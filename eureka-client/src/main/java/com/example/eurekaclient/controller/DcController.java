package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DcController {
	@Autowired
	DiscoveryClient discoveryClient;

	@GetMapping("/dc")
	public String dc() throws InterruptedException{
		String services = "Services: " + discoveryClient.getServices();
		System.out.println(services);
		return services;
	}

	@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
		System.out.println("bbbbb");
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		return file.getName();
	}

}