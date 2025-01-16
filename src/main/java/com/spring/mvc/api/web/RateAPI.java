package com.spring.mvc.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.dto.RateDTO;
import com.spring.mvc.service.IRateService;

@RestController(value = "rateAPIOfWeb")
public class RateAPI {
	
	@Autowired
	private IRateService rateService;
	
	@GetMapping("/api/rates")
    public ResponseEntity<RateDTO> getRates(@RequestParam(value = "id", required = false) Long id) {
        RateDTO model = new RateDTO();

        model.setListResult(rateService.findAllByTutorId(id));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

	@PostMapping("/api/rate")
	public RateDTO createRate(@RequestBody RateDTO rateDTO) {
		return rateService.save(rateDTO);
	}
}
