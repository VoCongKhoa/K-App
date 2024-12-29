package di.fa.kadevtools.feign.dto.request.base;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDelBaseRequest {
    private String action;
}
