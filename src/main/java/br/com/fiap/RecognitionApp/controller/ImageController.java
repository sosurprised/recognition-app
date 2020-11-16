package br.com.fiap.RecognitionApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.RecognitionApp.dto.CreateImageDto;
import br.com.fiap.RecognitionApp.model.Image;
import br.com.fiap.RecognitionApp.model.Person;
import br.com.fiap.RecognitionApp.repository.ImageRepository;
import br.com.fiap.RecognitionApp.repository.PersonRepository;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping()
	public List<Image> getAll() {
		List<Image> images = imageRepository.findAll();
		return images;
	}
	
	@PostMapping()
	public ResponseEntity<Image> save(@Valid @RequestBody CreateImageDto request, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Person person = personRepository.getOne(request.getPersonId());		
		if(person==null) new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Image image = new Image(request.getUrl(), person);
		imageRepository.save(image);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Image> deleteImage(@PathVariable Long id, RedirectAttributes redirect) {
		Image image = imageRepository.getOne(id);		
		if(image==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		personRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
