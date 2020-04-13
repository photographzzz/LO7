package com.photograph.lo7.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.MyArticleBottomViewBinding;

public class MyArticleBottomView extends LinearLayout implements View.OnClickListener {
    private MyArticleBottomViewBinding articleBottomViewBinding;
    private EditText commentEdit;
    private LinearLayout commentLL;
    private LinearLayout starLL;
    private LinearLayout likeLL;


    public MyArticleBottomView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initView(context,attrs);
    }

    public MyArticleBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyArticleBottomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        articleBottomViewBinding = DataBindingUtil.inflate(inflater, R.layout.my_article_bottom_view, null, false);
        commentEdit = articleBottomViewBinding.articleCommentEdit;
        commentEdit.setOnClickListener(this);
        commentLL = articleBottomViewBinding.articleCommentLl;
        commentLL.setOnClickListener(this);
        starLL = articleBottomViewBinding.articleStarLl;
        starLL.setOnClickListener(this);
        likeLL = articleBottomViewBinding.articleLikeLl;
        likeLL.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_comment_ll:
                String comment = commentEdit.getText().toString();
                break;
            case R.id.article_star_ll:
                break;
            case R.id.article_like_ll:
                break;
            default:
                break;
        }
    }
}
