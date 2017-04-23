package com.example.hk.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.hk.myapplication.R;
import com.example.hk.myapplication.client.entity.BaseEntity;
import com.example.hk.myapplication.client.entity.PageImpl;
import com.example.hk.myapplication.type.RecyclerViewHolderType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public abstract class BaseScrollableFragment<E extends BaseEntity> extends BaseFragment {

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;

    protected boolean loading;
    protected boolean existNext;
    protected int currentPage;
    protected List<E> contents;

    protected abstract RecyclerView getRecyclerView(View view);

    protected abstract Call<PageImpl<E>> getContentsByApi(int page);

    abstract void onBindViewHolderInternal(RecyclerView.ViewHolder holder, E entity);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = this.getRecyclerView(view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), this.getSpanCount()));
        recyclerView.setAdapter(getAdapter());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_feed_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        loading = false;
        contents = new ArrayList<>();
        currentPage = 0;
        existNext = true;
        this.loadContents(currentPage++);

        return view;
    }

    protected int getSpanCount() {

        return 1;
    }

    protected void loadContents(int page) {

        if (!existNext) {

            return;
        }

        Call<PageImpl<E>> call = this.getContentsByApi(page);
        loading = true;
        call.enqueue(new Callback<PageImpl<E>>() {

            @Override
            public void onResponse(Call<PageImpl<E>> call, Response<PageImpl<E>> response) {

                if (response.isSuccessful()) {

                    PageImpl<E> body = response.body();
                    existNext = body.existNext;
                    contents.addAll(body.content);
                    // TODO FIXME
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {

                    try {

                        Log.e(TAG, "onFailure: code:" + response.code() + ", body:" + response.errorBody().string());
                    } catch (IOException e) {

                        Log.e(TAG, "onFailure: ", e);
                    }
                }

                loading = false;
            }

            @Override
            public void onFailure(Call<PageImpl<E>> call, Throwable t) {

                Log.e(TAG, "onFailure: ", t);
                loading = false;
            }
        });
    }

    // TODO 外だし
    protected RecyclerView.Adapter getAdapter() {

        return new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeId) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                RecyclerViewHolderType viewHolderType = RecyclerViewHolderType.of(viewTypeId);
                View view = inflater.inflate(viewHolderType.getLayoutResouceId(), parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                onBindViewHolderInternal(holder, contents.get(position));

                if (position >= getItemCount() - 1 && !loading) {

                    loadContents(currentPage++);
                }
            }

            @Override
            public int getItemCount() {

                return contents.size();
            }
        };
    }
}
