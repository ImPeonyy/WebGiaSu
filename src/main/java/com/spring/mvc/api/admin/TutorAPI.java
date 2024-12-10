package com.spring.mvc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.service.ITutorService;

@RestController(value = "tutorAPIOfAdmin")
public class TutorAPI {
	@Autowired
	private ITutorService tutorService;
	
	@PostMapping("/api/tutor")
	public TutorDTO createTutor(@RequestBody TutorDTO tutorDTO) {
		return tutorService.save(tutorDTO);
	}
	
	@PutMapping("/api/tutor")
	public TutorDTO updateTutor(@RequestBody TutorDTO updateTutor) {
		return tutorService.save(updateTutor);
	}
	
	@DeleteMapping("/api/tutor")
	public void deleteTutor(@RequestBody long[] ids) {
		tutorService.delete(ids);
	}
}
