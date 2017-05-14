package com.application.testdoctalk.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rkrde on 14-05-2017.
 */
@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context(){
        return context;
    }
}
