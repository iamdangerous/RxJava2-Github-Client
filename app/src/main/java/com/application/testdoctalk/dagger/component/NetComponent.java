package com.application.testdoctalk.dagger.component;

import com.application.testdoctalk.activity.MainActivity;
import com.application.testdoctalk.dagger.module.ApiModule;
import com.application.testdoctalk.dagger.module.CacheModule;
import com.application.testdoctalk.dagger.module.ContextModule;
import com.application.testdoctalk.dagger.module.NetworkModule;
import com.application.testdoctalk.dagger.module.StorageModule;
import com.application.testdoctalk.fragment.BaseFragment;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rkrde on 14-05-2017.
 */
@Singleton
@Component(modules = {
        CacheModule.class,
        ApiModule.class,
        ContextModule.class,
        NetworkModule.class,
        StorageModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(BaseFragment fragment);

}
