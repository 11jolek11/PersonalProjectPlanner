package com.project.planner.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.nio.file.Path;

@Configuration
@ConfigurationProperties("store")
public class StorageConfig {
    @Value("@{storageroot}")
    private String stringRootPath;

    public void setStringRootPath(String newStringRootPath) {
        this.stringRootPath = newStringRootPath;
    }

    public String getStringRootPath() {
        return this.stringRootPath;
    }
}
