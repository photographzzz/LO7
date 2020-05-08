package com.photograph.lo7.util;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.photograph.lo7.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    public static void loadFollowImageBtn(ImageButton view, boolean hasBennFollowed) {
        Glide.with(view).load(view.getContext().getResources()
                .getDrawable(hasBennFollowed ? R.drawable.ic_follow_checked : R.drawable.ic_follow)).into(view);
    }

    @BindingAdapter("followFloatingBtnImg")
    public static void loadFollowFloatingBtn(FloatingActionButton view, boolean hasBennFollowed) {
        Glide.with(view).load(view.getContext().getResources()
                .getDrawable(hasBennFollowed ? R.drawable.ic_follow_checked : R.drawable.ic_follow)).into(view);
    }



    @BindingAdapter("visitableSrc")
    public static void loadVisitableSrc(WebView webView, String src) {
        Document parse = Jsoup.parse(src);
        Elements imgs = parse.getElementsByTag("img");
        if (!imgs.isEmpty()) {
            for (Element e : imgs) {
                imgs.attr("width", "100%");
                imgs.attr("height", "auto");
            }
        }
        Elements videos = parse.getElementsByTag("video");
        if (!videos.isEmpty()) {
            for (Element e : videos) {
                videos.attr("width", "100%");
                videos.attr("height", "auto");
            }
        }
        String content = parse.toString();
        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    @BindingAdapter("commentHint")
    public static void loadCommentHint(TextView textView, int commentCount) {
        if (commentCount == 0) {
            textView.setText("暂时还没有评论");
            textView.setVisibility(View.VISIBLE);
        }else {
            textView.setVisibility(View.GONE);
        }
    }
}
