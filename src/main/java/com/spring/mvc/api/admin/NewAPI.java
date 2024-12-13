package com.spring.mvc.api.admin;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.RegisterDTO;
import com.spring.mvc.entity.RoleEntity;
import com.spring.mvc.entity.UserEntity;
import com.spring.mvc.repository.RoleRepository;
import com.spring.mvc.repository.UserRepository;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@PostMapping("/api/register")
	public ResponseEntity<?> register(@RequestBody RegisterDTO regisDTO) {	
		ModelAndView mav = new ModelAndView("register");
		 if (userRepository.existsByUserName(regisDTO.getUsername())) {
			 mav.addObject("message", userRepository.existsByUserName(regisDTO.getUsername()));
			 return ResponseEntity.status(HttpStatus.CONFLICT)
                     .body("Username already exists.");
	        }
		UserEntity user = new UserEntity();
		user.setUserName(regisDTO.getUsername());
		String password = passwordEncoder().encode(regisDTO.getPassword());
		user.setPassword(password);
		user.setStatus(1);
		user.setFullName(regisDTO.getFullName());
		if(roleRepository.findByCode("USER").isPresent()) {
			RoleEntity roles = roleRepository.findByCode("USER").get();
			user.setRoles(Collections.singletonList(roles));
		}
		userRepository.save(user);
		mav.addObject("message", userRepository.existsByUserName(regisDTO.getUsername()));
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
}
