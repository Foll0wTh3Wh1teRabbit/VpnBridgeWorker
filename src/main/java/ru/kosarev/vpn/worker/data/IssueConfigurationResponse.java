package ru.kosarev.vpn.worker.data;

import lombok.Builder;
import lombok.Value;

import java.io.File;

@Value
@Builder
public class IssueConfigurationResponse {

    File file;

}
