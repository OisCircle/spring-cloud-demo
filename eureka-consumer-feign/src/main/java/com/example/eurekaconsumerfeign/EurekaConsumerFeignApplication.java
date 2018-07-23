package com.example.eurekaconsumerfeign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class EurekaConsumerFeignApplication {
	//feign文件上传需要加载的bean
	@Bean
	public Encoder feignFormEncoder() {
		return new SpringFormEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerFeignApplication.class, args);
	}
}
