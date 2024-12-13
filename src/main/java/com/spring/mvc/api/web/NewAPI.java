package com.spring.mvc.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.dto.RateDTO;
import com.spring.mvc.service.IRateService;

@RestController(value = "newAPIOfWeb")
public class NewAPI {
	
	@Autowired
	private IRateService rateService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@PostMapping("/api/rate")
	public RateDTO createRate(@RequestBody RateDTO rateDTO) {
		return rateService.save(rateDTO);
	}
}
