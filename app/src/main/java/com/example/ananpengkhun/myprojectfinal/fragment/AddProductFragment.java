package com.example.ananpengkhun.myprojectfinal.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {


    private static final int PICK_IMAGE_REQUEST = 1;
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


    private Button takeByCamera;
    private Button takeByGallery;
    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_add_product, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        imvAddPicture.setOnClickListener(importPictureClicklistener);
    }

    public static Fragment newInstant() {
        return new AddProductFragment();
    }

    private View.OnClickListener importPictureClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_import_picture);
            dialog.setCancelable(true);
            bindId(dialog);

            takeByCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                imvAddPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
