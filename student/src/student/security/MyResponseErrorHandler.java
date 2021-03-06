package student.security;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class MyResponseErrorHandler implements ResponseErrorHandler {
	
    @Override
    public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
        if (clienthttpresponse.getStatusCode() == HttpStatus.UNAUTHORIZED || clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {

        }
    }

    @Override
    public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {

        if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {

            if (clienthttpresponse.getStatusCode() == HttpStatus.UNAUTHORIZED || clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                return true;
            }
        }
        return false;
    }
}