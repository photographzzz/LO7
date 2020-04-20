package com.photograph.lo7.ui.userprofilefragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.ActivityUpdateProfileBinding;

import java.util.List;

public class UpdateUserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUpdateProfileBinding updateProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);

        NavController navController = Navigation.findNavController(this, R.id.nav_update_info_fragment);

        Toolbar toolbar = updateProfileBinding.getRoot().findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_host_drawer_fragment, R.id.nav_update_info_fragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener(((controller, destination, arguments) -> {
            toolbar.getMenu().clear();
        }));
    }


    // 为了使更新用户头像的Fragment在拍完照片后可以调用onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getFragment(UpdatePicFragment.class).onActivityResult(requestCode, resultCode, data);
    }


    // 在NavigationUI中获取fragment
    private Fragment getFragment(Class<?> clazz) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            NavHostFragment navHostFragment = (NavHostFragment) fragments.get(0);
            List<Fragment> childfragments = navHostFragment.getChildFragmentManager().getFragments();
            if (childfragments != null && childfragments.size() > 0) {
                for (int j = 0; j < childfragments.size(); j++) {
                    Fragment fragment = childfragments.get(j);
                    if (fragment.getClass().isAssignableFrom(clazz)) {
                        Log.i("Activity", "getFragment1: " + fragment);
                        return fragment;
                    }
                }
            }
        }
        return null;
    }
}
