package com.photograph.lo7.ui.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.MomentController;
import com.photograph.lo7.controller.UploadController;
import com.photograph.lo7.databinding.ActivityPostMomentBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.rxjava.rxlife.RxLife;
import com.wildma.pictureselector.FileUtils;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

public class PostMomentActivity extends AppCompatActivity {

    private ActivityPostMomentBinding postMomentBinding;
    private Uri imageUri;
    private EditText momentContentEdit;
    private Button momentImageBtn;
    private ImageView momentImageImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postMomentBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_moment);

        setSupportActionBar(postMomentBinding.postMomentToolbar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        momentContentEdit = postMomentBinding.momentContentEdit;
        momentImageImg = postMomentBinding.momentImageImg;
        momentImageBtn = postMomentBinding.momentImageBtn;
        momentImageBtn.setOnClickListener(v -> PictureSelector
                .create(PostMomentActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 200, 200, 1, 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu_update_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

     @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_verify:
                UploadController.INSTANCE.upload(FileUtils.getFileByPath(imageUri.getPath()))
                        .as(RxLife.asOnMain(this))
                        .subscribe(imagePath -> {
                            String content = momentContentEdit.getText().toString();
                            if (TextUtils.isEmpty(content)) {
                                Tip.show("动态内容不能为空");
                                return;
                            }
                            MomentController.INSTANCE.postMoment(content, imagePath).
                                    as(RxLife.asOnMain(this))
                                    .subscribe(moment -> {
                                        Tip.show("发布成功");
                                        Intent intent = new Intent();
                                        intent.putExtra("moment", moment);
                                        setResult(200, intent);
                                        finish();
                                    }, (OnError) error -> {
                                        error.show(error.getErrorMsg());
                                    });
                        }, (OnError) error -> {
                            error.show(error.getErrorMsg());
                        });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                imageUri = pictureBean.getUri();
                momentImageBtn.setText("更换图片");

                if (pictureBean.isCut()) {
                    momentImageImg.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                } else {
                    momentImageImg.setImageURI(pictureBean.getUri());
                }

                //使用 Glide 加载图片
                Glide.with(this)
                        .load(pictureBean.isCut() ? pictureBean.getPath() : pictureBean.getUri())
                        .apply(RequestOptions.centerCropTransform()).into(momentImageImg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        FileUtils.deleteAllCacheImage(this);
        super.onDestroy();
    }
}
