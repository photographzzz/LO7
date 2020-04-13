package com.photograph.lo7.ui.userprofilefragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentUpdateGenderBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.entity.User;

import rxhttp.wrapper.param.RxHttp;

public class UpdateGenderFragment extends Fragment implements MenuItem.OnMenuItemClickListener {
    private FragmentUpdateGenderBinding updateGenderBinding;
    private User user;
    private RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        updateGenderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_gender, container, false);
        user = AppHolder.currentUser;
        radioGroup = updateGenderBinding.genderRg;
        RadioButton maleRB = updateGenderBinding.maleRb;
        RadioButton femaleRB = updateGenderBinding.femaleRb;
        if (user.getGender() == 0) {
            maleRB.requestFocus();
        } else {
            femaleRB.requestFocus();
        }
        setHasOptionsMenu(true);

        return updateGenderBinding.getRoot();
    }


    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tool_menu_update_info, menu);
        menu.findItem(R.id.item_verify).setOnMenuItemClickListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.item_verify) {
            Integer gender = radioGroup.getCheckedRadioButtonId() == R.id.male_rb ? 0 : 1;

            RxHttp.postForm("user/update_gender")
                    .add("gender", gender)
                    .asResponse(String.class)
                    .subscribe(s -> {
                        user.setGender(gender);
                        Navigation.findNavController(updateGenderBinding.getRoot()).navigateUp();
                    }, (OnError) error -> {
                        error.show(error.getErrorCode() == 0 ? "修改成功" : "修改失败");
                    });
        }
        return super.onOptionsItemSelected(item);
    }

}
