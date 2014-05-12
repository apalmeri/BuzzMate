package edu.ycp.cs.cs496.locations.mobilecontrollers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import android.widget.Toast;

import edu.ycp.cs.cs496.buzzmateapp.MobileApplicationClient;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.json.JSON;



public class GetLocationList {
	public List<Location> getLocationList() throws ClientProtocolException, URISyntaxException, IOException {
		return makeGetRequest();
	}

	private List<Location> makeGetRequest() throws URISyntaxException, ClientProtocolException, IOException
	{
		// Create HTTP client
 		HttpClient client = new DefaultHttpClient();
		
		// Construct URI
		URI uri;
		String ip = "10.0.2.2";
		uri = URIUtils.createURI("http", ip , 8081, "/Locations", 
				    null, null);

		// Construct request
		HttpGet request = new HttpGet(uri);
		
		// Execute request
		HttpResponse response = client.execute(request);
		// Parse response
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// Copy the response body to a string
			HttpEntity entity = response.getEntity();
			
			
			// Parse JSON
			Location[] locations = JSON.getObjectMapper().readValue(entity.getContent(), Location[].class);
			return Arrays.asList(locations);
		} 
		
		// Return null if invalid response
		return null;		
	}

}
