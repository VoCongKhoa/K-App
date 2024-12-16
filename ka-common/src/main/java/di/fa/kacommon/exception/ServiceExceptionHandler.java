package di.fa.kacommon.exception;//package k.sls.kcommonservice.core.exception;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import feign.FeignException;
//import jakarta.servlet.http.HttpServletRequest;
//import k.sls.kcommonservice.payload.response.SystemError;
//import k.sls.kcommonservice.payload.response.SystemResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Slf4j
//@ControllerAdvice
//@RequiredArgsConstructor
//public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
//
//
//    @Value("${info.app.name}")
//    private String appName;
//
//    @Value("${info.app.version}")
//    private String appVersion;
//
//    @ExceptionHandler(ServiceException.class)
//    protected ResponseEntity<Object> handleControlledException(ServiceException ex, NativeWebRequest request) {
//        var req = request.getNativeRequest(HttpServletRequest.class);
//        var status = ex.getStatusCode();
//        var response = SystemResponse.builder().build();
//
//        String errorType = "";
//        String errorKey1 = "";
//        String errorKey2 = "";
//        String errorKey3 = "";
//        String errorKey4 = "";
//
//        try {
//            var mapper = new ObjectMapper();
//            var systemError = mapper.convertValue(ex.getSystemError(), SystemError.class);
//
//            if (null != systemError) {
////                errorType = systemError.getErrorType();
////                errorKey1 = systemError.getErrorKey1();
////                errorKey2 = systemError.getErrorKey2();
////                errorKey3 = systemError.getErrorKey3();
////                errorKey4 = systemError.getErrorKey4();
//            }
//        } catch (Exception iex) {
//        }
//
//        response.asFail(SystemResponse.FAILURE, SystemError.builder()
//                .code(ex.getErrorCode())
//                .msg(ex.getMessage())
//                .error(ex.getError())
//                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
//                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
//                .service(appName + ":" + appVersion)
////                .traceId(breadCrumbService.getTraceId())
////                .contextPath(req != null && req.getMethod() != null && req.getRequestURI() != null ? "[" + req.getMethod() + "] " + req.getRequestURI() : null)
////                .errorType(errorType)
////                .errorKey1(errorKey1)
////                .errorKey2(errorKey2)
////                .errorKey3(errorKey3)
////                .errorKey4(errorKey4)
//                .build());
//
//        this.logerror(ex.getErrorCode(), status, ex);
//        return handleExceptionInternal(ex, response, null, status, request);
////        return handleExceptionInternal(ex, response, breadCrumbService.getTracingHeader(), status, request);
//    }
//
//    @ExceptionHandler(FeignException.class)
//    public ResponseEntity<Object> handleFeignStatusException(FeignException ex, NativeWebRequest request) {
//        var mapper = new ObjectMapper();
//        var response = SystemResponse.builder().build();
//        var status = HttpStatus.resolve(ex.status());
//        var responseException = ex.contentUTF8();
//        var jsonException = new Object();
//        var current = LocalDateTime.now();
//
//        try {
//            //exception from dependency services bypass it to the caller
//            status = status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
//            jsonException = mapper.readValue(responseException, Object.class);
//            var errorResponse = mapper.readValue(responseException, SystemResponse.class);
//            var errorData = mapper.convertValue(errorResponse.getData(), SystemError.class);
//
////            log.error(String.format("Error invoking dependency services. of %s path: %s", errorData.getService(), errorData.getContextPath()),
////                    ex,
////                    loggingIndex.kv(),
////                    kv("nmu_feign_err_response", ex.contentUTF8()));
//
//            this.logerror(errorData.getCode(), status, ex);
//            return handleExceptionInternal(ex, responseException, breadCrumbService.getTracingHeader(), status, request);
//        } catch (Exception iex) {
//            var req = request.getNativeRequest(HttpServletRequest.class);
//            var requestException = ex.request();
//            var json = mapper.createObjectNode();
//            json.put("timestamp", current.format(DateTimeFormatter.ISO_DATE_TIME));
//            json.put("status", status.value());
//            json.put("error", status.name());
//
//            response.setOperationFail(FAILURE, ErrorData.builder()
//                    .code(INTERNAL_SERVER_ERROR.getCode())
//                    .msg(String.format("FeignException with URL: %s Method: %s", requestException.url(), requestException.httpMethod().name()))
//                    .error(StringUtils.isBlank(responseException) ? json : jsonException)
//                    .cause(ex.getCause() != null ? ex.getCause().toString() : null)
//                    .timestamp(current.format(DateTimeFormatter.ISO_DATE_TIME))
//                    .service(appName + ":" + appVersion)
//                    .traceId(breadCrumbService.getTraceId())
//                    .contextPath(req != null && req.getMethod() != null && req.getRequestURI() != null ? "[" + req.getMethod() + "] " + req.getRequestURI() : null)
//                    .build());
//
//            log.error(String.format("Error: %s %s", ex.status(), responseException), loggingIndex.kv(), kv("nmu_feign_err_response", responseException));
//            logerror(INTERNAL_SERVER_ERROR.getCode(), HttpStatus.INTERNAL_SERVER_ERROR, ex);
//
//            return handleExceptionInternal(ex, response, breadCrumbService.getTracingHeader(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//        }
//    }
//
//    @ExceptionHandler
//    protected ResponseEntity<Object> handleOtherException(Exception ex, NativeWebRequest request) {
//        var response = new ResponseBody<>();
//        var req = request.getNativeRequest(HttpServletRequest.class);
//        var status = HttpStatus.INTERNAL_SERVER_ERROR;
//
//        String message;
//        ObjectNode json;
//
//        if (ex instanceof NullPointerException) {
//            var stackTrace = ex.getStackTrace()[0];
//            var mapper = new ObjectMapper();
//
//            message = "NullPointerException";
//            json = mapper.createObjectNode();
//            json.put("class", stackTrace.getClassName());
//            json.put("method", stackTrace.getMethodName());
//            json.put("line", stackTrace.getLineNumber());
//        } else {
//            message = ex.getMessage();
//            json = null;
//        }
//
//        response.setOperationFail(FAILURE, ErrorData.builder()
//                .code(INTERNAL_SERVER_ERROR.getCode())
//                .msg(message)
//                .error(json)
//                .cause(ExceptionUtils.getRootCause(ex).toString())
//                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
//                .service(appName + ":" + appVersion)
//                .traceId(breadCrumbService.getTraceId())
//                .contextPath(req != null && req.getMethod() != null && req.getRequestURI() != null ? "[" + req.getMethod() + "] " + req.getRequestURI() : null)
//                .build());
//
//        this.logerror(INTERNAL_SERVER_ERROR.getCode(), status, ex);
//        return handleExceptionInternal(ex, response, breadCrumbService.getTracingHeader(), status, request);
//    }
//
//    private void logerror(String errorCode, HttpStatus httpstatus, Exception ex) {
//        loggingIndex.setErrorCode(errorCode);
//        loggingIndex.setApiResponseStatus(httpstatus);
//
//        var processTime = System.currentTimeMillis() - loggingIndex.getApiStartTime();
//
//        loggingIndex.setApiInvokeTime(System.currentTimeMillis() - loggingIndex.getApiStartTime());
//        log.error(String.format("executed api %s error in %s ms", loggingIndex.getApiSignature(), processTime), loggingIndex.kv(), kv("nmu_summary", true), ex);
//    }
//}
