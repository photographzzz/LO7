package com.photograph.lo7.ui.bottomnavigationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.photograph.lo7.R;
import com.photograph.lo7.ui.bottomnavigationbar.informationcontent.InformationFragmentViewPager;

public class BottomNavigationBarInformationFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = {"推荐", "赛事", "版本", "玩法"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        tabLayout = view.findViewById(R.id.information_tablayout);
        viewPager = view.findViewById(R.id.information_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[2]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[3]));
        InformationFragmentViewPager informationFragmentViewPager = new InformationFragmentViewPager(getActivity().getSupportFragmentManager(), titles);
        viewPager.setAdapter(informationFragmentViewPager);


        //表示将TabLayout 和Viewpager 进行关联
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
