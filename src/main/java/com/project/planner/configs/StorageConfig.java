package com.project.planner.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
@ConfigurationProperties("store")
public class StorageConfig {
    private String stringRootPath;

    public void setStringRootPath(String newStringRootPath) {
        this.stringRootPath = newStringRootPath;
    }

    public String getStringRootPath() {
        return this.stringRootPath;
    }
}
