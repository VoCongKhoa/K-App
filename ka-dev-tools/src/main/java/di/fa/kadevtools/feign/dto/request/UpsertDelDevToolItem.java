package di.fa.kadevtools.feign.dto.request;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDelDevToolItem {
    private UUID devToolId;
    private String status;
    private String title;
    private String code;
    private Boolean isNeedFile;
    private String demoFileUri;
}
