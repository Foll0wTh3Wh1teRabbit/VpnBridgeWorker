package ru.kosarev.vpn.worker.client;

import lombok.Getter;

@Getter
public enum ConfigurationScriptOption {

    ISSUE_CONFIG(1),

    LOCK_CONFIGS(4),

    UNLOCK_CONFIGS(5),

    REMOVE_CONFIGS(7);

    final Integer value;

    ConfigurationScriptOption(Integer value) {
        this.value = value;
    }

}
