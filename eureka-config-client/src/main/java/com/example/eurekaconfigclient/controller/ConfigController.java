package com.example.eurekaconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author O
 * @Description
 * @Date 2018/7/17 9:59
 * @Version 1.0
 */
//这个注解很重要，否则加载不出配置信息
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {
	@Value("${dev-id}")
	private String devId;
	@Value("${dev-key}")
	private String devKey;

	@GetMapping
	public String getConfigInfo() {
		return "devId: " + devId + "    devKey:" + devKey;
	}
}
