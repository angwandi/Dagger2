package io.neverstoplearning.advancedandroid.base;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import io.neverstoplearning.advancedandroid.home.MainActivity;
import io.neverstoplearning.advancedandroid.home.TestMainActivityComponent;

@Module(subcomponents = TestMainActivityComponent.class)
public abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector(TestMainActivityComponent.Builder builder);
}
