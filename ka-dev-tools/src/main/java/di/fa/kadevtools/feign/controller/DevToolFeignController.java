package di.fa.kadevtools.feign.controller;

import di.fa.kadevtools.feign.dto.request.SearchDevToolsRequest;
import di.fa.kadevtools.feign.dto.request.base.UpsertDelBaseRequest;
import di.fa.kadevtools.feign.service.DevToolFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev-tools")
public class DevToolFeignController {

    final DevToolFeignService devToolFeignService;

    @GetMapping()
    public ResponseEntity<Object> listDevTools() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{devToolId}")
    public ResponseEntity<Object> getDevTool(@PathVariable("devToolId") String devToolId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    public ResponseEntity<Object> upsertDelDevTool(@RequestBody UpsertDelBaseRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        var response = devToolFeignService.upsertDelDevTool(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/bulk")
    public ResponseEntity<Object> bulkUpsertDelDevTool(@RequestBody UpsertDelBaseRequest request) {
        // action == insert => insert
        // action == update => update
        // action == delete => delete
        return ResponseEntity.ok(null);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchDevTools(@RequestBody SearchDevToolsRequest request) {

        return ResponseEntity.ok(null);
    }
}
