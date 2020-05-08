package com.photograph.lo7.ui.bottomnavigationbar.squarecontent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SquareFragmentViewPager extends FragmentPagerAdapter {

    private String[] mTitles;


    public SquareFragmentViewPager(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new MomentSquareFragment();
        } else {
            return new VideoSquareFragment();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }


    /*
    *
    * 该函数是搭配TabLayout 布局所需重写的 ,如若不绑定TabLayout 布局，那么可以不重写
    *   mTl.setupWithViewPager(mVp);
    * */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
