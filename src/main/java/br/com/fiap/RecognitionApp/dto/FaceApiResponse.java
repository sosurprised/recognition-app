package br.com.fiap.RecognitionApp.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class FaceApiResponse {
	private List<FaceApiInformationDto> FaceApiInfos;

	public List<FaceApiInformationDto> getFaceApiInfos() {
		return FaceApiInfos;
	}

	public void setFaceApiInfos(List<FaceApiInformationDto> faceApiInfos) {
		FaceApiInfos = faceApiInfos;
	}	
}
