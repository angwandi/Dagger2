package app.a2ms.dagger2.trending;


import android.annotation.SuppressLint;

import javax.inject.Inject;

import app.a2ms.dagger2.data.RepoRepository;
import app.a2ms.dagger2.di.ScreenScope;
import app.a2ms.dagger2.model.Repo;
import app.a2ms.dagger2.ui.ScreenNavigator;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private final ScreenNavigator screenNavigator;

    @Inject
    TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        loadRepos();
    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        //noinspection ResultOfMethodCallIgnored
        repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}