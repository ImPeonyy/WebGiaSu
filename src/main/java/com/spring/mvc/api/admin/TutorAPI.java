package com.spring.mvc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.service.ITutorService;

@RestController(value = "tutorAPIOfAdmin")
public class TutorAPI {
	@Autowired
	private ITutorService tutorService;
	
    @GetMapping("/api/tutors")
    public ResponseEntity<TutorDTO> getTutorList(@RequestParam(value = "page", defaultValue = "1") int page, 
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        TutorDTO model = new TutorDTO();
        model.setPage(page);
        model.setLimit(limit);

        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(tutorService.findAll(pageable));
        model.setTotalItem(tutorService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    
    @GetMapping("/api/tutors/search")
    public ResponseEntity<TutorDTO> getTutorListSearch(@RequestParam(required = false) String searchTerm) {
        TutorDTO model = new TutorDTO();

        model.setListResult(tutorService.searchTutorsByTerm(searchTerm));
        
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    
    @GetMapping("/api/tutor")
    public ResponseEntity<TutorDTO> getTutorById(@RequestParam(value = "id", required = false) Long id) {
        TutorDTO model = new TutorDTO();

        model = tutorService.findById(id);
        
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
	
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
