package br.com.fiap.RecognitionApp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity(name="images")
public class Image {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private Long id;
	
	@NotBlank()
	@Column(name="url")
	private String url;
	
	@ManyToOne(cascade =  { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = true)
	private Person person;

	public Image(@NotBlank String url, Person person) {
		super();
		this.url = url;
		this.person = person;
	}	
}
