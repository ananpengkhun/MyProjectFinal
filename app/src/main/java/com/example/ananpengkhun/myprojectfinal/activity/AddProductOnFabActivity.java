package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductOnFabActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PHOTO_REQUEST = 2;

    @BindView(R.id.imv_add_picture) ImageView imvAddPicture;
    @BindView(R.id.ed_prod_code) AppCompatEditText edProdCode;
    @BindView(R.id.ed_prod_name) AppCompatEditText edProdName;
    @BindView(R.id.ed_prod_price) AppCompatEditText edProdPrice;
    @BindView(R.id.ed_prod_amount) AppCompatEditText edProdAmount;
    @BindView(R.id.ed_prod_unit) AppCompatEditText edProdUnit;
    @BindView(R.id.ed_prod_type) AppCompatEditText edProdType;
    @BindView(R.id.ed_prod_provider) AppCompatEditText edProdProvider;
    @BindView(R.id.ed_prod_alert) AppCompatEditText edProdAlert;
    @BindView(R.id.btn_add_product_confirm) Button btnAddProductConfirm;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.parent) CoordinatorLayout parent;
    @BindView(R.id.activity_add_product_on_fab) RelativeLayout activityAddProductOnFab;

    private Button takeByCamera;
    private Button takeByGallery;
    private Uri pathFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_on_fab);
        ButterKnife.bind(this);
        setupPageDrawer();
        init();
    }

    private void setupPageDrawer() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(toolbarClicklistener);
    }

    private void init() {
        imvAddPicture.setOnClickListener(importPictureClicklistener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                Uri uri = data.getData();
                Glide.with(AddProductOnFabActivity.this).load(uri).into(imvAddPicture);
            } else if (requestCode == TAKE_PHOTO_REQUEST) {
                Glide.with(AddProductOnFabActivity.this).load(pathFile).into(imvAddPicture);
            }
        }
    }

    private View.OnClickListener importPictureClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(AddProductOnFabActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_import_picture);
            dialog.setCancelable(true);
            bindId(dialog);

            takeByCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";

                    File newdir = new File(dir);
                    if (!newdir.exists()) {
                        newdir.mkdirs();
                    }
                    String file = dir + System.currentTimeMillis() + ".jpg";
                    File newfile = new File(file);
                    try {
                        newfile.createNewFile();
                    } catch (IOException e) {
                    }

                    pathFile = Uri.fromFile(newfile);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pathFile);
                    startActivityForResult(cameraIntent, TAKE_PHOTO_REQUEST);


                }
            });

            takeByGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                }
            });

            dialog.show();
        }
    };

    private void bindId(Dialog dialog) {
        takeByCamera = (Button) dialog.findViewById(R.id.btn_add_by_take);
        takeByGallery = (Button) dialog.findViewById(R.id.btn_add_by_gallery);
    }

    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
