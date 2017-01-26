package com.feifeier.letmeknow;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mToolbar = findView(R.id.main_activity_toolbar);

        // 让toobar取代actionbar
        // 设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(mToolbar);

        // 设置Drawerlayout开关指示器，即Toolbar最左边的那个icon
        mDrawerLayout = findView(R.id.drawer_layout);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

    }

    private <T> T findView(int resId) {
        return (T) findViewById(resId);
    }
}
