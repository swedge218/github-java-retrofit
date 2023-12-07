package com.example.githubclient.api;

import io.reactivex.Observable;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.event.DeletePayload;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RepositoryClient {
    @GET("user/repos")
    Call<List<Repository>> listRepos(@Header("Authorization") String accessToken,
                                     @Header("Accept") String apiVersionSpec);

    @GET("user/repos")
    Observable<List<Repository>> listReposRx(@Header("Authorization") String accessToken,
                                                       @Header("Accept") String apiVersionSpec);

    @GET("user/repos")
    CompletableFuture<List<Repository>> listReposCf(@Header("Authorization") String accessToken,
                                                    @Header("Accept") String apiVersionSpec);

    @GET("user/repos")
    Response<Observable<List<Repository>>> listReposJava8(@Header("Authorization") String accessToken,
                                                       @Header("Accept") String apiVersionSpec);

    @DELETE("repos/{owner}/{repo}")
    Call<DeletePayload> deleteRepo(@Header("Authorization") String accessToken,
                                   @Header("Accept") String apiVersionSpec,
                                   @Path("repo") String repo, @Path("owner") String owner);

    @POST("user/repos")
    Call<Repository> createRepo(@Body Repository repo, @Header("Authorization") String accessToken,
                                      @Header("Accept") String apiVersionSpec,
                                      @Header("Content-Type") String contentType);

    @POST("user/repos")
    Observable<Repository> createRepoRx(@Body Repository repo, @Header("Authorization") String accessToken,
                                        @Header("Accept") String apiVersionSpec,
                                        @Header("Content-Type") String contentType);
}