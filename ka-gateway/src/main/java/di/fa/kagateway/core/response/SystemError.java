package di.fa.kagateway.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemError {
    UNAUTHORIZED("KA-0001", "Unauthorized"),
    RESULT_NOT_FOUND("KA-0002", "Result not found"),
    DUPLICATE("KA-0003", "Duplicate"),
    INVALID_REQUEST_PARAMETER("KA-0003", "Invalid request parameter: "),
    INTERNAL_SERVER_ERROR("KA-9999", "Internal server error");
    private final String code;
    private final String message;
}
