package com.example.eurekaconsumerfeign.feignFallback;

import com.example.eurekaconsumerfeign.feign_client.EurekaClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author O
 * @Description
 * @Date 2018/7/17 20:54
 * @Version 1.0
 */
@Component
public class FeignFallback implements EurekaClient {
	@Override
	public String consumer() {
		return "超时！";
	}

	@Override
	public String uploadFile(MultipartFile file) {
		return "超时!";
	}
}
