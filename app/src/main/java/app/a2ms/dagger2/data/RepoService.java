package app.a2ms.dagger2.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();
}
