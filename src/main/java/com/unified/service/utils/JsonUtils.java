package com.unified.service.utils;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtils {

	public JsonUtils() {
		
	}
	
	/**
	 * Returns the value of top level JSON element
	 * 
	 * @param node The JSON node
	 * @param fieldName the field name whose value is required
	 * @return The value of field name
	 */
	public static String getValueOfNode(JsonNode node, String fieldName) {
		return node.get(fieldName).textValue();
	}

}
