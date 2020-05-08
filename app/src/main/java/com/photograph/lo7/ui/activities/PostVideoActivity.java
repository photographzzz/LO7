package com.photograph.lo7.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.UploadController;
import com.photograph.lo7.controller.VideoController;
import com.photograph.lo7.databinding.ActivityPostVideoBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.photograph.lo7.util.GlideEngine;
import com.rxjava.rxlife.RxLife;
import com.wildma.pictureselector.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class PostVideoActivity extends AppCompatActivity {

    private ActivityPostVideoBinding postVideoBinding;
    private EditText videoTitleEdit;
    private Button videoBtn;
    private VideoView videoVV;
    private String videoLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postVideoBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_video);

        setSupportActionBar(postVideoBinding.postVideoToolbar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        videoTitleEdit = postVideoBinding.videoTitleEdit;
        videoVV = postVideoBinding.videoVv;
        videoBtn = postVideoBinding.videoBtn;
        videoBtn.setOnClickListener(v -> {
            EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                    .setVideo(true)
                    .setVideoCount(1)
                    .setCount(1)
                    .setGif(false)
                    .setPictureCount(0)
                    .setFileProviderAuthority("com.huantansheng.easyphotos.demo.fileprovider")
                    .setCount(22)
                    .start(new SelectCallback() {
                        @Override
                        public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                            videoLocation = photos.get(0).path;

                        }
                    });
        });
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //录像选择返回事件
            case 0:
                if (resultCode == RESULT_OK) {
                    videoLocation = data.getStringArrayListExtra("pickerPaths").get(0);
                    videoBtn.setText("更换视频");
                }
                break;
        }
    }
*/

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
                UploadController.INSTANCE.upload(new File(videoLocation))
                        .as(RxLife.asOnMain(this))
                        .subscribe(videoPath -> {
                            String title = videoTitleEdit.getText().toString();
                            if (TextUtils.isEmpty(title)) {
                                Tip.show("视频标题不能为空");
                                return;
                            }
                            VideoController.INSTANCE.postVideo(title, videoPath)
                                    .as(RxLife.asOnMain(this))
                                    .subscribe(video -> {
                                        Intent intent = new Intent();
                                        intent.putExtra("video", video);
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
    protected void onDestroy() {
        FileUtils.deleteAllCacheImage(this);
        super.onDestroy();
    }
}
