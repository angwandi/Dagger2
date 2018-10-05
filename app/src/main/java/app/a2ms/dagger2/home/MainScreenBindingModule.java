package app.a2ms.dagger2.home;

import com.bluelinelabs.conductor.Controller;

import app.a2ms.dagger2.details.RepoDetailsComponent;
import app.a2ms.dagger2.details.RepoDetailsController;
import app.a2ms.dagger2.di.ControllerKey;
import app.a2ms.dagger2.trending.TrendingReposComponent;
import app.a2ms.dagger2.trending.TrendingReposController;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
        RepoDetailsController.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(
            TrendingReposComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(
            RepoDetailsComponent.Builder builder);
}
