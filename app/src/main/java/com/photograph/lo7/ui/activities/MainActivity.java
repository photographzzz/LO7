package com.photograph.lo7.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.photograph.lo7.R;
import com.photograph.lo7.util.FragmentUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        FragmentUtils.attachFragment(this, FragmentUtils.LOGIN_FRAGMENT_TAG, R.id.main_content);
    }
}
