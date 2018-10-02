package app.a2ms.dagger2.trending;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import app.a2ms.dagger2.data.RepoRequester;
import app.a2ms.dagger2.di.ScreenScope;
import app.a2ms.dagger2.model.Repo;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel trendingReposViewModel, RepoRequester repoRequester, TrendingReposViewModel viewModel, RepoRequester repoRequester1) {

        this.viewModel = viewModel;
        this.repoRequester = repoRequester1;

        loadRepos();
    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());

    }


    @Override
    public void onRepoClicked(Repo repo) {
        //TODO to detail screen
    }
}
