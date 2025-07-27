package ru.kosarev.vpn.worker.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kosarev.vpn.worker.data.*;

@Slf4j
@RestController(value = "/configuration")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @PutMapping(value = "/issue", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IssueConfigurationResponse issueConfiguration(@RequestBody IssueConfigurationRequest request) {
        log.info("issueConfiguration <- request: {}", request);

        return configurationService.issueConfiguration(request);
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
