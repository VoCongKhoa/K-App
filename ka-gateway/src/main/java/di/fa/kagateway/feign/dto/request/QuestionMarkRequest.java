package di.fa.kagateway.feign.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import di.fa.kacommon.common.Name;
import di.fa.kacommon.common.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionMarkRequest {

    // java or postgresql or ...
    @JsonProperty("main_language")
    private String mainLanguage = Name.MainLanguage.JAVA.getType();

    // tool or config
    @JsonProperty("type")
    private String type = Type.DevTools.CONFIG.getType();

    @JsonProperty("search_tags")
    private List<String> searchTags;
}
