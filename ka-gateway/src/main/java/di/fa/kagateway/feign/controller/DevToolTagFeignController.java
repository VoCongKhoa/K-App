package di.fa.kagateway.feign.controller;

import di.fa.kagateway.core.annotates.Information;
import di.fa.kagateway.feign.dto.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/dev-tool-tag")
public class DevToolTagFeignController {

    @GetMapping()
    @Information(description = "")
    public ResponseEntity<Object> listDevToolTags() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{devToolId}")
    @Information(description = "")
    public ResponseEntity<Object> getDevToolTag(@PathVariable("devToolId") String devToolId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    @Information(description = "")
    public ResponseEntity<Object> upsertDelDevToolTag(@RequestParam(value = "action", required = true) String action,
                                                   @RequestBody UpsertDelDevToolTagRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PutMapping("")
    @Information(description = "")
    public ResponseEntity<Object> bulkUpsertDelDevToolTags(
            @RequestParam(value = "action", required = true) String action,
            @RequestBody BulkUpsertDelDevToolTagsRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PostMapping("/search")
    @Information(description = "")
    public ResponseEntity<Object> upsertDelDevToolTag(@RequestBody UpsertDelDevToolTagRequest request) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("/search")
    @Information(description = "")
    public ResponseEntity<Object> searchDevToolTags(@RequestBody SearchDevToolTagsRequest request) {

        return ResponseEntity.ok(null);
    }
}
