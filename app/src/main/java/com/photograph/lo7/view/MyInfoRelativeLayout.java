package com.photograph.lo7.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.photograph.lo7.R;

import de.hdodenhof.circleimageview.CircleImageView;

// 自定义view
public class MyInfoRelativeLayout extends RelativeLayout {
    // 提示框
    private TextView hintTextView;
    // 信息框
    private TextView infoTextView;
    // 显示头像
    private ImageView picImageView;
    // 显示前进导航
    private CircleImageView circleImageView;

    public MyInfoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.my_info_relative_layout, this);

        // 获取自定义参数 mHint
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.info);
        String hint = array.getString(R.styleable.info_mHint);
        hintTextView = findViewById(R.id.info_hint_txt);
        hintTextView.setText(hint);
        infoTextView = findViewById(R.id.info_txt);
        picImageView = findViewById(R.id.pic_img);
        circleImageView = findViewById(R.id.update_forward);


        // 根据此行是不是要显示头像，来设置控件的可见性
        if (hint.equals("头像")) {
            circleImageView.setVisibility(GONE);
            infoTextView.setVisibility(GONE);
        }else {
            picImageView.setVisibility(GONE);
            Glide.with(context).load(R.drawable.ic_forward).into(circleImageView);
        }
    }

    public TextView getInfoTextView() {
        return infoTextView;
    }

    public ImageView getPicImageView() {
        return picImageView;
    }


    /**
     *
     * @param view 当前的view
     * @param mPic 自定义参数值mPic的值
     */
    @BindingAdapter(value = {"mPic"})
    public static void loadImg(MyInfoRelativeLayout view, String mPic) {
        Glide.with(view)
                .load(mPic.replace("image.LO7.com", "192.168.0.102"))
                .placeholder(R.drawable.nav_icon)
                .error(R.drawable.nav_icon)
                .centerCrop()
                .into(view.getPicImageView());
    }

    /**
     * @param view 当前的view
     * @param mInfo 自定义参数值mInfo的值
     */
    @BindingAdapter(value = {"mInfo"})
    public static void loadInfo(MyInfoRelativeLayout view, String mInfo) {
        view.getInfoTextView().setText(mInfo);
    }

}