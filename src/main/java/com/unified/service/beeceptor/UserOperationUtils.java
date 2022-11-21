package com.unified.service.beeceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import com.unified.service.common.Constants;
import com.unified.service.exception.UnifiedServiceException;
import com.unified.service.model.User;

/**
 * User related utility methods
 * 
 * @author Abhishek
 *
 */
public class UserOperationUtils {

	public UserOperationUtils() {
		
	}
	
	/**
	 * Validate user from Beeceptor
	 * 
	 * @param user user
	 * @param env env
	 * @throws Exception exception
	 */
	public static boolean validateUser(User user, Environment env) throws Exception {
		String baseUrl = System.getenv(Constants.BASE_URL);//env.getProperty("beeceptor.baseUrl");
		if (StringUtils.isBlank(baseUrl)) {
			throw new UnifiedServiceException("Error Occurred. Empty Base URL");
		}
		String endpointPath = env.getProperty("beeceptor.validateEndpoint");
		String protocol = env.getProperty("http.protocol");
		return HttpPostClient.validateUser(user, protocol, baseUrl, endpointPath);
	}

}
