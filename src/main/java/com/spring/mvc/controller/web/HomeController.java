package com.spring.mvc.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.RateDTO;
import com.spring.mvc.dto.RegisterDTO;
import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.service.IRateService;
import com.spring.mvc.service.ITutorService;
import com.spring.mvc.util.MessageUtil;
import com.spring.mvc.util.SecurityUtils;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private ITutorService tutorService;
	
	@Autowired
	private IRateService rateService;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(required = false) String searchTerm) {
		TutorDTO model = new TutorDTO();
		ModelAndView mav = new ModelAndView("web/home");
		if(searchTerm != null) {
			model.setListResult(tutorService.searchTutorsByTerm(searchTerm));
		} else {
			model.setListResult(tutorService.findAll());
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoTutor(@RequestParam(value = "userId", required = false) Long userId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/info");
		TutorDTO model = new TutorDTO();
		model.setUserID(SecurityUtils.getPrincipal().getId());
		model.setFullName(SecurityUtils.getPrincipal().getFullName());
		if (tutorService.findByUserId(userId) != null) {
			model = tutorService.findByUserId(userId);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "trang-chu/info", method = RequestMethod.GET)
	public ModelAndView tutorDetail(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/tutorDetail");
		TutorDTO model = new TutorDTO();
		RateDTO rate = new RateDTO();
		RateDTO rateSubmit = new RateDTO();
		
		String rateUrl = "http://localhost:8888/api/rates?id=" + id;
		String tutorUrl = "http://localhost:8888/api/tutor?id=" + id;
		ResponseEntity<TutorDTO> tutorResponse = restTemplate.getForEntity(tutorUrl, TutorDTO.class);
		ResponseEntity<RateDTO> rateResponse = restTemplate.getForEntity(rateUrl, RateDTO.class);
	    model = tutorResponse.getBody();
	    rate = rateResponse.getBody();
		
		mav.addObject("rateSubmit", rateSubmit);
		mav.addObject("rate", rate);
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public ModelAndView registrationPage() {
		RegisterDTO regisDTO = new RegisterDTO();
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("model", regisDTO);
		return mav;
	}
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
