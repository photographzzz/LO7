package com.photograph.lo7.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.FragmentRegisterBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.util.FragmentUtils;
import com.rxjava.rxlife.RxLife;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding registerBinding;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);

        TextView usernameText = registerBinding.registerEditUsername;
        TextView passwordText = registerBinding.registerEditPassword;
        TextView genderText = registerBinding.registerEditGender;
        TextView emailText = registerBinding.registerEditEmail;
        TextView phoneText = registerBinding.registerEditPhone;

        Button registerBtn = registerBinding.registerBtnRegister;
        Button loginBtn = registerBinding.registerBtnLogin;


        registerBtn.setOnClickListener(v -> {
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            int gender = Integer.parseInt(genderText.getText().toString());
            String email = emailText.getText().toString();
            String phone = phoneText.getText().toString();

            UserController.getInstance().register(username, password, gender, email, phone)
                    .as(RxLife.asOnMain(this))
                    .subscribe(user -> {
                        AppHolder.currentUser = user;
                        FragmentUtils.attachFragment(FragmentUtils.context, FragmentUtils.LOGIN_FRAGMENT_TAG, R.id.main_content);
                        loginBtn.performClick();
                    }, (OnError) error -> {
                        error.show(error.getErrorMsg());
                    });
        });


        loginBtn.setOnClickListener(v -> {
            FragmentUtils.attachFragment(FragmentUtils.context, FragmentUtils.LOGIN_FRAGMENT_TAG, R.id.main_content);
        });


        return registerBinding.getRoot();
    }

}
