package com.photograph.lo7.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.MyInfoRelativeLayoutBinding;
import com.photograph.lo7.httpsender.Url;

import de.hdodenhof.circleimageview.CircleImageView;

// 自定义view
public class MyInfoRelativeLayout extends RelativeLayout {
    private MyInfoRelativeLayoutBinding infoRelativeLayoutBinding;
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
        initView(context,attrs);
    }

    public MyInfoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyInfoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        infoRelativeLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.my_info_relative_layout, null, false);
        // 获取自定义参数 mHint
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.info);
        String hint = array.getString(R.styleable.info_mHint);
        hintTextView = infoRelativeLayoutBinding.infoHintTxt;
        hintTextView.setText(hint);
        picImageView = infoRelativeLayoutBinding.picImg;
        infoTextView = infoRelativeLayoutBinding.infoTxt;
        circleImageView = infoRelativeLayoutBinding.updateForward;


        // 根据此行是不是要显示头像，来设置控件的可见性
        if (hint.equals("头像")) {
            circleImageView.setVisibility(GONE);
            infoTextView.setVisibility(GONE);
        } else {
            picImageView.setVisibility(GONE);
            Glide.with(context).load(R.drawable.ic_forward).into(circleImageView);
        }

        addView(infoRelativeLayoutBinding.getRoot());
    }



    /**
     * @param view 当前的view
     * @param mPic 自定义参数值mPic的值
     */
    @BindingAdapter(value = {"mPic"})
    public static void loadImg(MyInfoRelativeLayout view, String mPic) {
        Glide.with(view)
                .load(mPic.replace("http://image.LO7.com/", Url.imageHost))
                .error(R.drawable.nav_icon)
                .centerCrop()
                .into(view.getPicImageView());
    }

    /**
     * @param view  当前的view
     * @param mInfo 自定义参数值mInfo的值
     */
    @BindingAdapter(value = {"mInfo"})
    public static void loadInfo(MyInfoRelativeLayout view, String mInfo) {
        view.getInfoTextView().setText(mInfo);
    }



    public TextView getInfoTextView() {
        return infoTextView;
    }

    public ImageView getPicImageView() {
        return picImageView;
    }

}