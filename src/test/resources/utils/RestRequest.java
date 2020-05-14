package utils;

import java.util.HashMap;
import java.util.Map.Entry;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class RestRequest {

	public static ClientResponse doGet(String url, HashMap<String, String> map) {

		ClientResponse response = null;

		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			Builder builder = webResource.cookie(null);

			for (Entry<String, String> entry : map.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
			
			response = builder.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return response;
	}

	public static ClientResponse doPost(String url, HashMap<String, String> map,String request) {

		ClientResponse response = null;

		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			Builder builder = webResource.cookie(null);
		
			for (Entry<String, String> entry : map.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
			
			response = builder.post(ClientResponse.class, request);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return response;
	}

}