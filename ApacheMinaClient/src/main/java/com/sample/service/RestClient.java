package com.sample.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	
	 public static String VerifyMD5(String fileName) {
			try {

				Client client = Client.create();
				
				WebResource webResource = client
				   .resource("http://localhost:8080/ApacheMinaRest/checkmd5/"+fileName);

				ClientResponse response = webResource.accept("application/json")
		                   .get(ClientResponse.class);

				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}

				String output = response.getEntity(String.class);
				
				return output;

			  } catch (Exception e) {

				e.printStackTrace();

			  }
			return null;

			}

}
