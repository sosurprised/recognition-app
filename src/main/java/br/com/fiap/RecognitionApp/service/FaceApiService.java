package br.com.fiap.RecognitionApp.service;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.RecognitionApp.dto.FaceApiInformationDto;
import br.com.fiap.RecognitionApp.model.Measures;

@Service
public class FaceApiService {
	
	public Measures GetMesures(String url) {
		String subscription_Key = "8b5d12ba2bcb464c997ff4f8e3f600f3";
		String endpoint = "https://ps.cognitiveservices.azure.com/";
        Measures measures = new Measures();
        String formatedUrl =  String.format("{\"url\":\"%s\"}", url);
	    try
	    {
	    	HttpClient httpclient = HttpClientBuilder.create().build();
	
	        URIBuilder builder = new URIBuilder(endpoint + "/face/v1.0/detect");
	
	        // Request parameters. All of them are optional.
	        builder.setParameter("detectionModel", "detection_02");
	        builder.setParameter("returnFaceId", "true");
	
	        // Prepare the URI for the REST API call.
	        URI uri = builder.build();
	        HttpPost request = new HttpPost(uri);
	
	        // Request headers.
	        request.setHeader("Content-Type", "application/json");
	        request.setHeader("Ocp-Apim-Subscription-Key", subscription_Key);
	
	        // Request body.
	        StringEntity reqEntity = new StringEntity(formatedUrl);
	        request.setEntity(reqEntity);
	
	        // Execute the REST API call and get the response entity.
	        HttpResponse response = httpclient.execute(request);
	        HttpEntity entity = response.getEntity();
	        FaceApiInformationDto[] faceApiResponse;
	        if (entity != null) {	        
	            // Format and display the JSON response.
	            System.out.println("REST Response:\n");
	            String jsonString = EntityUtils.toString(entity);	          	         	           

                ObjectMapper mapper = new ObjectMapper();
                faceApiResponse = mapper.readValue(jsonString, FaceApiInformationDto[].class);
                System.out.println("face api response" + faceApiResponse[0].getFaceId());
                
                measures = new Measures(faceApiResponse[0].getFaceId(), 
                		faceApiResponse[0].getFaceRectangle().getTop(),
                		faceApiResponse[0].getFaceRectangle().getLeft(),
                		faceApiResponse[0].getFaceRectangle().getWidth(),
                		faceApiResponse[0].getFaceRectangle().getHeight());
	        }	       
	    }
	    catch (Exception e)
	    {
	        // Display error message.
	        System.out.println(e.getMessage());
	    }
	    return measures;
	}
}


