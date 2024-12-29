package di.fa.kagateway.feign.dto.request;

import di.fa.kagateway.feign.dto.request.base.UpsertDelBaseRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BulkUpsertDelDevToolsRequest extends UpsertDelBaseRequest {
    private UUID id;

}
