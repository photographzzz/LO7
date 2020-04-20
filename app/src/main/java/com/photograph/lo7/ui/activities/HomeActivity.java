package com.photograph.lo7.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.NavigationHeaderBinding;
import com.photograph.lo7.ui.userprofilefragments.UpdateUserProfileActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 设置侧拉导航栏
        setSupportActionBar(findViewById(R.id.toolbar));
        DrawerLayout drawerLayout = findViewById(R.id.home_drawer_layout);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_drawer_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationView navigationView = findViewById(R.id.home_drawer_navigation);
        NavigationUI.setupWithNavController(navigationView, navController);

        // 设置导航栏头部
        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.navigation_user_info).setOnClickListener(this);
        NavigationHeaderBinding navHeaderBinding = DataBindingUtil.bind(header);

        // 绑定用户
        navHeaderBinding.setUser(AppHolder.currentUser);
    }


    //左上角的菜单被点击时调用到
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_drawer_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }


    // 侧拉导航栏头部被单击事件
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.navigation_user_info) {
            Intent intent = new Intent(HomeActivity.this, UpdateUserProfileActivity.class);
            startActivity(intent);
        }
    }


}
