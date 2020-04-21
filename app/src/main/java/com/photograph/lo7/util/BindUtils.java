package com.photograph.lo7.util;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.photograph.lo7.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class BindUtils {
    // 与HomeActivity的侧拉导航栏头部CircleImageView保持绑定，当用户pic字段变化时，自动刷新头像


    @BindingAdapter({"img"})
    public static void loadImage(ImageView view, String src) {
        Glide.with(view.getContext()).load(src)
                .error(R.drawable.nav_icon)
                .into(view);
    }

    @BindingAdapter({"cirImg"})
    public static void loadFriendPic(CircleImageView view, String src) {
            Glide.with(view.getContext()).load(src)
                    .error(R.drawable.nav_icon)
                    .into(view);

    }

    @BindingAdapter("followBtnImg")
    public static void loadFollowButton(ImageButton view, boolean hasBennFollowed) {
        Glide.with(view).load(view.getContext().getResources()
                .getDrawable(hasBennFollowed ? R.drawable.ic_follow_checked : R.drawable.ic_follow)).into(view);
    }

   /* @BindingAdapter("commentLikeBtn")
    public static void loadCommentLikeBtn(ImageButton view, boolean hasLike) {
        Glide.with(view).load(view.getContext().getResources()
                .getDrawable(hasLike ? R.drawable.ic_like_24dp : R.drawable.ic_like_border_24dp)).into(view);
    }*/
}
