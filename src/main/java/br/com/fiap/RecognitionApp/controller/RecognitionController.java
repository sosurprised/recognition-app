package br.com.fiap.RecognitionApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.RecognitionApp.dto.RecognitionDto;
import br.com.fiap.RecognitionApp.model.Image;
import br.com.fiap.RecognitionApp.model.Measures;
import br.com.fiap.RecognitionApp.model.Person;
import br.com.fiap.RecognitionApp.model.User;
import br.com.fiap.RecognitionApp.repository.ImageRepository;
import br.com.fiap.RecognitionApp.repository.MeasuresRepository;
import br.com.fiap.RecognitionApp.repository.PersonRepository;
import br.com.fiap.RecognitionApp.service.FaceApiService;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {
	@Autowired
	private FaceApiService faceApiService;
	
	@Autowired
	private MeasuresRepository measuresRespository;
	
	@Autowired
	private ImageRepository imageRespository;
	
	@Autowired
	private PersonRepository personRespository;
	
	//to test url="https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg" person_id=1
	@PostMapping()
	public ResponseEntity<Measures> Recognize(@RequestBody RecognitionDto request) {
		if(request == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Person person = personRespository.getOne(request.getPerson_id());		
		if(person == null || request.getUrl() == null || request.getUrl() == "")
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		Image image = new Image (request.getUrl(), person);
		imageRespository.save(image);
		Measures measures = faceApiService.GetMesures(request.getUrl());
		if (measures != null) {
			measures.setImage(image);
			measuresRespository.save(measures);
			return new ResponseEntity<>(HttpStatus.OK);
		}			
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}
