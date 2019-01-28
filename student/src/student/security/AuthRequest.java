package student.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.client.RestTemplate;

public final class AuthRequest {
	
	public static <T> ResponseEntity<T> getResponse(String auth, String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", auth);
		restTemplate.setErrorHandler(new MyResponseErrorHandler());
		
		HttpEntity<String> request2 = new HttpEntity<String>(headers);
	    ResponseEntity<T> response = restTemplate.exchange(url, method, request2, responseType, uriVariables);
	    return response;
	}
}
