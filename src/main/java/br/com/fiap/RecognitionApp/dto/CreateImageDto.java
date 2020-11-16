package br.com.fiap.RecognitionApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CreateImageDto {
	@NotBlank
	private String url;
	
	@NotNull
	private Long personId;

	public CreateImageDto(String url, Long personId) {
		super();
		this.url = url;
		this.personId = personId;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
  
}
