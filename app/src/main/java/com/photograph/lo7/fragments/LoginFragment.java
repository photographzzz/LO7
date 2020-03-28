package com.photograph.lo7.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.activities.HomeActivity;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentLoginBinding;
import com.photograph.lo7.util.FragmentUtils;
import com.photograph.lo7.vo.UserVO;

import rxhttp.wrapper.param.RxHttp;

public class LoginFragment extends Fragment {

    FragmentLoginBinding loginBinding;
    Context context;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {

            } else {
                Toast.makeText(context, msg.getData().getString("msg"), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        TextView usernameText = loginBinding.loginEditUsername;
        TextView passwordText = loginBinding.loginEditPassword;
        Button loginBtn = loginBinding.loginBtnLogin;
        Button registerBtn = loginBinding.loginBtnRegister;

        loginBtn.setOnClickListener(v -> {
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            //String url = getString(R.string.host) + "user/login?username=" + username + "&password=" + password;
            //OkHttpUtils.get(url, new OkHttpCallback());


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
