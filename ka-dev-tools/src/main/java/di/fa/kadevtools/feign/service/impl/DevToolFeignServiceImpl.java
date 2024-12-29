package di.fa.kadevtools.feign.service.impl;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kadevtools.core.entity.DevToolEntity;
import di.fa.kadevtools.core.repository.DevToolRepository;
import di.fa.kadevtools.feign.dto.request.UpsertDelDevToolRequest;
import di.fa.kadevtools.feign.dto.request.base.UpsertDelBaseRequest;
import di.fa.kadevtools.feign.service.DevToolFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DevToolFeignServiceImpl implements DevToolFeignService {

    DevToolRepository devToolRepository;

    @Override
    public SystemResponse<Object> upsertDelDevTool(UpsertDelBaseRequest request) {
        var action = request.getAction();
        var castedRequest = (UpsertDelDevToolRequest) request;
        if (action.equals("up")) {
            var devTool = devToolRepository.getReferenceById(castedRequest.getItem().getDevToolId());

        }

        if (action.equals("sert")) {
            var devtool = DevToolEntity.builder();
        }

        if (action.equals("del")) {
            var devTool = devToolRepository.getReferenceById(castedRequest.getItem().getDevToolId());

        }
        return null;
    }
}
