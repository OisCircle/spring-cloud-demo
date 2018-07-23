package com.example.eurekaconsumerfeign.feign_client;

import com.example.eurekaconsumerfeign.feignFallback.FeignFallback;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author O
 * @Description
 * @Date 2018/7/16 17:05
 * @Version 1.0
 */
@Component
@FeignClient(value = "eureka-client",fallback = FeignFallback.class)
public interface EurekaClient {
	@GetMapping("/dc")
	String consumer();

	@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String uploadFile(@RequestPart(value = "file") MultipartFile file);
}
