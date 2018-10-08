package app.a2ms.dagger2.details;

import javax.inject.Inject;
import javax.inject.Named;

import app.a2ms.dagger2.data.RepoRepository;
import app.a2ms.dagger2.di.ScreenScope;

@ScreenScope
class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel) {
        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                        .doOnError(viewModel.contributorsError()))
                .subscribe(viewModel.processContributors(), throwable -> {
                    //we handle logging in the view model
                });
    }
}
