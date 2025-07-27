package ru.kosarev.vpn.worker.client;

import lombok.Getter;

@Getter
public enum ConfigurationScriptOption {

    ISSUE_CONFIG(1),

    LOCK_CONFIG(4),

    UNLOCK_CONFIG(5);

    final Integer value;

    ConfigurationScriptOption(Integer value) {
        this.value = value;
    }

}
