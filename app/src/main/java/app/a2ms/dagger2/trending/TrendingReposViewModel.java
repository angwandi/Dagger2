package app.a2ms.dagger2.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import app.a2ms.dagger2.R;
import app.a2ms.dagger2.di.ScreenScope;
import app.a2ms.dagger2.model.Repo;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
public class TrendingReposViewModel {

    private final BehaviorRelay<List<Repo>> repoRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {
    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<List<Repo>> repos() {
        return repoRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<List<Repo>> reposUpdated() {
        errorRelay.accept(-1);
        return repoRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_erro_repos);
        };
    }


}
