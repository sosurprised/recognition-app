package br.com.fiap.RecognitionApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="measures")
public class Measure {
	public Measure () {}	
	public Measure(String faceId, Integer top, Integer left, Integer width, Integer height) {
		super();
		this.faceId = faceId;
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="measures_id")
	private Long id;	
	
	@Column(name="face_id")
	private String faceId;
	
	@Column(name="top")
	private Integer top;
	
	@Column(name="leftside")
	private Integer left;
	
	@Column(name="width")
	private Integer width;
	
	@Column(name="height")
	private Integer height;
	
	@Column(name="url")
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
