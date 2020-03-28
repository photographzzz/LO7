package com.photograph.lo7.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.base.BaseActivity;
import com.photograph.lo7.databinding.ActivityHomeBinding;
import com.photograph.lo7.databinding.NavigationHeaderBinding;
import com.photograph.lo7.vo.UserVO;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private UserVO userVO = AppHolder.currentUser;
    private CircleImageView circleImageView;
    private ActivityHomeBinding homeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //navController.addOnDestinationChangedListener((controller, destination, arguments) -> Toast.makeText(HomeActivity.this, "onDestinationChanged() called", Toast.LENGTH_SHORT).show());
        //设置底部菜单
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);



        drawerLayout = homeBinding.homeDrawerLayout;
        //homeBinding.homeBottomNavigation.setItemIconTintList(null);

        NavigationView navigationView = homeBinding.homeNavigationView;
//        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.navigation_user_info).setOnClickListener(this);
        NavigationHeaderBinding navHeaderBinding = DataBindingUtil.bind(header);
        //navHeaderBinding.setUser(userVO);
        circleImageView = header.findViewById(R.id.navigation_pic);
        //loadUserPic();


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


   /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
    }*/


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
