package com.photograph.lo7.base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.photograph.lo7.R;


public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    private ToolBarHelper mToolBarHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB db = DataBindingUtil.setContentView(this, getContentViewId());
        initView(db);
        Toolbar toolbar = findViewById(R.id.toolbar);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            mToolBarHelper = new ToolBarHelper(toolbar);
            handleToolBar(mToolBarHelper);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_backup);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    /**
     * 子类去实现
     */
    protected abstract void handleToolBar(ToolBarHelper toolBarHelper);

    protected abstract int getContentViewId();

    protected abstract void initView(DB bindView);


    public static class ToolBarHelper {

        private Toolbar mToolbar;

        public ToolBarHelper(Toolbar toolbar) {
            mToolbar = toolbar;
        }

        public Toolbar getToolbar() {
            return mToolbar;
        }

        public void setTitle(String title) {
            mToolbar.setTitle(title);
        }
    }
}
