package com.photograph.lo7.ui.userprofilefragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.databinding.FragmentUpdatePicBinding;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.entity.Response;
import com.photograph.lo7.util.BitmapUtils;
import com.photograph.lo7.entity.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.EasyPermissions;
import rxhttp.wrapper.param.RxHttp;

import static android.app.Activity.RESULT_OK;

public class UpdatePicFragment extends Fragment
        implements MenuItem.OnMenuItemClickListener, EasyPermissions.PermissionCallbacks {
    User user = AppHolder.currentUser;
    private File photoPath;//照片路径
    private Uri photoUri;//照片的uri
    private ImageView picImg;
    private String dirPath = AppHolder.userPicPath;


    private static final int OPEN_ALBUM_REQUEST = 1;
    private static final int OPEN_CAMERA_REQUEST = 2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUpdatePicBinding fragmentUpdatePicBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_update_pic, container, false);
        setHasOptionsMenu(true);

        fragmentUpdatePicBinding.setUser(user);
        picImg = fragmentUpdatePicBinding.userPicImg;

        return fragmentUpdatePicBinding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tool_menu_update_pic, menu);
        menu.findItem(R.id.item_choose_pic).setOnMenuItemClickListener(this);
        menu.findItem(R.id.item_take_pic).setOnMenuItemClickListener(this);
        menu.findItem(R.id.item_save_pic).setOnMenuItemClickListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        getPermission();
        switch (item.getItemId()) {
            case R.id.item_choose_pic:
                choosePhoto();
                break;
            case R.id.item_take_pic:
                openCamera();
                break;
            case R.id.item_save_pic:
                Bitmap bitmap = ((BitmapDrawable) picImg.getDrawable()).getBitmap();
                saveFile(bitmap);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveFile(Bitmap bitmap) {
        File dirFile = new File(dirPath);
        String fileName = generateFileName();
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File file = new File(dirPath, fileName);//将要保存图片的路径和图片名称
        try {
            file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(uri);
            getActivity().sendBroadcast(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, OPEN_ALBUM_REQUEST);
    }

    private void openCamera() {
        photoPath = new File(Environment.getExternalStorageDirectory().getPath()
                + "/" + System.currentTimeMillis() + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            photoUri = FileProvider.getUriForFile(getActivity(),
                    "com.photograph.lo7", photoPath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String url = new String();
            
        } else {
            photoUri = Uri.fromFile(photoPath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("photoPath", photoPath);
        getActivity().startActivityForResult(intent, OPEN_CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == OPEN_ALBUM_REQUEST) {
                Uri photoUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);
                    File photo = new File(dirPath, generateFileName());
                    BitmapUtils.bitmap2File(bitmap, photo);
                    updatePic(photo.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == OPEN_CAMERA_REQUEST) {
                String picPath = data.getStringExtra("photoPath");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    picPath = String.valueOf(photoPath);
                } else {
                    picPath = photoUri.getEncodedPath();
                }
                Log.d("拍照返回图片路径:", picPath);
                Glide.with(getActivity()).load(picPath).into(picImg);
                updatePic(picPath);
            }
        }

    }

    private String generateFileName() {
        Calendar now = new GregorianCalendar();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        return user.getUsername() + simpleDate.format(now.getTime());
    }

    private void updatePic(String picPath) {
        RxHttp.postForm("user/update_pic")
                .addFile("pic", new File(picPath))
                .asString()
                .subscribe(s -> {
                    Gson gson = new Gson();
                    Response response = gson.fromJson(s, Response.class);
                    user.setPic((String) response.getData());
                }, (OnError) error -> {
                    error.show(error.getErrorCode() == 0 ? "更换头像成功" : error.getErrorMsg());
                });
    }

    //获取权限
    private void getPermission() {
        String[] permissions = {Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(getActivity(), permissions)) {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(
                    this, "需要获取您的相册、照相使用权限", 4, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(getActivity(), "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }

    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(getActivity(), "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }
}
