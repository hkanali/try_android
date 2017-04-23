package com.example.hk.myapplication.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hk.myapplication.activity.MainActivity;
import com.example.hk.myapplication.fragment.BaseFragment;
import com.example.hk.myapplication.type.MainPageViewType;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private MainActivity mainActivity;

    public MainPagerAdapter(MainActivity mainActivity) {

        super(mainActivity.getSupportFragmentManager());
        this.mainActivity = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {

        try {

            return MainPageViewType.of(position).getFragment().newInstance();
        } catch (Exception e) {

            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getCount() {

        return MainPageViewType.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return this.mainActivity.getResources().getString(MainPageViewType.of(position).getNameResourceId());
    }
}
