package di.fa.kaauth.core.exception;

import di.fa.kacommon.exception.ServiceException;
import di.fa.kacommon.response.ResponseStatus;
import di.fa.kacommon.response.SystemError;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class AccountException extends ServiceException {

    public AccountException(HttpStatus statusCode, String errCode, String errMessage, SystemError systemError, Object... args){
        super(statusCode, null, null, errCode, errMessage, systemError, args);
    }

    public AccountException(HttpStatus statusCode, String errCode, String errMessage, Object... args){
        super(statusCode, null, null, errCode, errMessage, args);
    }

    public AccountException(HttpStatus statusCode, ResponseStatus status, SystemError systemError, Object... args){
        super(statusCode, null, null, status.getCode(), status.getMessage(), systemError, args);
    }

    public AccountException(HttpStatus statusCode, ResponseStatus status, Object... args){
        super(statusCode, null, null, status.getCode(), status.getMessage(), args);
    }

    public AccountException(HttpStatus statusCode, Throwable cause, ResponseStatus status, Object... args){
        super(statusCode, cause, null, status.getCode(), status.getMessage(), args);
    }

    public AccountException(HttpStatus statusCode, Object error, ResponseStatus status, Object... args){
        super(statusCode, null, error, status.getCode(), status.getMessage(), args);
    }

    public AccountException(HttpStatus statusCode, Object error, String errCode, String errMessage, Object... args){
        super(statusCode, null, error, errCode, errMessage, args);
    }

    public AccountException(HttpStatus statusCode, SystemError systemError){
        super(statusCode, systemError);
    }

    public AccountException(Set<? extends ConstraintViolation<?>> violations) {
        super(HttpStatus.BAD_REQUEST,
                null,
                null,
                ResponseStatus.INVALID_REQUEST_PARAMETER.getCode(),
                violations != null ? ResponseStatus.INVALID_REQUEST_PARAMETER.getMessage() + renderText(violations) : null);
    }

    private static String renderText(Set<? extends ConstraintViolation<?>> violations) {
        var text = violations.stream()
                .map(v -> v == null ? "null" : String.format("'%s' %s", convertSnakeCase(v.getPropertyPath().toString()), v.getMessage()) + " |" )
                .collect(Collectors.joining(" "));

        return text.substring(0, text.length() - 2);
    }

    private static String convertSnakeCase(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
