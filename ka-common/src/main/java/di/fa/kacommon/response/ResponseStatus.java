package di.fa.kacommon.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESSFUL("KSLS-GW-000", "Successful"),

    // authorized & validate request
    UNAUTHORIZED("KSLS-GW-401", "Unauthorized"),
    SECRET_KEY_NOT_FOUND("KSLS-GW-402", "Secret key not found"),
    RESULT_NOT_FOUND("KSLS-GW-404", "Result not found"),
    INVALID_REQUEST_PARAMETER("KSLS-GW-500", "Invalid request parameter: "),
    INVALID_REQUEST_BODY("KSLS-GW-501", "Invalid request body"),
    REQUEST_BODY_NOT_FOUND("KSLS-GW-502", "Request body not found"),
    MAINTENANCE_IN_PROGRESS("KSLS-GW-800", "Maintenance in progress"),
    INTERNAL_SERVER_ERROR("KSLS-GW-999", "Internal server error");

    private final String code;
    private final String message;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
}