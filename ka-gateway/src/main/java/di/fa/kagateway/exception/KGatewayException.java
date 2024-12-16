package di.fa.kagateway.exception;

import di.fa.kacommon.response.ResponseStatus;
import org.springframework.http.HttpStatus;

public class KGatewayException extends ServiceException {

    public KGatewayException(HttpStatus statusCode, String errCode, String errMessage, Object... args){
        super(statusCode, null, null, errCode, errMessage, args);
    }

    public KGatewayException(HttpStatus statusCode, ResponseStatus status, Object... args){
        super(statusCode, null, null, status.getCode(), status.getMessage(), args);
    }

    public KGatewayException(HttpStatus statusCode, Throwable cause, ResponseStatus status, Object... args){
        super(statusCode, cause, null, status.getCode(), status.getMessage(), args);
    }

    public KGatewayException(HttpStatus statusCode, Object error, ResponseStatus status, Object... args){
        super(statusCode, null, error, status.getCode(), status.getMessage(), args);
    }

//    public KGatewayException(Set<? extends ConstraintViolation<?>> violations) {
//        super(HttpStatus.BAD_REQUEST,
//                null,
//                null,
//                ResponseStatus.INVALID_REQUEST_PARAMETER.getCode(),
//                violations != null ? ResponseStatus.INVALID_REQUEST_PARAMETER.getMessage() + renderText(violations) : null);
//    }
//
//    private static String renderText(Set<? extends ConstraintViolation<?>> violations) {
//        var text = violations.stream()
//                .map(v -> v == null ? "null" : String.format("'%s' %s", convertSnakeCase(v.getPropertyPath().toString()), v.getMessage()) + " |" )
//                .collect(Collectors.joining(" "));
//
//        return text.substring(0, text.length() - 2);
//    }

    private static String convertSnakeCase(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}