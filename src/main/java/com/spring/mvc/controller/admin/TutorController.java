package com.spring.mvc.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.service.ITutorService;
import com.spring.mvc.util.MessageUtil;

@Controller(value = "tutorControllerOfAdmin")
public class TutorController {
	@Autowired
	private ITutorService tutorService;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/gia-su/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, 
								 @RequestParam("limit") int limit, HttpServletRequest request) {
		TutorDTO model = new TutorDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/tutor/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(tutorService.findAll(pageable));
		model.setTotalItem(tutorService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/gia-su/danh-sach/search", method = RequestMethod.GET)
	public ModelAndView serchList(@RequestParam("page") int page, 
								 @RequestParam("limit") int limit, 
								 @RequestParam("freeText") String freeText, HttpServletRequest request) {
		TutorDTO model = new TutorDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/tutor/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(tutorService.findAll(pageable, freeText));
		model.setTotalItem(tutorService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
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
			model = tutorService.findById(id);
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
