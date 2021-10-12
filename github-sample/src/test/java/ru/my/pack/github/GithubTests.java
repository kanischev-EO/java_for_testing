package ru.my.pack.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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 ");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("pentogon", "java_for_testing")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
