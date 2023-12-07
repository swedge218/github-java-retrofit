package com.example.githubclient.service;

import com.example.githubclient.api.APIConfiguration;
import com.example.githubclient.api.RepositoryClient;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService implements APIConfiguration {

    private String accessToken;
    private RepositoryClient client;

    public GitHubService(@Qualifier("repoClient") RepositoryClient client,
                         @Qualifier("github_access_token") String accessToken) {
        this.client = client;
        this.accessToken = accessToken;
    }

    public List<Repository> getRepositories() throws IOException {
        Call<List<Repository>> retrofitCall = client.listRepos(accessToken, API_VERSION_SPEC);
        Response<List<Repository>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    public List<Repository> getRepositoriesRx() throws IOException {
        //---------> Blocking process to return the list
        // return client.listReposRx(accessToken, API_VERSION_SPEC).blockingFirst();

        //---------> Using Async/Reactive process
        List<Repository> repos = new ArrayList<Repository>();
        client.listReposRx(accessToken, API_VERSION_SPEC)
                .flatMapIterable(x -> {return x;})
                .subscribe(repo -> repos.add(repo), Throwable::printStackTrace);
        return repos;
    }


    public Repository createRepository(Repository repo) throws IOException {
        Call<Repository> retrofitCall = client.createRepo(repo, accessToken, API_VERSION_SPEC, JSON_CONTENT_TYPE);
        Response<Repository> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    public Repository createRepositoryRx(Repository repo) throws IOException {
        Repository[] repository = {null};
        client.createRepoRx(repo, accessToken, API_VERSION_SPEC, JSON_CONTENT_TYPE)
                .subscribe(r -> {
                    System.out.println("Inside subscribe");
                    repository[0] = r;
                });
        System.out.println("Outside subscribe");
        return repository[0];
    }

    public DeletePayload deleteRepository(String owner, String repoName) throws IOException {
        Call<DeletePayload> retrofitCall = client.deleteRepo(accessToken, API_VERSION_SPEC, repoName, owner);

        Response<DeletePayload> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }
}