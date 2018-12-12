package com.gosuncn.myartifact.myblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.mycloud")
@Component
public class MyConfig {
    @Autowired
    Environment env;
    @Autowired
    MyConfig myConfig;
    @Value("${server.port}")
    String port;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
