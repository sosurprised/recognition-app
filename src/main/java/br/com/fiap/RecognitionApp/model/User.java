package br.com.fiap.RecognitionApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity(name="users")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@NotBlank
	@Column(name="name")
	private String name;
	
	@NotBlank
	@Column(name="surname")
	private String surname;
	
	@NotBlank
	@Email
	@Column(name="email")
	private String email;
	
	@NotBlank
	@Column(name="pass")
	private String pass;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}	
}
