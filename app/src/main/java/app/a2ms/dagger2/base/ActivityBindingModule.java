package app.a2ms.dagger2.base;

import android.app.Activity;

import app.a2ms.dagger2.home.MainActivity;
import app.a2ms.dagger2.home.MainActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(MainActivityComponent.Builder builder);
}
