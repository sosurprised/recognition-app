package br.com.fiap.RecognitionApp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity(name="people")
public class Person {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="person_id")
	private Long id;
	
	@NotBlank()
	@Column(name="name")
	private String name;
	
	@NotBlank()
	@Column(name="surname")
	private String surname;
	
	@NotBlank()
	@Column(name="document")
	private String document;
	
	@NotBlank()
	@Column(name="cpf")
	private String cpf;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="person")
	Set<Image> images;
	
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
