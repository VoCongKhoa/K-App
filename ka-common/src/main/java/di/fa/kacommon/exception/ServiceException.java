package di.fa.kacommon.exception;

import di.fa.kacommon.response.SystemError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final Object error;
    private final SystemError systemError;

    public ServiceException(HttpStatus statusCode, Throwable cause, Object error, String errCode, String errMessage, SystemError systemError, Object... args) {
        super(String.format(errMessage, args), cause);
        this.statusCode = statusCode;
        this.errorCode = errCode;
        this.error = error;
        this.systemError = systemError;
    }

    public ServiceException(HttpStatus statusCode, Throwable cause, Object error, String errCode, String errMessage, Object... args) {
        super(String.format(errMessage, args), cause);
        this.statusCode = statusCode;
        this.errorCode = errCode;
        this.error = error;
        this.systemError = null;
    }

    public ServiceException(HttpStatus statusCode, SystemError systemError) {
        super(systemError.getMsg());
        this.statusCode = statusCode;
        this.errorCode = systemError.getCode();
        this.error = systemError.getError();
        this.systemError = systemError;
    }
}
