package com.photograph.lo7.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.photograph.lo7.R;

public class UpdateUserInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_user_info, container, false);
    }

  /*@Override
    protected void handleToolBar(ToolBarHelper toolBarHelper) {
        toolBarHelper.setTitle("修改个人信息");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_update_user_info;
    }

    @Override
    protected void initView(ActivityUpdateUserInfoBinding bindView) {
        updateUserInfoBinding = bindView;
    }*/
}
