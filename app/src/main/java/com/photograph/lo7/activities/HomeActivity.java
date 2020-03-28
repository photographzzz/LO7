package com.photograph.lo7.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.NavigationHeaderBinding;
import com.photograph.lo7.vo.UserVO;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private UserVO userVO = AppHolder.currentUser;
    private CircleImageView circleImageView;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setSupportActionBar(findViewById(R.id.toolbar));
        DrawerLayout drawerLayout = findViewById(R.id.home_drawer_layout);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_drawer_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        //设置左侧菜单
        NavigationView navigationView = findViewById(R.id.home_drawer_navigation);
        NavigationUI.setupWithNavController(navigationView, navController);


        userVO = new UserVO();
        userVO.setBio("3231233");
        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.navigation_user_info).setOnClickListener(this);
        NavigationHeaderBinding navHeaderBinding = DataBindingUtil.bind(header);
        navHeaderBinding.setUser(userVO);
        circleImageView = header.findViewById(R.id.navigation_pic);
        loadUserPic();
    }


  /*
    状态栏的换一个settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
*/


    /**
     * 左上角的菜单被点击时调用到
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_drawer_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }


    private void loadUserPic() {
       /* Glide.with(this).load(Uri.parse(userVO.getPic().replace("image.LO7.com", "192.168.0.102")))
                .disallowHardwareConfig()
                .placeholder(R.mipmap.ic_launcher).into(circleImageView);*/
        Glide.with(this).load(R.drawable.ic_backup).into(circleImageView);
    }

    @Override
    public void onClick(View v) {

    }
}
