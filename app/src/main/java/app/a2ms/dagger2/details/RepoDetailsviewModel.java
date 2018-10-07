package app.a2ms.dagger2.details;

import com.jakewharton.rxrelay2.BehaviorRelay;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import javax.inject.Inject;

import app.a2ms.dagger2.R;
import app.a2ms.dagger2.di.ScreenScope;
import app.a2ms.dagger2.model.Contributor;
import app.a2ms.dagger2.model.Repo;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class RepoDetailsviewModel {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MM dd, yyy");

    private final BehaviorRelay<RepoDetailState> detailStateRelay = BehaviorRelay.create();
    private final BehaviorRelay<ContributorState> contributorStateRelay = BehaviorRelay.create();

    @Inject
    RepoDetailsviewModel() {
        detailStateRelay.accept(RepoDetailState.builder().loading(true).build());
        contributorStateRelay.accept(ContributorState.builder().loading(true).build());
    }

    Observable<RepoDetailState> details() {
        return detailStateRelay;
    }

    Observable<ContributorState> contributor() {
        return contributorStateRelay;
    }

    Consumer<Repo> processRepo() {
        return repo -> detailStateRelay.accept(
                RepoDetailState.builder()
                        .loading(false)
                        .name(repo.name())
                        .description(repo.description())
                        .createdDate(repo.createdDate().format(DATE_TIME_FORMATTER))
                        .createdDate(repo.updatedDate().format(DATE_TIME_FORMATTER))
                        .build()
        );
    }

    Consumer<List<Contributor>> processContributors() {
        return contributors -> contributorStateRelay.accept(
                ContributorState.builder()
                        .loading(false)
                        .contributors(contributors)
                        .build());
    }

    Consumer<Throwable> detaislError() {
        return throwable -> {
            Timber.e(throwable, "Error loading repo details");
            detailStateRelay.accept(
                    RepoDetailState.builder()
                            .loading(false)
                            .errorRes(R.string.api_error_single_repo)
                            .build()
            );
        };

    }

    Consumer<Throwable> contributorsError() {
        return throwable -> {
            Timber.e(throwable, "Error loading contributors");
            contributorStateRelay.accept(
                    ContributorState.builder()
                            .loading(false)
                            .errorRes(R.string.api_error_contributors)
                            .build()
            );
        };

    }
}
