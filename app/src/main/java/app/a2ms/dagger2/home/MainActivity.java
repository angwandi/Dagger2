package app.a2ms.dagger2.home;


import com.bluelinelabs.conductor.Controller;

import app.a2ms.dagger2.R;
import app.a2ms.dagger2.base.BaseActivity;
import app.a2ms.dagger2.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}