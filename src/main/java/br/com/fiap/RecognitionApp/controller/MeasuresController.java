package br.com.fiap.RecognitionApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.RecognitionApp.model.Measures;
import br.com.fiap.RecognitionApp.repository.MeasuresRepository;

@RestController
@RequestMapping("/measures")
public class MeasuresController {
	@Autowired
	private MeasuresRepository measuresRepository;
	
	@GetMapping()
	public List<Measures> getAll() {
		List<Measures> measures = measuresRepository.findAll();
		return measures;
	}
}
