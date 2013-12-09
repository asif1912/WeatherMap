package com.test.weather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherTest {
	
	public static void main(String args[]) {
		
		
		
		try {
			
			String wundergroundURL = "http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/94117.json";
			
			JsonNode node = new ObjectMapper().readTree(new URL(wundergroundURL));
			
			JsonNode errorNode = node.get("response").get("error");
			
			if(null == errorNode) {
				
				JsonNode currentObservationNode = node.get("current_observation");
				
				JsonNode observationLocationNode = currentObservationNode.get("observation_location");
				
				String city  = observationLocationNode.get("city").asText();
				
				String state = observationLocationNode.get("state").asText();
				
				float tempInFarenheit = (float)currentObservationNode.get("temp_f").asDouble();
				
				System.out.println(city + " " + state + " " + tempInFarenheit);
			}
			else {
				System.out.println("Invalid Zip Code");
			}
			
			
		} catch (JsonProcessingException | MalformedURLException e) {
			
		} catch(IOException e) {
			
		} catch(Exception e) {
			
		}
		
		
	}

}
