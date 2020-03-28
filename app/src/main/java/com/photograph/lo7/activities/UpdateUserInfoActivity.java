package com.photograph.lo7.activities;

import android.os.Bundle;

import com.photograph.lo7.base.BaseActivity;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.ActivityUpdateUserInfoBinding;

public class UpdateUserInfoActivity extends BaseActivity<ActivityUpdateUserInfoBinding> {
    private ActivityUpdateUserInfoBinding updateUserInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    protected void handleToolBar(ToolBarHelper toolBarHelper) {
        toolBarHelper.setTitle("修改个人信息");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_update_user_info;
    }

    @Override
    protected void initView(ActivityUpdateUserInfoBinding bindView) {
        updateUserInfoBinding = bindView;
    }
}
