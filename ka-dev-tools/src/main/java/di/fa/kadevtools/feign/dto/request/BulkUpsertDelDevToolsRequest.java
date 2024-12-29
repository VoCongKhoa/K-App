package di.fa.kadevtools.feign.dto.request;

import di.fa.kadevtools.feign.dto.request.base.UpsertDelBaseRequest;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BulkUpsertDelDevToolsRequest extends UpsertDelBaseRequest {
    private List<UpsertDelDevToolItem> toolItems;
}
