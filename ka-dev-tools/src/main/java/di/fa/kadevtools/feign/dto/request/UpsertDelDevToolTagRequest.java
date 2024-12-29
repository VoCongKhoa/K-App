package di.fa.kadevtools.feign.dto.request;

import di.fa.kadevtools.feign.dto.request.base.UpsertDelBaseRequest;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDelDevToolTagRequest extends UpsertDelBaseRequest {
    private UpsertDelDevToolTagItem item;
}
