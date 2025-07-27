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
        String userIdString = Long.toString(request.getUserId());
        File configFile = configurationScriptClient.issueConfig(userIdString);

        log.info("issueConfiguration -> configFilename: [{}]", configFile.getName());

        return configFile;
    }

    public void lockConfigurations(LockConfigurationRequest request) {
        String userIdString = Long.toString(request.getUserId());
        configurationScriptClient.lockConfigs(userIdString);

        log.info("lockConfiguration -> ");
    }

    public void unlockConfigurations(UnlockConfigurationRequest request) {
        String userIdString = Long.toString(request.getUserId());
        configurationScriptClient.unlockConfigs(userIdString);

        log.info("unlockConfiguration -> ");
    }

    public void removeConfigurations(RemoveConfigurationRequest request) {
        String userIdString = Long.toString(request.getUserId());
        configurationScriptClient.removeConfigs(userIdString);

        log.info("removeConfigurations -> ");
    }

}
