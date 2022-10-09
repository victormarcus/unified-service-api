package com.unified.service.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;


public class HttpUtils {

	public HttpUtils() {

	}
	
	/**
	 * Generates request URL
	 * 
	 * @param protocol The protocol
	 * @param basePath The base path
	 * @param endpointPath the endpoint suffix
	 * @return the whole request URL
	 */
	public static String generateRequestUrl(String protocol, String basePath, String endpointPath) {
		return String.format("%s://%s%s", protocol, basePath, endpointPath);
	}
	
	/**
	 * Forms HTTP post request
	 * 
	 * @param dataFormat data format (json, xml)
	 * @param requestUrl request URL
	 * @param requestBody The post request body
	 * @return The Post Request
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static HttpPost getPostRequest(String dataFormat, String requestUrl, String requestBody) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(requestUrl);
		httpPost.setHeader("Content-Type", dataFormat);
		httpPost.setHeader("Accept", dataFormat);
		httpPost.setEntity(new StringEntity(requestBody));
		return httpPost;
	}

	/**
	 * Returns string response from HTTP response
	 * 
	 * @param response The http response obj
	 * @return string response
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getResponseAsString(CloseableHttpResponse response) throws ParseException, IOException {
		Optional<HttpEntity> responseEntity = Optional.ofNullable(response).map(CloseableHttpResponse::getEntity);
		if (responseEntity.isPresent()) {
			return EntityUtils.toString(responseEntity.get());
		}
		//TBD CHECK
		return "{}";
	}

}
