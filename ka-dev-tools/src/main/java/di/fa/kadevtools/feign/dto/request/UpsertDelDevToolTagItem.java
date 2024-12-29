package di.fa.kadevtools.feign.dto.request;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDelDevToolTagItem {
    private UUID devToolTagId;
    private String status;
    private String title;
    private String code;
}
