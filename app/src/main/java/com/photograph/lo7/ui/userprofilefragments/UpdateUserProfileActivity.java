package com.photograph.lo7.ui.userprofilefragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.ActivityUpdateProfileBinding;

public class UpdateUserProfileActivity extends AppCompatActivity {
    @SuppressLint("CheckResult")
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

}
