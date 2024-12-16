package di.fa.kagateway.core.exception;

import di.fa.kagateway.core.response.ErrorData;
import di.fa.kagateway.core.response.SystemError;
import org.springframework.http.HttpStatus;

public class GreetingException extends BaseException {

    public GreetingException(HttpStatus statusCode, SystemError systemError, ErrorData errorData, Object... args) {
        super(statusCode, null, null, systemError.getCode(), systemError.getMessage(), errorData, args);
    }
}
