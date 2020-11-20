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
	
	@NotBlank()
	@Column(name="url")
	private String url;
	
	@OneToMany(mappedBy="image")
	Set<Measures> measures;
	
	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Measures> getMeasures() {
		return measures;
	}
	public void setMeasures(Set<Measures> measures) {
		this.measures = measures;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
