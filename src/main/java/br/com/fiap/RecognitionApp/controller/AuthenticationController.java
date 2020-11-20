package br.com.fiap.RecognitionApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.RecognitionApp.dto.LoginDto;
import br.com.fiap.RecognitionApp.dto.TokenDto;
import br.com.fiap.RecognitionApp.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenServices;
	
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody @Valid LoginDto user) {
		UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getSenha());
		
		try {
			 Authentication authentication = authManager.authenticate(login);
			 String token = tokenServices.gerarToken(authentication);
				return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().build();
		}
	}
}
