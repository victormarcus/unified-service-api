package com.unified.service.beeceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unified.service.common.Constants;
import com.unified.service.model.User;
import com.unified.service.utils.HttpUtils;
import com.unified.service.utils.JsonUtils;

public class HttpPostClient {

	private static final CloseableHttpClient httpClient = HttpClients.createDefault();

	private static final ObjectMapper mapper = new ObjectMapper();

	public static boolean validateUser(User user, String protocol, String basePath, String endpointPath) throws Exception {
		try {
			String requestUrl = HttpUtils.generateRequestUrl(protocol, basePath, endpointPath);
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
