package com.example.githubclient.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
public class StartUpService {

    private Dotenv dotenv;

    @Bean(name = "dotenv")
    public Dotenv loadEnvs() {
        return dotenv = Dotenv.configure().load();
    }

    @Bean(name = "github_access_token")
    @DependsOn("dotenv")
    public String loadAccessToken() {
        return "token " + dotenv.get("GITHUB_ACCESS_TOKEN");
    }
}
