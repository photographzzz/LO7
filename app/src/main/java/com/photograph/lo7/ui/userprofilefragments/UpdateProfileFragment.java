package com.photograph.lo7.ui.userprofilefragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentUpdateProfileBinding;

public class UpdateProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUpdateProfileBinding updateProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile, container, false);
        View view = updateProfileBinding.getRoot();

        updateProfileBinding.updatePicFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updatePicFragment));
        updateProfileBinding.updateUsernameFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateUsernameFragment));
        updateProfileBinding.updateGenderFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateGenderFragment));
        updateProfileBinding.updateEmailFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateEmailFragment));
        updateProfileBinding.updatePhoneFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updatePhoneFragment));
        updateProfileBinding.updateBioFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_updateBioFragment));

        return view;
    }
}
