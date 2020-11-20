package br.com.fiap.RecognitionApp.dto;

public class FaceApiInformationDto {
	private FaceRectangleDto faceRectangle;
	private String faceId;
	
	public FaceRectangleDto getFaceRectangle() {
		return faceRectangle;
	}
	public void setFaceRectangle(FaceRectangleDto faceRectangle) {
		this.faceRectangle = faceRectangle;
	}
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
}
