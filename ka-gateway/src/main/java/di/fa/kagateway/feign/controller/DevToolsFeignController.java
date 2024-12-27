package di.fa.kagateway.feign.controller;

import di.fa.kagateway.core.annotates.Information;
import di.fa.kagateway.feign.dto.request.BulkUpsertDelDevToolRequest;
import di.fa.kagateway.feign.dto.request.UpsertDelDevToolRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/dev-tools")
public class DevToolsFeignController {

    @GetMapping()
    @Information(description = "")
    public ResponseEntity<Object> listDevTools() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{devToolId}")
    @Information(description = "")
    public ResponseEntity<Object> getDevTool(@PathVariable("devToolId") String devToolId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    @Information(description = "")
    public ResponseEntity<Object> upsertDelDevTool(@RequestParam(value = "action", required = true) String action,
                                                   @RequestBody UpsertDelDevToolRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PutMapping("")
    @Information(description = "")
    public ResponseEntity<Object> bulkUpsertDelDevTool(
            @RequestParam(value = "action", required = true) String action,
            @RequestBody BulkUpsertDelDevToolRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }
}
