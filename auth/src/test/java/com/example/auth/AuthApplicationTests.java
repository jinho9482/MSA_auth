package com.example.auth;

import com.example.auth.domain.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@SpringBootTest
class AuthApplicationTests {
	@Autowired
	RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void checkNetwork() {
		SignUpRequest request = new SignUpRequest("jinho@naver.com", "1234", "wlshzz", LocalDate.now(), "male");
		restTemplate.postForEntity("http://192.168.0.18:8080/api/v1/auth/signup", request, Void.class);
	}

//	void sendToken() {
//		TeamRequest
//	}



}
