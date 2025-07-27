package ru.kosarev.vpn.worker.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kosarev.vpn.worker.data.*;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/configuration")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @PutMapping(value = "/issue")
    public ResponseEntity<Resource> issueConfiguration(@RequestBody IssueConfigurationRequest request) {
        log.info("issueConfiguration <- request: {}", request);

        File file = configurationService.issueConfiguration(request);
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }

    @PostMapping(value = "/lock")
    public LockConfigurationResponse lockConfiguration(@RequestBody LockConfigurationRequest request) {
        log.info("lockConfiguration <- request: {}", request);

        return configurationService.lockConfiguration(request);
    }

    @PostMapping(value = "/unlock")
    public UnlockConfigurationResponse unlockConfiguration(@RequestBody UnlockConfigurationRequest request) {
        log.info("unlockConfiguration <- request: {}", request);

        return configurationService.unlockConfiguration(request);
    }

}
