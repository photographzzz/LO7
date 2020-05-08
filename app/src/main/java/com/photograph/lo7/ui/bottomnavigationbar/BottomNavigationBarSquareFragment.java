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
import com.photograph.lo7.ui.bottomnavigationbar.squarecontent.SquareFragmentViewPager;

public class BottomNavigationBarSquareFragment extends Fragment {
    private String[] titles = {"动态", "视频"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);

        TabLayout tabLayout = view.findViewById(R.id.square_tablayout);
        ViewPager viewPager = view.findViewById(R.id.square_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
        SquareFragmentViewPager squareFragmentViewPager =
                new SquareFragmentViewPager(getActivity().getSupportFragmentManager(), titles);
        viewPager.setAdapter(squareFragmentViewPager);


        //表示将TabLayout 和Viewpager 进行关联
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
