package di.fa.kagateway.feign.dto.request.base;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDelBaseRequest {
    private String action;
}
