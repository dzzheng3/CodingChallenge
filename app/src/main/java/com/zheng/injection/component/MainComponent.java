package com.zheng.injection.component;

import com.zheng.injection.module.MainModule;
import com.zheng.injection.scope.ActivityScope;
import com.zheng.feature.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Naiqing on 9/26/17.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
