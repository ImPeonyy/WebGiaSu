package com.spring.mvc.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.service.ITutorService;
import com.spring.mvc.util.MessageUtil;

@Controller(value = "tutorControllerOfAdmin")
public class TutorController {
	@Autowired
	private ITutorService tutorService;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private MessageUtil messageUtil;
	

	@RequestMapping(value = "/quan-tri/gia-su/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
	        					HttpServletRequest request) {
	    TutorDTO model = new TutorDTO();
	    model.setPage(page);
	    model.setLimit(limit);
	    ModelAndView mav = new ModelAndView("admin/tutor/list");
	    String url = "http://localhost:8888/api/tutors?page=" + page + "&limit=" + limit;
	    ResponseEntity<TutorDTO> response = restTemplate.getForEntity(url, TutorDTO.class);
	    model = response.getBody();
	    if (request.getParameter("message") != null) {
	        Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
	        mav.addObject("message", message.get("message"));
	        mav.addObject("alert", message.get("alert"));
	    }
	    mav.addObject("model", model);

	    return mav;
	}
	
	@RequestMapping(value = "/quan-tri/gia-su/search", method = RequestMethod.GET)
	public ModelAndView serchList(@RequestParam(required = false) String searchTerm, HttpServletRequest request) {
		TutorDTO model = new TutorDTO();
		ModelAndView mav = new ModelAndView("admin/tutor/search");
		String url = "http://localhost:8888/api/tutors/search?searchTerm=" + searchTerm;
		ResponseEntity<TutorDTO> response = restTemplate.getForEntity(url, TutorDTO.class);
	    model = response.getBody();
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/gia-su/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editTutor(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/tutor/edit");
		TutorDTO model = new TutorDTO();
		if (id != null) {
			String url = "http://localhost:8888/api/tutor?id=" + id;
			ResponseEntity<TutorDTO> response = restTemplate.getForEntity(url, TutorDTO.class);
		    model = response.getBody();
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("users", tutorService.getAll());
		mav.addObject("model", model);
		return mav;
	}
}
