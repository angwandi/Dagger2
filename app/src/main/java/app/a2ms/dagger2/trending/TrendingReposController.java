package app.a2ms.dagger2.trending;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import app.a2ms.dagger2.base.BaseController;
import app.a2ms.dagger2.home.MainActivity;

public class TrendingReposController extends BaseController {
    @Inject
    MainActivity mainActivity;
    
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return null;
    }
}
