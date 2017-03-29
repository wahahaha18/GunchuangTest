package com.example.circle;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
//    private RxPermissions rxPermissions;
    private SharedPreferences sharedPreferences;
//    private MyfragmentPresenter myfragmentPresenter;

//    LoginResponse.BBean user;
    private String number = "10086";
//    private MyPageItemAdapter myPageItemAdapter;
    private RxPermissions rxPermissions;
    private static final int CAMERA_CODE = 1;
    private static final int GALLERY_CODE = 2;
    private static final int CROP_CODE = 3;
    private File img;
    private boolean isSelectPic;
    private Uri imageUriData;
    private int userId;
    private SimpleDraweeView toolbarHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        SharedPreferences image = getSharedPreferences("image", MODE_PRIVATE);
        image.getString("imgs","");
        toolbarHead = (SimpleDraweeView) findViewById(R.id.user_avator);
        toolbarHead.setImageURI("http://111.160.54.27:6300/Content/face/20170328/131351380821240702.png");

        toolbarHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("选择图片")
                        .setSingleChoiceItems(new String[]{"拍照", "相册"}, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0://拍照
                                        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                .subscribe(new Consumer<Boolean>() {
                                                    @Override
                                                    public void accept(Boolean aBoolean) throws Exception {
                                                        if (aBoolean) {
                                                            chooseFromCamera();
                                                        } else {
                                                            Toast.makeText(MainActivity.this, "请授予相机和存储权限", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                        break;
                                    case 1://相册
                                        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                .subscribe(new Consumer<Boolean>() {
                                                    @Override
                                                    public void accept(Boolean aBoolean) throws Exception {
                                                        if (aBoolean) {
                                                            chooseFromGallery();
                                                        } else {
                                                            Toast.makeText(MainActivity.this, "请授予相机和存储权限", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });


                                        break;
                                }
                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
            }

        });
    }
    /**
     * 拍照选择图片
     */
    private void chooseFromCamera() {
        //构建隐式Intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //调用系统相机
        startActivityForResult(intent, CAMERA_CODE);
    }

    /**
     * 从相册选择图片
     */
    private void chooseFromGallery() {
        //构建一个内容选择的Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //设置选择类型为图片类型
        intent.setType("image/*");
        //打开图片选择
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("HandleFragment", "requestCode:" + requestCode);
        Uri uri;
        switch (requestCode) {
            case CAMERA_CODE:
                //用户点击了取消
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获得拍的照片
                        Bitmap bm = extras.getParcelable("data");
                        //将Bitmap转化为uri
                        uri = saveBitmap(bm, "temp");
                        //启动图像裁剪
                        startImageZoom(uri);
                    }
                }
                break;
            case GALLERY_CODE:
                if (data == null) {
                    return;
                } else {
                    ContentResolver resolver = MainActivity.this.getContentResolver();
                    uri = data.getData();
                    img = new File(getAbsolutePath(MainActivity.this, uri));
                    startImageZoom(uri);
                }
                break;
            case CROP_CODE:
                if (data == null) {
                    return;
                } else {
//                    final Uri resultUri = UCrop.getOutput(data);
//                    //设置裁剪完成后的图片显示
//                    imgPic.setImageURI(resultUri);
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获取到裁剪后的图像
                        Bitmap bm = extras.getParcelable("data");
                        imageUriData = saveBitmap(bm, "temp");
                        Log.e(TAG, "onActivityResult: " + imageUriData.toString());
//                        sharedPreferences.edit().putString("user_image_uri",imageUriData+"");

//                        myfragmentPresenter.uploadPicAndSaveFaceInfo(userId + "", img);
                        toolbarHead.setImageBitmap(bm);
                        isSelectPic = true;
                    }
                }
                break;
            default:
                break;
        }
    }


    public String getAbsolutePath(final Context context,
                                  final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(
                            MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 将content类型的Uri转化为文件类型的Uri
     *
     * @param uri
     * @return
     */
    private Uri convertUri(Uri uri) {
        InputStream is;
        try {
            //Uri ----> InputStream
            is = MainActivity.this.getContentResolver().openInputStream(uri);
            //InputStream ----> Bitmap
            Bitmap bm = BitmapFactory.decodeStream(is);
            //关闭流
            is.close();
            return saveBitmap(bm, "temp");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Bitmap写入SD卡中的一个文件中,并返回写入文件的Uri
     *
     * @param bm
     * @param dirPath
     * @return
     */
    private Uri saveBitmap(Bitmap bm, String dirPath) {
        //新建文件夹用于存放裁剪后的图片
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + dirPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }

        int i = 0;
        i++;
        //新建文件存储裁剪后的图片
        img = new File(tmpDir.getAbsolutePath() + "/avator" + i + ".png");
        Log.e(TAG, "saveBitmap: " + img);
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(img);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
            //返回File类型的Uri
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 通过Uri传递图像信息以供裁剪
     *
     * @param uri
     */
    private void startImageZoom(Uri uri) {
        //构建隐式Intent来启动裁剪程序
        Intent intent = new Intent("com.android.camera.action.CROP");
        // TODO: 2017/3/14 修改android 7.0 无法裁剪
        Log.e(TAG, "startImageZoom: " + img);

        Uri photoURI = FileProvider.getUriForFile(MainActivity.this, "com.ycl.car.fileprovider", img);
        Log.e(TAG, "startImageZoom: " + photoURI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(photoURI, "image/*");
        //设置数据uri和类型为图片类型
//        intent.setDataAndType(uri, "image/*");
        //显示View为可裁剪的
        intent.putExtra("crop", true);
        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //输出图片的宽高均为150
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_CODE);
    }
}
