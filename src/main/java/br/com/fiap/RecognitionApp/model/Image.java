package br.com.fiap.RecognitionApp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity(name="images")
public class Image {
	public Image() {}
	public Image(@NotBlank String url, Person person) {
		super();
		this.url = url;
		this.person = person;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private Long image_id;
	
	@Column(name="url")
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@OneToMany(mappedBy="image")
	Set<Measure> measures;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
