package di.fa.kadevtools.feign.service;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kadevtools.feign.dto.request.base.UpsertDelBaseRequest;

public interface DevToolFeignService {

    SystemResponse<Object> upsertDelDevTool(UpsertDelBaseRequest request);
}
