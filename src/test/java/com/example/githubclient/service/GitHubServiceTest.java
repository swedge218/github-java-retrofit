package com.example.githubclient.service;


import static com.example.githubclient.api.APIConfiguration.*;

import com.example.githubclient.api.ClientFactory;
import com.example.githubclient.api.RepositoryClient;
import static com.example.githubclient.fixture.RepositoryFixture.*;

import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.egit.github.core.Repository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.MockitoAnnotations.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GitHubServiceTest {
    private String accessToken;
    private GitHubService gitHubService;

    private String repoOwner = "swedge218";

    @BeforeEach
    void setUp() {
        openMocks(this);
        RepositoryClient client = new ClientFactory().produceRepositoryClient();
        this.accessToken = "token " + Dotenv.configure().load().get("GITHUB_ACCESS_TOKEN");
        gitHubService = new GitHubService(client, accessToken);
    }

    @Test
    @Order(1)
    void listLengthGreaterThanZero() throws IOException  {
        assertTrue(gitHubService.getRepositories().size() > 0);
    }

    @Test
    @Order(2)
    void listLengthGreaterThanZeroRx() throws IOException  {
        assertTrue(gitHubService.getRepositoriesRx().size() > 0);
    }

    @Test
     @Order(3)
    @DependsOn("deleteRepoTest")
    void createRepoTest() throws IOException {
        String repoName = "test-repo";
        Repository newRepo = gitHubService.createRepository(makeRepository(repoName));
        assertTrue(newRepo.getName().equals(repoName));
    }

    @Test
    @Order(4)
    void createRepoRxTest() throws IOException {
        String repoName = "test-repo-2";
        Repository newRepo = gitHubService.createRepositoryRx(makeRepository(repoName));
        assertTrue(newRepo.getName().equals(repoName));
    }

    @Test
    @Order(5)
    void deleteRepoTest() throws IOException{
        String repoName = "test-repo";
        String repoName2 = "test-repo-2";
        List<String> repoNames = List.of(repoName, repoName2);

        for(String rName: repoNames) {
            assertFalse(gitHubService.getRepositoriesRx()
                    .stream()
                    .filter(repo -> repo.getName().equals(rName))
                    .findFirst()
                    .isEmpty());

            gitHubService.deleteRepository(repoOwner, rName);

            assertTrue(gitHubService.getRepositoriesRx()
                    .stream()
                    .filter(repo -> repo.getName().equals(rName))
                    .findFirst()
                    .isEmpty());
        }
    }


}
