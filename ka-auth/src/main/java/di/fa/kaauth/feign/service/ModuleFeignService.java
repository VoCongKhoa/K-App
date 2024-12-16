package di.fa.kaauth.feign.service;

import di.fa.kaauth.feign.dto.request.RegisterModuleFeignRequest;
import di.fa.kacommon.response.SystemResponse;

public interface ModuleFeignService {
    SystemResponse<Object> registerModule(RegisterModuleFeignRequest request);

    SystemResponse<Object> getModuleSettings(String subDomain);
}
