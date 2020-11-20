package br.com.fiap.RecognitionApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import br.com.fiap.RecognitionApp.dto.RecognitionByUrlDto;
import br.com.fiap.RecognitionApp.dto.RecognitionDto;
import br.com.fiap.RecognitionApp.model.Image;
import br.com.fiap.RecognitionApp.model.Measure;
import br.com.fiap.RecognitionApp.model.Person;
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
	public ResponseEntity<Measure> Recognize(@RequestBody RecognitionDto request) {
		if(request == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Person person = personRespository.getOne(request.getPerson_id());		
		if(person == null || request.getUrl() == null || request.getUrl() == "")
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		Image image = new Image (request.getUrl(), person);
		imageRespository.save(image);
		Measure measure = faceApiService.GetMesures(request.getUrl());
		measure.setImage(image);
		if (measure != null) {
			measuresRespository.save(measure);
			return new ResponseEntity<>(HttpStatus.OK);
		}	
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@PostMapping("byUrl")
	public Person RecognizeByUrl(@RequestBody RecognitionByUrlDto request) {
		Person person = new Person();
		String url = request.getUrl();
		if(url == null || url == "")
			return null;
		Measure measureFromApi = faceApiService.GetMesures(url);
		java.util.List<Person> people = personRespository.findAll();
		java.util.List<Measure> allMeasures = measuresRespository.findAll();
		for(Measure thisMeasure : allMeasures ) {
			if(Measure.IsEqual(thisMeasure, measureFromApi)) {
				person = thisMeasure.getImage().getPerson();
			}
		}

		return person;	
		
	}	
}
