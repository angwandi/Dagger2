package app.a2ms.dagger2.trending;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import app.a2ms.dagger2.data.RepoRepository;
import app.a2ms.dagger2.di.ScreenScope;
import app.a2ms.dagger2.model.Repo;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRepository repoRepository) {

        this.viewModel = viewModel;
        this.repoRepository = repoRepository;

        loadRepos();
    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());

    }


    @Override
    public void onRepoClicked(Repo repo) {
        //TODO to detail screen
    }
}
