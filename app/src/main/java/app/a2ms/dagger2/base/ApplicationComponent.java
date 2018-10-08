package app.a2ms.dagger2.base;

import javax.inject.Singleton;

import app.a2ms.dagger2.data.RepoServiceModule;
import app.a2ms.dagger2.networking.ServiceModule;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);
}
