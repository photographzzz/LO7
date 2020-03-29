package com.photograph.lo7.ui.userinfofragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentUpdateInfoBinding;

public class UpdateInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUpdateInfoBinding updateInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_info, container, false);
        View view = updateInfoBinding.getRoot();


        updateInfoBinding.updatePic.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updatePicFragment));
        updateInfoBinding.updateUsername.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateUsernameFragment));
        updateInfoBinding.updateGender.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateGenderFragment));
        updateInfoBinding.updateEmail.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateEmailFragment));
        updateInfoBinding.updatePhone.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updatePhoneFragment));
        updateInfoBinding.updateBio.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateBioFragment));

        updateInfoBinding.setUser(AppHolder.currentUser);

        return view;
    }
}
