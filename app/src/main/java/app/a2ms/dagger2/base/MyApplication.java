package app.a2ms.dagger2.base;

import android.app.Application;

import javax.inject.Inject;

import app.a2ms.dagger2.BuildConfig;
import app.a2ms.dagger2.di.ActivityInjector;
import timber.log.Timber;

public class MyApplication extends Application {
    //make a field for {@ApplicationComponent}
    private ApplicationComponent component;
    //Get the activityInjector
    @Inject
    ActivityInjector activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        //always remember to rebuild the project to get Dagger builders
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
