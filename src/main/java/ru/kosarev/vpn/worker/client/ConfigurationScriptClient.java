package ru.kosarev.vpn.worker.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.function.BinaryOperator;

import static ru.kosarev.vpn.worker.client.ConfigurationScriptOption.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfigurationScriptClient {

    private static final BinaryOperator<String> CONFIG_NAME_BUILDER =
        (userId, uuid) -> userId + "-" + uuid;

    private static final BinaryOperator<String> CONFIG_PATH_BUILDER =
        (userId, uuid) -> "/root/" + CONFIG_NAME_BUILDER.apply(userId, uuid) + ".conf";

    private static final String ISSUE_CONFIG_SCRIPT = "wireguard-install.sh";

    private static final String WHITESPACE_DELIMITER = " ";

    public File issueConfig(String userId) {
        String configUuid = UUID.randomUUID().toString();
        String shellString = String.join(
            WHITESPACE_DELIMITER,
            ISSUE_CONFIG_SCRIPT,
            Integer.toString(ISSUE_CONFIG.getValue()),
            CONFIG_NAME_BUILDER.apply(userId, configUuid)
        );

        runScript(shellString);

        return new File(CONFIG_PATH_BUILDER.apply(userId, configUuid));
    }

    public void lockConfigs(String userId) {
        String shellString = String.join(
            WHITESPACE_DELIMITER,
            ISSUE_CONFIG_SCRIPT,
            Integer.toString(LOCK_CONFIGS.getValue()),
            userId
        );

        runScript(shellString);
    }

    public void unlockConfigs(String userId) {
        String shellString = String.join(
            WHITESPACE_DELIMITER,
            ISSUE_CONFIG_SCRIPT,
            Integer.toString(UNLOCK_CONFIGS.getValue()),
            userId
        );

        runScript(shellString);
    }

    public void removeConfigs(String userId) {
        String shellString = String.join(
            WHITESPACE_DELIMITER,
            ISSUE_CONFIG_SCRIPT,
            Integer.toString(REMOVE_CONFIGS.getValue()),
            userId
        );

        runScript(shellString);
    }

    private void runScript(String shellString) {
        ProcessBuilder processBuilder = new ProcessBuilder(shellString.split("\\s"));
        Process process = null;

        try {
            process = processBuilder.start();

            int processCode = process.waitFor();
            if (processCode != 0) {
                throw new RuntimeException("Shell command exited with code " + processCode);
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new RuntimeException("Error running shell command", e);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

}
