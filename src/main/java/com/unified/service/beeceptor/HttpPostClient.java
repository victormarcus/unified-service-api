package com.unified.service.beeceptor;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unified.service.common.Constants;
import com.unified.service.model.User;
import com.unified.service.utils.HttpUtils;
import com.unified.service.utils.JsonUtils;

public class HttpPostClient {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static boolean validateUser(User user, String protocol, String basePath, String endpointPath) throws Exception {
		try {
			String requestUrl = HttpUtils.generateRequestUrl(protocol, basePath, endpointPath);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			CloseableHttpResponse response = httpClient.execute(
					HttpUtils.getPostRequest(Constants.APPLICATION_JSON, requestUrl, mapper.writeValueAsString(user)));
			
			if (response.getStatusLine().getStatusCode() == 200) {
				String resBody = HttpUtils.getResponseAsString(response);
				String status = JsonUtils.getValueOfNode(mapper.readTree(resBody), "status");
				if (status.equalsIgnoreCase("VALID")) {
					return true;
				}
			}
			httpClient.close();
		} catch (IOException e) {
			throw e;
		}
		return false;
	}
}
