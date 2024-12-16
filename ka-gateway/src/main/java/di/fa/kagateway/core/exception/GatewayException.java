package di.fa.kagateway.core.exception;

import di.fa.kagateway.core.response.ErrorData;
import di.fa.kagateway.core.response.SystemError;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class GatewayException extends BaseException {

    public GatewayException(HttpStatus statusCode, SystemError systemError, ErrorData errorData, Object... args) {
        super(statusCode, null, null, systemError.getCode(), systemError.getMessage(), null, args);
    }

    public GatewayException(Set<? extends ConstraintViolation<?>> violations) {
        super(HttpStatus.BAD_REQUEST,
                null,
                null,
                SystemError.INVALID_REQUEST_PARAMETER.getCode(),
                violations != null ? SystemError.INVALID_REQUEST_PARAMETER.getMessage() + renderText(violations) : null,
                null);
    }

    private static String renderText(Set<? extends ConstraintViolation<?>> violations) {
        var text = violations.stream()
                .map(v -> v == null ? "null" : String.format("'%s' %s", convertSnakeCase(v.getPropertyPath().toString()), v.getMessage()) + " |")
                .collect(Collectors.joining(" "));

        return text.substring(0, text.length() - 2);
    }

    private static String convertSnakeCase(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
