package br.com.fiap.RecognitionApp.service;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.fiap.RecognitionApp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private long expiration = 86400000;
	private String secret = "8794646574619DADWXWC";
	
	public String gerarToken(Authentication authentication) {
		User logado = (User) authentication.getPrincipal();
		Date today = new Date();
		Date dateExpiration = new Date(today.getTime() + expiration);
		return Jwts.builder()
			   .setIssuer("API da vida")
			   .setSubject(logado.getId().toString())
			   .setIssuedAt(today)
			   .setExpiration(dateExpiration )
			   .signWith(SignatureAlgorithm.HS256, secret)
			   .compact();
			   
	}
	public boolean isTokenValid(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
		
		
	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret)
		.parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
