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

    public File issueConfiguration(IssueConfigurationRequest request) {
        Long userId = request.getUserId();

        return configurationScriptClient.issueConfig(Long.toString(userId));
    }

    public LockConfigurationResponse lockConfiguration(LockConfigurationRequest request) {
        return null;
    }

    public UnlockConfigurationResponse unlockConfiguration(UnlockConfigurationRequest request) {
        return null;
    }

}
