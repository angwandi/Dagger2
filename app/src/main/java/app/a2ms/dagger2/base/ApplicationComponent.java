package app.a2ms.dagger2.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
