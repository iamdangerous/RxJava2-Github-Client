package com.application.testdoctalk.retrofit;

import com.application.testdoctalk.modal.networkResponse.IssuesResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by rkrde on 14-05-2017.
 */

public interface ApiInterface {
    @GET("/repos/{owner_name}/{repo}/issues")
    Observable<List<IssuesResponse>> getSearchResult(@Path(value = "owner_name", encoded = true) String owner_name,
                                                     @Path(value = "repo", encoded = true) String repo,
                                                     @QueryMap Map<String, String> options);
}
