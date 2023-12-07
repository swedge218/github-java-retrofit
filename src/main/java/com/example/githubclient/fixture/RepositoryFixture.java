package com.example.githubclient.fixture;

import org.eclipse.egit.github.core.Repository;

public class RepositoryFixture {

    public static Repository makeRepository(String repoName) {
        return new Repository()
                .setName(repoName)
                .setDescription("This is a test repository created using my GitHub client")
                .setHomepage("https://github.com")
                .setPrivate(false)
                .setHasIssues(true)
                .setHasWiki(true);
    }
}
