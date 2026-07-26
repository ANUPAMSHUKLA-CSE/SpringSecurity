package com.AnupamSecurity.demo;

import com.AnupamSecurity.demo.Entities.User;
import com.AnupamSecurity.demo.Service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
     private JwtService jwtService;
	@Test
	void contextLoads() {
		User user = new User(4L,"anupam@gmail.com","anupam@2312","anupam");
		String token = jwtService.generateToken(user);
		System.out.println("Token="+token);

		Long id=jwtService.getUserIdFromToken(token);
		System.out.println("Id="+id);
	}

}
