package com.photograph.lo7.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentInformationBinding;
import com.photograph.lo7.fragments.home.information_content.InformationFragmentViewPager;
import com.photograph.lo7.util.FragmentUtils;

public class InformationFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = {"推荐", "赛事", "版本", "玩法"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentInformationBinding fragmentInformationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false);

        tabLayout = fragmentInformationBinding.informationTablayout;
        viewPager = fragmentInformationBinding.informationViewpager;

        tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[2]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[3]));
        InformationFragmentViewPager informationFragmentViewPager = new InformationFragmentViewPager(FragmentUtils.context.getSupportFragmentManager(), titles);
        viewPager.setAdapter(informationFragmentViewPager);


        //表示将TabLayout 和Viewpager 进行关联
        tabLayout.setupWithViewPager(viewPager);

        return fragmentInformationBinding.getRoot();
    }
}
