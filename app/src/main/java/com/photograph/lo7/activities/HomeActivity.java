package com.photograph.lo7.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.base.BaseActivity;
import com.photograph.lo7.databinding.ActivityHomeBinding;
import com.photograph.lo7.databinding.NavigationHeaderBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.entity.Response;
import com.photograph.lo7.util.FragmentUtils;
import com.photograph.lo7.vo.UserVO;

import de.hdodenhof.circleimageview.CircleImageView;
import rxhttp.wrapper.param.RxHttp;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> implements BottomNavigationView.OnNavigationItemSelectedListener,  View.OnClickListener {
    private DrawerLayout drawerLayout;
    private Menu menu;
    private UserVO userVO = AppHolder.currentUser;
    private CircleImageView circleImageView;
    private ActivityHomeBinding homeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menu = homeBinding.homeBottomNavigation.getMenu();

        drawerLayout = homeBinding.homeDrawerLayout;
        homeBinding.homeBottomNavigation.setItemIconTintList(null);
        homeBinding.homeBottomNavigation.setOnNavigationItemSelectedListener(this);


        NavigationView navigationView = homeBinding.homeNavigationView;
//        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.navigation_user_info).setOnClickListener(this);
        NavigationHeaderBinding navHeaderBinding = DataBindingUtil.bind(header);
        navHeaderBinding.setUser(userVO);
        circleImageView = header.findViewById(R.id.navigation_pic);
        loadUserPic();


        FragmentUtils.attachFragment(this, FragmentUtils.INFORMATION_FRAGMENT_TAG, R.id.home_content);
    }

  /*
    状态栏的换一个settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
*/


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    protected void handleToolBar(ToolBarHelper toolBarHelper) {
        Toolbar toolbar = toolBarHelper.getToolbar();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(ActivityHomeBinding bindView) {
        homeBinding = bindView;

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_information:
                restMenuItemToDefaultIcon();
                item.setIcon(R.drawable.ic_info_selected);
                FragmentUtils.attachFragment(HomeActivity.this, FragmentUtils.INFORMATION_FRAGMENT_TAG, R.id.home_content);
                break;
            case R.id.bottom_square:
                restMenuItemToDefaultIcon();
                item.setIcon(R.drawable.ic_square_selected);
                FragmentUtils.attachFragment(HomeActivity.this, FragmentUtils.SQUARE_FRAGMENT_TAG, R.id.home_content);
                break;
            case R.id.bottom_mall:
                restMenuItemToDefaultIcon();
                item.setIcon(R.drawable.ic_mall_selected);
                FragmentUtils.attachFragment(HomeActivity.this, FragmentUtils.MALL_FRAGMENT_TAG, R.id.home_content);
                break;
            case R.id.bottom_person:
                restMenuItemToDefaultIcon();
                FragmentUtils.attachFragment(HomeActivity.this, FragmentUtils.PERSON_FRAGMENT_TAG, R.id.home_content);
                break;
            case R.id.nav_exit:
                RxHttp.get("user/exit")
                        .asResponse(Response.class)
                        .subscribe(response -> {
                            finish();
                        }, (OnError) error -> {
                            error.show("退出失败");
                        });
                break;
        }


        return true;
    }

    private void restMenuItemToDefaultIcon() {
        menu.findItem(R.id.bottom_information).setIcon(R.drawable.ic_info);
        menu.findItem(R.id.bottom_square).setIcon(R.drawable.ic_square);
        menu.findItem(R.id.bottom_mall).setIcon(R.drawable.ic_mall);
    }


    private void loadUserPic() {
        Glide.with(this).load(Uri.parse(userVO.getPic().replace("image.LO7.com", "192.168.0.102")))
                .disallowHardwareConfig()
                .placeholder(R.mipmap.ic_launcher).into(circleImageView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigation_user_info:
                Intent intent = new Intent(HomeActivity.this, UpdateUserInfoActivity.class);
                startActivity(intent);
        }
    }
}
