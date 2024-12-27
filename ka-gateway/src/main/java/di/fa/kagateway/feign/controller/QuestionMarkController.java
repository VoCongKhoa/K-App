package di.fa.kagateway.feign.controller;

import di.fa.kacommon.common.Name;
import di.fa.kacommon.common.Type;
import di.fa.kagateway.core.annotates.Information;
import di.fa.kagateway.core.annotates.UnAuthController;
import di.fa.kagateway.feign.dto.request.BulkUpsertDelDevToolRequest;
import di.fa.kagateway.feign.dto.request.QuestionMarkRequest;
import di.fa.kagateway.feign.dto.request.UpsertDelDevToolRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/?")
@UnAuthController
public class QuestionMarkController {

    // Get Config PostgreSQL Demo
    @GetMapping("/{search}")
    @Information(description = "")
    public ResponseEntity<Object> showListJavaDevTools(@PathVariable("search") String search) {
        // "/java/sql/config/"
        // Process the dynamic path
        var searchTags = Arrays.stream(search.split("-")).toList(); // Split into segments
        var request = QuestionMarkRequest.builder();
        List<String> searchs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(searchTags)) {

            for (int i = 0; i < searchTags.size(); i++) {
                if (i == 0) {
                    request.mainLanguage(StringUtils.defaultIfBlank(searchTags.get(0), Name.MainLanguage.JAVA.getType()));
                }
                if (i == 1) {
                    request.type(StringUtils.defaultIfBlank(searchTags.get(1), Type.DevTools.CONFIG.getType()));
                }
                searchs.add(searchTags.get(i));
            }
        }
        request.searchTags(searchs);
        request.build();
        return ResponseEntity.ok(null);
    }
}
