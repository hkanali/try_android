package com.example.hk.myapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hk.myapplication.R;
import com.example.hk.myapplication.adapter.MainPagerAdapter;
import com.example.hk.myapplication.databinding.ActivityMainBinding;
import com.example.hk.myapplication.view.MyNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Toolbar toolbar;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Toolbar
        this.toolbar = this.binding.toolbar;
        super.setSupportActionBar(this.toolbar);

        // Pager
        this.viewPager = this.binding.container;
        this.viewPager.setAdapter(new MainPagerAdapter(this));

        // Tab
        this.tabLayout = this.binding.tabLayout;
        this.tabLayout.setupWithViewPager(viewPager);

        // Drawer
        this.drawerLayout = this.binding.mainDrawer;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, this.toolbar, R.string.app_name, R.string.app_name);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation
        this.navigationView = this.binding.mainDrawerNavigation;
        this.navigationView.setNavigationItemSelectedListener(new MyNavigationItemSelectedListener(this.drawerLayout));
    }
}
