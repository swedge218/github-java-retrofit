package com.example.githubclient.util;

import com.example.githubclient.api.APIConfiguration;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
//import retrofit2.adapter.java8.Java8CallAdapterFactory;

public class RetrofitUtil implements APIConfiguration {

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}