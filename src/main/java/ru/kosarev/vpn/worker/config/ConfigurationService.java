package ru.kosarev.vpn.worker.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kosarev.vpn.worker.client.ConfigurationScriptClient;
import ru.kosarev.vpn.worker.data.*;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationScriptClient configurationScriptClient;

    public IssueConfigurationResponse issueConfiguration(IssueConfigurationRequest request) {
        Long userId = request.getUserId();

        File file = configurationScriptClient.issueConfig(Long.toString(userId));

        return IssueConfigurationResponse.builder()
            .file(file)
            .build();
    }

    public LockConfigurationResponse lockConfiguration(LockConfigurationRequest request) {
        return null;
    }

    public UnlockConfigurationResponse unlockConfiguration(UnlockConfigurationRequest request) {
        return null;
    }

}
