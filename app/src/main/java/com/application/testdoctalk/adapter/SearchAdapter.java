package com.application.testdoctalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.testdoctalk.R;
import com.application.testdoctalk.modal.networkResponse.IssuesResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rkrde on 14-05-2017.
 */
public class SearchAdapter extends RecyclerView.Adapter {

    Context context;
    List<IssuesResponse> mList;
    public ISearchAdapter mListener;
    public
    interface ISearchAdapter {
        void getNextItem();
    }

    public SearchAdapter(Context context,List<IssuesResponse> mList,ISearchAdapter mListener) {
        this.context = context;
        this.mList = mList;
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder v = null;
        v = new SearchViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent, false));
        return v;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder viewHolder = (SearchViewHolder) holder;
        String title = mList.get(holder.getAdapterPosition()).title;
        String name = mList.get(holder.getAdapterPosition()).user.login;
        String state = mList.get(holder.getAdapterPosition()).state;

        viewHolder.tvTitle.setText(title);
        viewHolder.tvName.setText(name);
        viewHolder.tvState.setText(state);


        if (position == mList.size() - 4 && mList.size()>18) {
            mListener.getNextItem();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_state)
        TextView tvState;
        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

