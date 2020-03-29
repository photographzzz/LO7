package com.photograph.lo7.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.photograph.lo7.databinding.FragmentLoginBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.ui.activities.HomeActivity;
import com.photograph.lo7.util.FragmentUtils;
import com.photograph.lo7.vo.UserVO;

import rxhttp.wrapper.param.RxHttp;

public class LoginFragment extends Fragment {

    FragmentLoginBinding loginBinding;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        TextView usernameText = loginBinding.loginEditUsername;
        TextView passwordText = loginBinding.loginEditPassword;
        Button loginBtn = loginBinding.loginBtnLogin;
        Button registerBtn = loginBinding.loginBtnRegister;

        loginBtn.setOnClickListener(v -> {
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();

            RxHttp.postForm("user/login?")
                    .add("username", username)
                    .add("password", password)
                    .asResponse(UserVO.class)
                    .subscribe(userVO -> {
                        AppHolder.currentUser = userVO;
                        Intent intent = new Intent(context, HomeActivity.class);
                        startActivity(intent);
                    }, (OnError) error -> {
                        error.show(error.getErrorMsg());
                    });

        });

        registerBtn.setOnClickListener(v -> {
            FragmentUtils.attachFragment(FragmentUtils.context, FragmentUtils.REGISTER_FRAGMENT_TAG, R.id.main_content);
        });

        return loginBinding.getRoot();
    }

}
