package br.com.fiap.RecognitionApp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.RecognitionApp.model.User;
import br.com.fiap.RecognitionApp.repository.UserRepository;
import br.com.fiap.RecognitionApp.service.TokenService;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository repository;
	
	public AuthenticationTokenFilter (TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recoverToken(request);
		boolean valido = tokenService.isTokenValid(token);
		
		System.out.println(valido);
		if(valido) {
			authenticateUser(token);
		}
		System.out.println(token);
		filterChain.doFilter(request, response);
	}
	private void authenticateUser(String token) {
		// TODO Auto-generated method stub
		Long idUser = tokenService.getIdUser(token);
		User user = repository.findById(idUser).get();
		UsernamePasswordAuthenticationToken	  authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) { return null; }
		return token.substring(7, token.length());
		
	}

}
