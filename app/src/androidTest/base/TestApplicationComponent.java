package io.neverstoplearning.advancedandroid.base;

import javax.inject.Singleton;

import dagger.Component;
import io.neverstoplearning.advancedandroid.data.TestRepoServiceModule;
import io.neverstoplearning.advancedandroid.networking.ServiceModule;
import io.neverstoplearning.advancedandroid.trending.TrendingReposControllerTest;
import io.neverstoplearning.advancedandroid.ui.NavigationModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(TrendingReposControllerTest trendingReposControllerTest);
}
