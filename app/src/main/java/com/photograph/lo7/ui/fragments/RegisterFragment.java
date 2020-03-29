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
import com.photograph.lo7.databinding.FragmentRegisterBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.util.FragmentUtils;
import com.photograph.lo7.vo.UserVO;

import rxhttp.wrapper.param.RxHttp;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding registerBinding;
  /*  Context context;
    final Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
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
    }*/

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

            RxHttp.postForm("user/register?")
                    .add("username", username)
                    .add("password", password)
                    .add("gender", gender)
                    .add("email", email)
                    .add("phone", phone)
                    .asResponse(UserVO.class)
                    .subscribe(userVO -> {
                        AppHolder.currentUser = userVO;
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
