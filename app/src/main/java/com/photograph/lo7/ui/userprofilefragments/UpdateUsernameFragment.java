package com.photograph.lo7.ui.userprofilefragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.UserController;
import com.photograph.lo7.databinding.FragmentUpdateUsernameBinding;
import com.photograph.lo7.entity.User;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.view.MyEditText;
import com.rxjava.rxlife.RxLife;

public class UpdateUsernameFragment extends Fragment implements MenuItem.OnMenuItemClickListener {
    private MyEditText updateUsernameTxt;
    private User user;
    private FragmentUpdateUsernameBinding updateUsernameBinding;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        updateUsernameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_username, container, false);
        user = AppHolder.currentUser;
        updateUsernameTxt = updateUsernameBinding.updateUsernameTxt;
        updateUsernameTxt.setText(user.getUsername());
        updateUsernameTxt.requestFocus();
        setHasOptionsMenu(true);

        return updateUsernameBinding.getRoot();
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(hasMenu);
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
            String username = updateUsernameTxt.getText().toString();
            UserController.INSTANCE.updateUsername(username)
                    .as(RxLife.asOnMain(this))
                    .subscribe(result ->{
                        user.setUsername(username);
                        Navigation.findNavController(updateUsernameBinding.getRoot()).navigateUp();
                    }, (OnError) error -> {
                        error.show(error.getErrorCode() == 0 ? "修改成功" : "修改失败");
                    });
        }
        return super.onOptionsItemSelected(item);
    }
}
