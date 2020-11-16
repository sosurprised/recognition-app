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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.RecognitionApp.model.Person;
import br.com.fiap.RecognitionApp.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping()
	public List<Person> getAll() {
		List<Person> persons = personRepository.findAll();
		return persons;
	}
	
	@PostMapping()
	public ResponseEntity<Person> save(@Valid @RequestBody Person person, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		personRepository.save(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Person> updateTask(@Valid Person person, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Person personToEdit = personRepository.getOne(person.getId());
		
		if(personToEdit==null) new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		personToEdit = person;
		
		personRepository.save(personToEdit);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Person> deleteUser(@PathVariable Long id, RedirectAttributes redirect) {
		Person person = personRepository.getOne(id);		
		if(person==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		personRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK); 
	}	
}
