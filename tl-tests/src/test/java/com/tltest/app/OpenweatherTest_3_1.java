package com.tltest.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OpenweatherTest_3_1 {

	@Test
	public void historicalWeatherAPIReq() throws MalformedURLException, IOException, ParseException {
		
		URL url = new URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=47.890781&lon=56.638771&dt=1611311111&appid=355c4c2fe75d20a157c461d7159b3df2");
		var connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);
		
		connection.connect();
		
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
		
	    String inline = "";
	    Scanner scanner = new Scanner(url.openStream());
	  
	    while (scanner.hasNext()) {
	       inline += scanner.nextLine();
	    }
	    
	    scanner.close();

	    JSONParser parser = new JSONParser();
	        
    	var response = (JSONObject)parser.parse(inline);
    	var current = (JSONObject)response.get("current");
    	
        var kelvin = current.get("temp");
        
        var celsius = Math.round(Double.parseDouble(kelvin.toString()) - 273.15);
        System.out.println(celsius);
            
		connection.disconnect();
	}
}
