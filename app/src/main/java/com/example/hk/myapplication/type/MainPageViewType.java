package com.example.hk.myapplication.type;

import com.example.hk.myapplication.R;
import com.example.hk.myapplication.fragment.BaseFragment;
import com.example.hk.myapplication.fragment.FeedFragment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MainPageViewType {

    FEED(0, FeedFragment.class, R.string.feed_name),;
//    FAVORITE(1, FeedFragment.class, R.string.app_name),
//    RANKING(2, FeedFragment.class, R.string.app_name),
//    NEWS(3, FeedFragment.class, R.string.app_name);

    @Getter
    private int position;

    @Getter
    private Class<? extends BaseFragment> fragment;

    @Getter
    private int nameResourceId;

    public static MainPageViewType of(int position) {

        for (MainPageViewType mainPageViewType :
                values()) {

            if (position == mainPageViewType.position) {

                return mainPageViewType;
            }
        }

        throw new IllegalStateException();
    }
}
