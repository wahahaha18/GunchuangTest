package com.example.sun0002.gunchuangtest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhoto.TakeResultListener;

import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Main2Activity extends TakePhotoActivity {

    private InvokeParam invokeParam;

    //声明一个AlertDialog构造器
    private AlertDialog.Builder builder;
    private ImageView lf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lf = (ImageView) findViewById(R.id.lf);
        TakePhotoImpl takePhoto = new TakePhotoImpl(Main2Activity.this, this);
        lf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSingleChoiceDialog(v);

            }
        });
    }
    private void showSingleChoiceDialog(View view) {
        builder=new AlertDialog.Builder(this);
//        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("设置头像");

        /**
         * 设置内容区域为单选列表项
         */
        final String[] items={"拍摄","从手机相册选择"};
        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 重新构造Uri：content://
                final String CACHE_IMG = Environment.getExternalStorageDirectory()+"/demo/";
                final int TAG_PHOTO_CAMERA=200;

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String fileName = "defaultImage.jpg";

                File file = new File(CACHE_IMG, fileName);

                Uri imageUri= FileProvider.getUriForFile(Main2Activity.this,"com.example.sun0002.gunchuangtest.fileprovider", file);//这里进行替换uri的获得方式

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//这里加入flag

                startActivityForResult(intent, TAG_PHOTO_CAMERA);
//                File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
//                if (!file.getParentFile().exists())file.getParentFile().mkdirs();
//                Uri imageUri = Uri.fromFile(file);

//                configCompress(takePhoto);
//                configTakePhotoOption(takePhoto);
                switch (i){
                    case 1:
                        int limit= 1;
//                        if(limit>1){
//                            if(rgCrop.getCheckedRadioButtonId()==R.id.rbCropYes){
                                takePhoto.onPickMultipleWithCrop(limit,getCropOptions());
//                            }else {
//                                takePhoto.onPickMultiple(limit);
////                            }
//                            return;
//                        }
//                        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFile){
//                            if(rgCrop.getCheckedRadioButtonId()==R.id.rbCropYes){
//                                takePhoto.onPickFromDocumentsWithCrop(imageUri,getCropOptions());
//                            }else {
//                                takePhoto.onPickFromDocuments();
//                            }
//                            return;
//                        }else {
//                            if(rgCrop.getCheckedRadioButtonId()==R.id.rbCropYes){
                                takePhoto.onPickFromGalleryWithCrop(imageUri,getCropOptions());
//                            }else {
//                                takePhoto.onPickFromGallery();
//                            }

                        break;
                    case 0:
//                        if(rgCrop.getCheckedRadioButtonId()==R.id.rbCropYes){
                            takePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
//                        }else {
//                            takePhoto.onPickFromCapture(imageUri);
//                        }
                        break;
                    default:
                        break;
                }
                Toast.makeText(getApplicationContext(), "You clicked "+items[i], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
//    private void configCompress(TakePhoto takePhoto){
//        if(rgCompress.getCheckedRadioButtonId()!=R.id.rbCompressYes){
//            takePhoto.onEnableCompress(null,false);
//            return ;
//        }
//        int maxSize= Integer.parseInt(etSize.getText().toString());
//        int width= Integer.parseInt(etCropWidth.getText().toString());
//        int height= Integer.parseInt(etHeightPx.getText().toString());
//        boolean showProgressBar=rgShowProgressBar.getCheckedRadioButtonId()==R.id.rbShowYes? true:false;
//        boolean enableRawFile = rgRawFile.getCheckedRadioButtonId() == R.id.rbRawYes ? true : false;
//        CompressConfig config;
//        if(rgCompressTool.getCheckedRadioButtonId()==R.id.rbCompressWithOwn){
//            config=new CompressConfig.Builder()
//                    .setMaxSize(maxSize)
//                    .setMaxPixel(width>=height? width:height)
//                    .enableReserveRaw(enableRawFile)
//                    .create();
//        }else {
//            LubanOptions option=new LubanOptions.Builder()
//                    .setMaxHeight(height)
//                    .setMaxWidth(width)
//                    .setMaxSize(maxSize)
//                    .create();
//            config=CompressConfig.ofLuban(option);
//            config.enableReserveRaw(enableRawFile);
//        }
//        takePhoto.onEnableCompress(config,showProgressBar);
//
//
//    }

    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
//        if(rgPickTool.getCheckedRadioButtonId()==R.id.rbPickWithOwn){
//            builder.setWithOwnGallery(true);
//        }
//        if(rgCorrectTool.getCheckedRadioButtonId()==R.id.rbCorrectYes){
//            builder.setCorrectImage(true);
//        }
        takePhoto.setTakePhotoOptions(builder.create());

    }
    private CropOptions getCropOptions(){
//        if(rgCrop.getCheckedRadioButtonId()!=R.id.rbCropYes)return null;
        int height= 600;
        int width= 600;
        boolean withWonCrop=false;

        CropOptions.Builder builder=new CropOptions.Builder();

//        if(rgCropSize.getCheckedRadioButtonId()==R.id.rbAspect){
//            builder.setAspectX(width).setAspectY(height);
//        }else {
            builder.setOutputX(width).setOutputY(height);
//        }
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

















    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {
        if (images.size()!=0){
            Glide.with(this).load(new File(images.get(0).getCompressPath())).into(lf);
        }
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llImages);
//        for (int i = 0, j = images.size(); i < j - 1; i += 2) {
//            View view = LayoutInflater.from(this).inflate(R.layout.image_show, null);
//            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
//            ImageView imageView2 = (ImageView) view.findViewById(R.id.imgShow2);
//            Glide.with(this).load(new File(images.get(i).getCompressPath())).into(imageView1);
//            Glide.with(this).load(new File(images.get(i + 1).getCompressPath())).into(imageView2);
//            linearLayout.addView(view);
//        }
//        if (images.size() % 2 == 1) {
//            View view = LayoutInflater.from(this).inflate(R.layout.image_show, null);
//            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
//            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(imageView1);
//            linearLayout.addView(view);
//        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
    TakePhoto takePhoto = getTakePhoto();
    /** * 获取TakePhoto实例 * @return */
    public TakePhoto getTakePhoto(){

        TakePhoto takePhoto = null;
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
//    TakeResultListener takeResultListener = new TakeResultListener() {
//        @Override
//        public void takeSuccess(TResult result) {
//
//        }
//
//        @Override
//        public void takeFail(TResult result, String msg) {
//
//        }
//
//        @Override
//        public void takeCancel() {
//
//        }
//    }
}
