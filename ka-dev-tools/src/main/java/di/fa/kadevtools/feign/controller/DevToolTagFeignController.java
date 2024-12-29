package di.fa.kadevtools.feign.controller;

import di.fa.kadevtools.feign.dto.request.BulkUpsertDelDevToolTagsRequest;
import di.fa.kadevtools.feign.dto.request.SearchDevToolTagsRequest;
import di.fa.kadevtools.feign.dto.request.UpsertDelDevToolTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/dev-tool-tag")
public class DevToolTagFeignController {

    @GetMapping()
    public ResponseEntity<Object> listDevToolTags() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{devToolId}")
    public ResponseEntity<Object> getDevToolTag(@PathVariable("devToolId") String devToolId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    public ResponseEntity<Object> upsertDelDevToolTag(@RequestParam(value = "action", required = true) String action,
                                                   @RequestBody UpsertDelDevToolTagRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PutMapping("")
    public ResponseEntity<Object> bulkUpsertDelDevToolTags(
            @RequestParam(value = "action", required = true) String action,
            @RequestBody BulkUpsertDelDevToolTagsRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> upsertDelDevToolTag(@RequestBody UpsertDelDevToolTagRequest request) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchDevToolTags(@RequestBody SearchDevToolTagsRequest request) {

        return ResponseEntity.ok(null);
    }
}
