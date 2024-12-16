package di.fa.kagateway.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemError {
    UNAUTHORIZED("K-0001", "Unauthorized"),
    RESULT_NOT_FOUND("K-0002", "Result not found"),
    DUPLICATE("K-0003", "Duplicate"),
    INVALID_REQUEST_PARAMETER("NMU-0003", "Invalid request parameter: "),
    INTERNAL_SERVER_ERROR("NMU-9999", "Internal server error");
    private final String code;
    private final String message;
}
