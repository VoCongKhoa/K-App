package di.fa.kagateway.core.exception;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kagateway.core.response.ErrorData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

import static di.fa.kagateway.core.response.SystemError.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected SystemResponse<Object> handleBaseException(BaseException ex, NativeWebRequest nativeWebRequest) {
        var request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        var status = ex.getStatusCode();
        var response = new SystemResponse<>();
        var errorData = ErrorData.builder()
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getMessage())
                .errorObject(ex.getError())
                .cause(Objects.nonNull(ex.getCause()) ? ex.getCause().toString() : null)
                .timestamp(LocalDateTime.now())
                .originService(ErrorData.selfService)
                .build();
        response.asFailed(SystemResponse.FAILURE, errorData);
        return response;
    }

    @ExceptionHandler
    protected SystemResponse<Object> handleOtherException(Exception ex, NativeWebRequest nativeWebRequest) {
        var request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = new SystemResponse<>();
        var errorData = ErrorData.builder()
                .errorCode(INTERNAL_SERVER_ERROR.getCode())
                .errorMessage(ex.getMessage())
                .cause(Objects.nonNull(ex.getCause()) ? ex.getCause().toString() : null)
                .timestamp(LocalDateTime.now())
                .originService(ErrorData.selfService)
                .build();
        response.asFailed(SystemResponse.FAILURE, errorData);
        return response;
    }
}
