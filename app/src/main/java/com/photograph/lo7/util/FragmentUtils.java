package com.photograph.lo7.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.photograph.lo7.ui.fragments.LoginFragment;
import com.photograph.lo7.ui.fragments.RegisterFragment;
import com.photograph.lo7.ui.bottomnavigationbar.BottomNavigationBarInformationFragment;
import com.photograph.lo7.ui.bottomnavigationbar.BottomNavigationBarMallFragment;
import com.photograph.lo7.ui.bottomnavigationbar.BottomNavigationBarPersonFragment;
import com.photograph.lo7.ui.bottomnavigationbar.BottomNavigationBarSquareFragment;

public class FragmentUtils {
    public static AppCompatActivity context;

    public static final String LOGIN_FRAGMENT_TAG = "LOGIN";
    public static final String REGISTER_FRAGMENT_TAG = "REGISTER";

    public static final String INFORMATION_FRAGMENT_TAG = "INFORMATION";
    public static final String SQUARE_FRAGMENT_TAG = "SQUARE";
    public static final String MALL_FRAGMENT_TAG = "MALL";
    public static final String PERSON_FRAGMENT_TAG = "PERSON";

    public static void attachFragment(AppCompatActivity context, String fragmentTag, int replaced) {
        if (FragmentUtils.context == null || !FragmentUtils.context.equals(context)) {
            FragmentUtils.context = context;
        }


        //step1;获取Fragment管理器
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (fragment == null) {
            //管理器没有这个fragment
            if (fragmentTag.equals(LOGIN_FRAGMENT_TAG)) {
                fragment = new LoginFragment();
            } else if (fragmentTag.equals(REGISTER_FRAGMENT_TAG)) {
                fragment = new RegisterFragment();
            } else if (fragmentTag.equals(INFORMATION_FRAGMENT_TAG)) {
                fragment = new BottomNavigationBarInformationFragment();
            } else if (fragmentTag.equals(SQUARE_FRAGMENT_TAG)) {
                fragment = new BottomNavigationBarSquareFragment();
            } else if (fragmentTag.equals(MALL_FRAGMENT_TAG)) {
                fragment = new BottomNavigationBarMallFragment();
            } else if (fragmentTag.equals(PERSON_FRAGMENT_TAG)) {
                fragment = new BottomNavigationBarPersonFragment();
            }

        }
        //添加Fragment
        fragmentTransaction.add(fragment, fragmentTag);
        fragmentTransaction.replace(replaced, fragment, fragmentTag);
        fragmentTransaction.commit();
    }

}
