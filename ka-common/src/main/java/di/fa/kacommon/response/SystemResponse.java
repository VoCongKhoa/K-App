package di.fa.kacommon.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemResponse<T> {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    @JsonProperty("system_error")
    private SystemError systemError;

    public void asSuccess(String resultMsg, T resultData) {
        this.code = 1;
        this.message = resultMsg;
        this.data = resultData;
        this.systemError = null;
    }

    public void asFailed(String resultMsg, T resultData) {
        this.code = 0;
        this.message = resultMsg;
        this.data = resultData;
        this.systemError = null;
    }

    public void asError(String resultMsg, SystemError systemError) {
        this.code = 0;
        this.message = resultMsg;
        this.data = null;
        this.systemError = systemError;
    }

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
}
