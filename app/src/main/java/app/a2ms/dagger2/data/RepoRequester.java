package app.a2ms.dagger2.data;

import java.util.List;

import javax.inject.Inject;

import app.a2ms.dagger2.model.Contributor;
import app.a2ms.dagger2.model.Repo;
import io.reactivex.Single;

public class RepoRequester {

    private final RepoService service;

    @Inject
    RepoRequester(RepoService service) {
        this.service = service;
    }

    Single<List<Repo>> getTrendingRepos() {
        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos);
    }

    //Make separate requester for every API call for Big project

    Single<Repo> getRepo(String repoOwner, String repoName) {
        return service.getRepo(repoOwner, repoName);
    }

    Single<List<Contributor>> getContributors(String url) {
        return service.getContributors(url);
    }
}
