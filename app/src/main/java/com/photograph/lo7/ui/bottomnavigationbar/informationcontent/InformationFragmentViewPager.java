package com.photograph.lo7.ui.bottomnavigationbar.informationcontent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class InformationFragmentViewPager extends FragmentPagerAdapter {

    private String[] mTitles;


    public InformationFragmentViewPager(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new SynthesisInformationFragment();
        } else if (i == 1) {
            return new MatchInformationFragment();
        } else if (i == 2) {
            return new VersionInformationFragment();
        } else if (i == 3) {
            return new PlayingInformationFragment();
        }
        return new SynthesisInformationFragment();
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
