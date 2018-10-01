package app.a2ms.dagger2.home;

import com.bluelinelabs.conductor.Controller;

import app.a2ms.dagger2.di.ControllerKey;
import app.a2ms.dagger2.trending.TrendingReposComponent;
import app.a2ms.dagger2.trending.TrendingReposController;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(
            TrendingReposComponent.Builder builder);

}
