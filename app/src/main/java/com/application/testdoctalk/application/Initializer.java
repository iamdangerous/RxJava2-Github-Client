package com.application.testdoctalk.application;

import android.app.Application;

import com.application.testdoctalk.dagger.component.DaggerNetComponent;
import com.application.testdoctalk.dagger.component.NetComponent;
import com.application.testdoctalk.dagger.module.ContextModule;
import com.facebook.stetho.Stetho;
import com.application.testdoctalk.BuildConfig;
import timber.log.Timber;

/**
 * Created by rkrde on 14-05-2017.
 */

public class Initializer extends Application {
    private NetComponent mNetComponent;


    @Override
    public void onCreate() {
        super.onCreate();
//
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)+":"+element.getLineNumber();
                }
            });

        mNetComponent = DaggerNetComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        if(BuildConfig.DEBUG){
            Stetho.InitializerBuilder initializerBuilder =
                    Stetho.newInitializerBuilder(this);
            initializerBuilder.enableWebKitInspector(
                    Stetho.defaultInspectorModulesProvider(this)
            );
            initializerBuilder.enableDumpapp(
                    Stetho.defaultDumperPluginsProvider(getApplicationContext())
            );
            Stetho.Initializer initializer = initializerBuilder.build();
            Stetho.initialize(initializer);
        }
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
