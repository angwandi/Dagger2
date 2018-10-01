package app.a2ms.dagger2.base;

import android.app.Application;

public class MyApplication extends Application {
    //make a field for {@ApplicationComponent}
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //always remember to rebuild the project to get Dagger builders
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        //next creating activity scope
    }
}
