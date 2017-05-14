package com.application.testdoctalk.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.application.testdoctalk.R;
import com.application.testdoctalk.adapter.SearchAdapter;
import com.application.testdoctalk.modal.networkResponse.IssuesResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by rkrde on 14-05-2017.
 */

public class SearchFragment extends BaseFragment implements SearchAdapter.ISearchAdapter {

    List<IssuesResponse> mList;
    SearchAdapter mAdapter;
    String searchQuery;
    @BindView(R.id.et)
    AppCompatEditText et;
    @BindView(R.id.rv)
    RecyclerView rv;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        init();
        setAdapter();
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH: {
                        doMySearch(et.getText().toString(), String.valueOf(0));
                        searchQuery = et.getText().toString();
                    }
                    return true;
                    default:
                        return false;
                }
            }
        });

//        rv.setOnClickListener(v-> System.out.println());

        return view;
    }

    private void doMySearch(String query, String start) {
        if (query.isEmpty()) {
            displayMessage("write something");
            return;
        }
        String arr[] = query.split("/");
        if(arr.length!=2){
            displayMessage("invalid input");
            return;
        }

        String ownerName = arr[0];
        String repoName = arr[1];
        Map<String,String> map = new HashMap<>();
        apiInterface.getSearchResult(ownerName,repoName,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private void init() {
        mList = new ArrayList<>();
    }

    private void setAdapter() {
        mAdapter = new SearchAdapter(getContext(), mList, this);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getNextItem() {

    }

    Observer<List<IssuesResponse>> observer = new Observer<List<IssuesResponse>>() {
        @Override
        public void onSubscribe(Disposable d) {
            Timber.d("onSubs");
        }

        @Override
        public void onNext(List<IssuesResponse> issuesResponses) {
            Timber.d("onNext");
            mList.clear();
            mList.addAll(issuesResponses);
            mAdapter.notifyItemRangeInserted(0,mList.size());
        }

        @Override
        public void onError(Throwable e) {
            Timber.d("onError");
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    };
}
