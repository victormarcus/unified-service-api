package com.unified.service.beeceptor;

import com.unified.service.model.UserModel;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unified.service.common.Constants;
import com.unified.service.utils.HttpUtils;
import com.unified.service.utils.JsonUtils;

public class HttpPostClient {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final String SO_TIMEOUT = "SO_TIMEOUT";
	
	private static final String CO_TIMEOUT = "CO_TIMEOUT";
	
	private static final String CO_REQUEST_TIMEOUT = "CO_REQUEST_TIMEOUT";
	
	private static final String VALID = "VALID";
	
	private static final String STATUS = "status";
	
	public static boolean validateUser(UserModel userModel, String protocol, String basePath, String endpointPath) throws Exception {
		try {
			String requestUrl = HttpUtils.generateRequestUrl(protocol, basePath, endpointPath);
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(getRequestConfig()).build();
			
			CloseableHttpResponse response = httpClient.execute(
					HttpUtils.getPostRequest(Constants.APPLICATION_JSON, requestUrl, mapper.writeValueAsString(userModel)));
			
			if (response.getStatusLine().getStatusCode() == 200) {
				String resBody = HttpUtils.getResponseAsString(response);
				String status = JsonUtils.getValueOfNode(mapper.readTree(resBody), STATUS);
				if (status.equalsIgnoreCase(VALID)) {
					return true;
				}
			}
			httpClient.close();
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
	
	/*
	 * 1. Connection request timeout => how long a client should wait for a connection from the connection pool. (Connection milne me)
	 *    For example, if all connections are busy executing an HTTP request, subsequent connection requests will wait until a connection becomes available.
	 * 2. Timeout for connecting a server => This value defines how long the HttpClient should wait when trying to connect a server. (Connect hone me)
	 * 3. Socket timeout => the maximum interval between consequent network packets. (Response aane me)
	 */
	public static RequestConfig getRequestConfig() {
		
	    RequestConfig requestConfig = RequestConfig.custom()
	    		  // time to get connection
	    	      .setConnectionRequestTimeout(HttpUtils.getHttpTimeoutEnvVariable(CO_REQUEST_TIMEOUT))
	    	      // time to connect to server
	    	      .setConnectTimeout(HttpUtils.getHttpTimeoutEnvVariable(CO_TIMEOUT))
	    	      // time to get response from server
	    	      .setSocketTimeout(HttpUtils.getHttpTimeoutEnvVariable(SO_TIMEOUT))
	    	      .build();
		return requestConfig;
	}
	
	
}
