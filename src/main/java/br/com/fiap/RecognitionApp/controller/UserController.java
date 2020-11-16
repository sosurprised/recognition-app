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

import br.com.fiap.RecognitionApp.model.User;
import br.com.fiap.RecognitionApp.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping()
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@PostMapping()
	public ResponseEntity<User> save(@Valid @RequestBody User user, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateTask(@Valid User user, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		User userToEdit = userRepository.getOne(user.getId());
		
		if(userToEdit==null) new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		userToEdit = user;
		
		userRepository.save(userToEdit);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id, RedirectAttributes redirect) {
		User user = userRepository.getOne(id);		
		if(user==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK); 
	}	
}
	