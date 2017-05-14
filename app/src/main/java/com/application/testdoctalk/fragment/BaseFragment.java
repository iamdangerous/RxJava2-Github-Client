package com.application.testdoctalk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.application.testdoctalk.application.Initializer;
import com.application.testdoctalk.retrofit.ApiInterface;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by rkrde on 14-05-2017.
 */

public class BaseFragment extends Fragment {

    @Inject
    @Named("withoutToken")
    Retrofit retrofit;
    ApiInterface apiInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((Initializer) getContext().getApplicationContext()).getNetComponent().inject(this);
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
     void displayMessage(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
