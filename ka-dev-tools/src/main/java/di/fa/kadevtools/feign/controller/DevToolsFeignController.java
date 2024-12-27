package di.fa.kadevtools.feign.controller;

import di.fa.kadevtools.feign.dto.request.BulkUpsertDelDevToolRequest;
import di.fa.kadevtools.feign.dto.request.UpsertDelDevToolRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
    @RequestMapping("/dev-tools")
public class DevToolsFeignController {

    @GetMapping()
    public ResponseEntity<Object> listDevTools() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{devToolId}")
    public ResponseEntity<Object> getDevTool(@PathVariable("devToolId") String devToolId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    public ResponseEntity<Object> upsertDelDevTool(@RequestParam(value = "action", required = true) String action,
                                                   @RequestBody UpsertDelDevToolRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PutMapping("")
    public ResponseEntity<Object> bulkUpsertDelDevTool(
                                                   @RequestParam(value = "action", required = true) String action,
                                                   @RequestBody BulkUpsertDelDevToolRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }
}
