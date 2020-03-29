package com.photograph.lo7.ui.userinfofragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.photograph.lo7.R;

public class UpdateUserInfoActivity extends AppCompatActivity {
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        NavController navController = Navigation.findNavController(this, R.id.nav_update_info_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_host_drawer_fragment, R.id.nav_update_info_fragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);





     /*   UserVO userVO = AppHolder.currentUser;
        updateUserInfoBinding.updateInfoCommitBtn.setOnClickListener(v -> {
            String bio = updateUserInfoBinding.updateInfoEditBio.getText().toString();
            String email = updateUserInfoBinding.updateInfoEditEmail.getText().toString();
            String phone = updateUserInfoBinding.updateInfoEditPhone.getText().toString();
            Integer gender = Integer.parseInt(updateUserInfoBinding.updateInfoEditGender.getText().toString());

            RxHttp.postForm("user/update_info")
                    .add("id", userVO.getId())
                    .add("gender", gender)
                    .add("email", email)
                    .add("phone", phone)
                    .add("bio", bio)
                    .asResponse(UserVO.class)
                    .subscribe(newUser -> {
                        convertUser(userVO, newUser);
                        Tip.show("修改成功", false);
                        finish();
                    }, (OnError) error -> {
                        error.show(error.getErrorMsg());
                    });
        });
*/
    }

}
