package com.example.hk.myapplication.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hk.myapplication.R;
import com.example.hk.myapplication.client.ApiFactory;
import com.example.hk.myapplication.client.entity.PageImpl;
import com.example.hk.myapplication.client.entity.PlayerPost;
import com.example.hk.myapplication.databinding.FragmentFeedBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class FeedFragment extends BaseScrollableFragment<PlayerPost> {

    private FragmentFeedBinding binding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        this.binding = FragmentFeedBinding.bind(super.getView());
    }

    @Override
    protected int getResourceId() {

        return R.layout.fragment_feed;
    }

    @Override
    protected RecyclerView getRecyclerView(View view) {

        return this.binding.fragmentFeedRecyclerView;
    }

    @Override
    protected int getSpanCount() {

        return 2;
    }

    @Override
    protected Call<PageImpl<PlayerPost>> getContentsByApi(int page) {

        return ApiFactory.create().getPosts(page);
    }

    @Override
    protected void onBindViewHolderInternal(RecyclerView.ViewHolder holder, PlayerPost playerPost) {

        // id
        TextView id = (TextView) holder.itemView.findViewById(R.id.content_player_post_id);
        id.setText(String.valueOf(playerPost.id));

        // playerName
        TextView playerName = (TextView) holder.itemView.findViewById(R.id.content_player_post_player_name);
        playerName.setText(playerPost.playerName);

        // image
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.content_player_post_image_url);
        // TODO: check exceptions when url is empty.
        Picasso.with(getContext()).load(playerPost.imageUrl).into(imageView);
    }
}
