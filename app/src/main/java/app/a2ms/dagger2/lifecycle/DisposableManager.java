package app.a2ms.dagger2.lifecycle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableManager {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void add(Disposable... disposables) {
        compositeDisposable.addAll(disposables);
    }

    public void dispose() {
        compositeDisposable.clear();
    }
}
