package com.photograph.lo7.util;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.photograph.lo7.R;
import com.photograph.lo7.httpsender.Url;

import de.hdodenhof.circleimageview.CircleImageView;

public class BindUtils {
    // 与HomeActivity的侧拉导航栏头部CircleImageView保持绑定，当用户pic字段变化时，自动刷新头像
    @BindingAdapter({"urlCirImg"})
    public static void loadImage(CircleImageView view, String src) {
        Glide.with(view.getContext()).load(Uri.parse(src.replace("http://image.LO7.com/", Url.imageHost)))
                .error(R.drawable.nav_icon)
                .centerCrop()
                .into(view);
    }

    @BindingAdapter({"urlImg"})
    public static void loadImage(ImageView view, String src) {
        Glide.with(view.getContext()).load(Uri.parse(src.replace("http://image.LO7.com/", Url.imageHost)))
                .error(R.drawable.nav_icon)
                .into(view);
    }

    @BindingAdapter({"preview"})
    public static void loadPreview(ImageView view, String src) {
        Glide.with(view.getContext()).load(Uri.parse(src.replace("http://image.LO7.com/", Url.imageHost)))
                .error(R.drawable.nav_icon)
                .into(view);
    }

    @BindingAdapter({"section_img"})
    public static void loadSectionImage(ImageView view, String src) {
        Glide.with(view.getContext()).load(Uri.parse(src.replace("http://image.LO7.com/", Url.imageHost)))
                .error(R.drawable.nav_icon)
                .into(view);
    }

    @BindingAdapter({"friend_pic"})
    public static void loadFriendPic(CircleImageView view, String src) {
        if (!TextUtils.isEmpty(src)) {
            Glide.with(view.getContext()).load(Uri.parse(src.replace("http://image.LO7.com/", Url.imageHost)))
                    .error(R.drawable.nav_icon)
                    .into(view);
        }
    }

    @BindingAdapter("follow_btn_img")
    public static void loadFollowButton(ImageButton view, boolean hasBennFollowed) {
        Glide.with(view).load(view.getContext().getResources()
                .getDrawable(hasBennFollowed ? R.drawable.ic_follow_checked : R.drawable.ic_follow)).into(view);
    }
}
