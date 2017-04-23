package com.example.hk.myapplication.view;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.hk.myapplication.type.NativationType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyNavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

    @lombok.NonNull
    private DrawerLayout drawerLayout;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NativationType nativationType = NativationType.of(item.getItemId());
        // TODO
        Fragment fragment = nativationType.getFragment();

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
