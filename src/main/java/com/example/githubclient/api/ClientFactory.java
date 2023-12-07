package com.example.githubclient.api;

import com.example.githubclient.util.RetrofitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {
    @Bean(name = "repoClient")
    public RepositoryClient produceRepositoryClient() {
        return new RetrofitUtil().provideRetrofit().create(RepositoryClient.class);
    }
}
