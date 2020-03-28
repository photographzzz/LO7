package com.photograph.lo7.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DB db = DataBindingUtil.inflate(inflater, getContentViewId(), container, false);
        initView(db);
        return db.getRoot();
    }


    protected abstract int getContentViewId();


    protected abstract void initView(DB bindView);
}
