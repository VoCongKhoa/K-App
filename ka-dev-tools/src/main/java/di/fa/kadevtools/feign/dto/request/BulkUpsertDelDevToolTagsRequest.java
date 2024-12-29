package di.fa.kadevtools.feign.dto.request;

import di.fa.kadevtools.feign.dto.request.base.UpsertDelBaseRequest;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BulkUpsertDelDevToolTagsRequest  extends UpsertDelBaseRequest {
    private List<UpsertDelDevToolTagItem> tagItems;
}
