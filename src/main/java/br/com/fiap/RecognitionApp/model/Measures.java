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

@Entity(name="measure")
public class Measures {
	public Measures () {}	
	public Measures(String faceId, int top, int left, int width, int height) {
		super();
		this.faceId = faceId;
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="measures_id")
	private Long measures_id;	
	
	@Column(name="face_id")
	private String faceId;
	
	@Column(name="top")
	private int top;
	
	@Column(name="left")
	private int left;
	
	@Column(name="width")
	private int width;
	
	@Column(name="height")
	private int height;
	
	@ManyToOne
	@JoinColumn(name = "image_id", nullable = false)
	private Image image;
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
