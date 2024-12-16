package di.fa.kagateway.core.exception;

import di.fa.kagateway.core.response.ErrorData;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final Object error;
    private final ErrorData errorData;

    public BaseException(HttpStatus statusCode, Throwable cause, Object error, String errCode, String errMessage, ErrorData errorData, Object... args) {
        super(String.format(errMessage, args), cause);
        this.statusCode = statusCode;
        this.errorCode = errCode;
        this.error = error;
        this.errorData = errorData;
    }
}
