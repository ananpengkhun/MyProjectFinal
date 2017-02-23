package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.EachItemSizeAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.Product;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableListDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DetailOfListProductActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list) RelativeLayout activityDetailOfList;
    @BindView(R.id.rc_size_item) RecyclerView rcSizeItem;
    @BindView(R.id.ed_code_prod) AppCompatEditText edCodeProd;
    @BindView(R.id.tv_code_prod) TextView tvCodeProd;
    @BindView(R.id.ed_name_prod) AppCompatEditText edNameProd;
    @BindView(R.id.tv_namePro) TextView tvNamePro;
    @BindView(R.id.tv_provider_prod) TextView tvProviderProd;
    @BindView(R.id.tv_prod_amount) TextView tvProdAmount;
    @BindView(R.id.ed_prod_amount) LinearLayout edProdAmount;
    @BindView(R.id.tv_prod_alert) TextView tvProdAlert;
    @BindView(R.id.ed_prod_alert) TextView edProdAlert;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;
    @BindView(R.id.img_product) ImageView imgProduct;
    @BindView(R.id.ll_prod_price) LinearLayout llProdPrice;
    @BindView(R.id.ll_amount) LinearLayout llAmount;
    @BindView(R.id.ll_alert) LinearLayout llAlert;
    @BindView(R.id.spinner_provider) SearchableSpinner spinnerProvider;
    @BindView(R.id.ed_price_pro) AppCompatEditText edPricePro;
    @BindView(R.id.tv_price_pro) TextView tvPricePro;
    @BindView(R.id.imv_minus) ImageView imvMinus;
    @BindView(R.id.imv_plus) ImageView imvPlus;


    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PHOTO_REQUEST = 2;

    private Button takeByCamera;
    private Button takeByGallery;
    private Uri pathFile;

    private ProductDao productDao;
    private boolean swap = true;

    private String prodCode;
    private String prodName;
    private int prodPrice;
    private int prodAmount;
    private int spinProvider;
    private String prodUnit;
    private int prodAlert;
    private List<String> listProdType;
    private List<String> listProvider;
    private List<ProviderDao> providerDaoList;
    private List<ProviderDao> providerDaoload;
    private List<String> getListProdType;

    private EachItemSizeAdapter eachItemSizeAdapter;
    private int index;
    private Realm realm;
    private int productId;
    // private int sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        setupView();
        providerDaoload = new ArrayList<>();
        getListProdType = new ArrayList<>();
        imgProduct.setEnabled(false);
        init();
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(toolbarClicklistener);
        //  imvBoxForEdit.setOnClickListener(imvClicklistener);
    }

    private void init() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/provider");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProviderDao providerDao = new ProviderDao();
                    providerDao.setProvId(snapshot.getValue(ProviderDao.class).getProvId());
                    providerDao.setProvName(snapshot.getValue(ProviderDao.class).getProvName());
                    providerDao.setProvPhone(snapshot.getValue(ProviderDao.class).getProvPhone());
                    providerDao.setProvEmail(snapshot.getValue(ProviderDao.class).getProvEmail());
                    providerDao.setProvAddress(snapshot.getValue(ProviderDao.class).getProvAddress());
                    Log.d("loaddata", "onDataChange: " + providerDao.getProvName());
                    providerDaoload.add(providerDao);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinnerOfTypeProduct();
            }
        }, 100);

        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            productDao = intent.getParcelableExtra("product_object_index");
            productId = intent.getIntExtra("product_id",-1);
            providerDaoList = intent.getParcelableArrayListExtra("provider_arraylist");
            tvNamePro.setText(productDao.getProdName());
            tvCodeProd.setText(productDao.getProdCode());

            if (productDao.getProductQuantity() == 0) {
                llAmount.setVisibility(View.GONE);
            } else {
                llAmount.setVisibility(View.VISIBLE);
                tvProdAmount.setText(productDao.getProductQuantity() + "");
            }

            if (productDao.getProductPrice() == 0) {
                llProdPrice.setVisibility(View.GONE);
            } else {
                llProdPrice.setVisibility(View.VISIBLE);
                tvPricePro.setText(productDao.getProductPrice() + "");
            }
            for (int i = 0; i < providerDaoList.size(); i++) {
                if (providerDaoList.get(i).getProvId() == productDao.getProviderId()) {
                    tvProviderProd.setText(providerDaoList.get(i).getProvName());
                    index = i;
                }
            }

            if (productDao.getProductAlert() == 0) {
                llAlert.setVisibility(View.GONE);
            } else {
                llAlert.setVisibility(View.VISIBLE);
                tvProdAlert.setText(productDao.getProductAlert() + "");
            }

            if (!"".equals(productDao.getProductImg())) {
                Glide.with(DetailOfListProductActivity.this).load(productDao.getProductImg()).placeholder(ContextCompat.getDrawable(DetailOfListProductActivity.this, R.drawable.folder)).into(imgProduct);
            }

            rcSizeItem.setHasFixedSize(true);
            rcSizeItem.setLayoutManager(new LinearLayoutManager(DetailOfListProductActivity.this));
            eachItemSizeAdapter = new EachItemSizeAdapter(DetailOfListProductActivity.this, productDao.getProductEachSizes());
            rcSizeItem.setAdapter(eachItemSizeAdapter);
        }


        imvBoxForEdit.setOnClickListener(imvClicklistener);


    }


    private View.OnClickListener imvClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (imvBoxForEdit.isSelected()) {
                imvBoxForEdit.setSelected(false);
                imgProduct.setEnabled(false);
                //textView Visible
                tvNamePro.setVisibility(View.VISIBLE);
                tvCodeProd.setVisibility(View.VISIBLE);
                if (productDao.getProductPrice() != 0) {
                    tvPricePro.setVisibility(View.VISIBLE);
                }
                if (productDao.getProductQuantity() != 0) {
                    tvProdAmount.setVisibility(View.VISIBLE);
                }
                tvProviderProd.setVisibility(View.VISIBLE);
                if (productDao.getProductAlert() != 0) {
                    tvProdAlert.setVisibility(View.VISIBLE);
                }


                //EditText Gone
                edProdAmount.setVisibility(View.GONE);
                edNameProd.setVisibility(View.GONE);
                edCodeProd.setVisibility(View.GONE);
                edPricePro.setVisibility(View.GONE);
                spinnerProvider.setVisibility(View.GONE);
                edProdAlert.setVisibility(View.GONE);

                //Log.d("summmm", "onClick: "+Integer.parseInt(edProdAmount.getText().toString()));

//                int sum = productDao.getProductQuantity() - Integer.parseInt(edProdAmount.getText().toString());
//                productDao.setProductQuantity(sum);
//                prodAmount = sum;
                prodAmount = productDao.getProductQuantity();
                prodName = edNameProd.getText().toString();
                prodCode = edCodeProd.getText().toString();
                prodPrice = Integer.parseInt(edPricePro.getText().toString());
                spinProvider = spinnerProvider.getSelectedItemPosition();
                prodAlert = Integer.parseInt(edProdAlert.getText().toString());

                setTextView(prodAmount, prodName, prodCode, prodPrice, (spinProvider == -1) ? -2 : spinProvider, prodAlert);

                // save data


                //realm edit

                Product product = realm.where(Product.class).equalTo("productId",productId).findFirst();
                realm.beginTransaction();
                product.setNameItem(prodName);
                product.setNameCode(prodCode);
                product.setProductQuantity(prodAmount);
                if(pathFile != null){
                    product.setProductImg(pathFile.toString());
                }
                product.setProductPrice(prodPrice);
                if(spinProvider != -1){
                    product.setProvider(providerDaoload.get(spinProvider).getProvId());
                }
                product.setProductAlert(prodAlert);
                realm.commitTransaction();

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType/" + productDao.getProdInType());
                //Log.d("providers", "onDataChange: "+providerDao.getProvId());
                Query query = mRootRef.child("data").orderByChild("productId").equalTo(productDao.getProdId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            //Log.d("providers", "onDataChange: "+snapshot.toString());
                            snapshot.getRef().child("productQuantity").setValue(prodAmount);
                            snapshot.getRef().child("nameItem").setValue(prodName);
                            snapshot.getRef().child("nameCode").setValue(prodCode);
                            if (pathFile != null) {
                                snapshot.getRef().child("productImg").setValue(pathFile.toString());
                            }
                            snapshot.getRef().child("productPrice").setValue(prodPrice);
                            Log.d("detailpro", "onDataChange: " + spinProvider);
                            if (spinProvider != -1) {
                                snapshot.getRef().child("provider").setValue(providerDaoload.get(spinProvider).getProvId());
                            }
                            snapshot.getRef().child("productAlert").setValue(prodAlert);



                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            } else {
                // edit data
                imvBoxForEdit.setSelected(true);
                imgProduct.setEnabled(true);
                imgProduct.setOnClickListener(importPictureClicklistener);
                //textView Gone
                tvProdAmount.setVisibility(View.GONE);
                tvNamePro.setVisibility(View.GONE);
                tvCodeProd.setVisibility(View.GONE);
                tvPricePro.setVisibility(View.GONE);
                tvProviderProd.setVisibility(View.GONE);
                tvProdAlert.setVisibility(View.GONE);


                //EditText Visible
                edProdAmount.setVisibility(View.VISIBLE);
                edNameProd.setVisibility(View.VISIBLE);
                edCodeProd.setVisibility(View.VISIBLE);
                edPricePro.setVisibility(View.VISIBLE);
                spinnerProvider.setVisibility(View.VISIBLE);
                edProdAlert.setVisibility(View.VISIBLE);

                imvMinus.setOnClickListener(imvMinusClicklistener);
                imvPlus.setOnClickListener(imvPlusClicklistener);

                if (swap) {
                    swap = false;
                    setTextEdit(productDao.getProductQuantity(),
                            productDao.getProdName(),
                            productDao.getProdCode(),
                            productDao.getProductPrice(),
                            providerDaoList.get(index).getProvName(),
                            productDao.getProductAlert());
                } else {
                    Log.d("detailpro", "onClick: " + spinProvider);
                    setTextEdit(prodAmount,
                            prodName,
                            prodCode,
                            prodPrice,
                            (spinProvider == -1) ? "" : providerDaoList.get(spinProvider).getProvName(),
                            prodAlert);
                }

            }
        }
    };


    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent = new Intent();
//            if (!prodCode.equals(productDao.getProdCode())) {
//                intent.putExtra("nameCode", prodCode);
//            } else {
//                intent.putExtra("nameCode", productDao.getProdCode());
//            }
//
//            if (!prodName.equals(productDao.getProdName())) {
//                intent.putExtra("nameItem", prodName);
//            } else {
//                intent.putExtra("nameItem", productDao.getProdName());
//            }
//
//            if (prodAlert != productDao.getProductAlert()) {
//                intent.putExtra("productAlert", prodAlert);
//            } else {
//                intent.putExtra("productAlert", productDao.getProductAlert());
//            }
//
//            intent.putExtra("productId", productDao.getProdId());
//
//            if (pathFile != null) {
//                intent.putExtra("productImg", pathFile.toString());
//            }else{
//                intent.putExtra("productImg", "");
//            }
//
//            intent.putExtra("ProdInType", productDao.getProdInType());
//
//            if(prodPrice != productDao.getProductPrice()) {
//                intent.putExtra("productPrice", prodPrice);
//            }else{
//                intent.putExtra("productPrice", productDao.getProductPrice());
//            }
//
//            if(prodAmount != productDao.getProductQuantity()) {
//                intent.putExtra("productQuantity", prodAmount);
//            }else{
//                intent.putExtra("productQuantity", productDao.getProductQuantity());
//            }
//            //intent.putExtra("productUnit",productDao.ge());
//            Log.d("spinProvider", "onClick: " + spinProvider);
//            if (spinProvider != -1) {
//                intent.putExtra("provider", providerDaoload.get(spinProvider).getProvId());
//            } else {
//                intent.putExtra("provider", productDao.getProviderId());
//            }
//            setResult(99, intent);
            finish();
        }
    };


    private void setTextView(int prodAmount, String prodName, String prodCode, int prodPrice, int spinProvider, int Alert) {
        tvProdAmount.setText(prodAmount + "");
        tvNamePro.setText(prodName);
        tvCodeProd.setText(prodCode);
        tvPricePro.setText(prodPrice + "");
        if (spinProvider == -2) {
            tvProviderProd.setText("");
        } else {
            tvProviderProd.setText(getListProdType.get(spinProvider));
        }
        tvProdAlert.setText(Alert + "");
    }

    private void setTextEdit(int productQuantity, String prodName, String prodCode, int productPrice, String provName, int alert) {
        Log.d("detailpro", "setTextEdit: " + provName);
//        edProdAmount.setText(productQuantity + "");
        //edProdAmount.setText("");
        edNameProd.setText(prodName);
        edCodeProd.setText(prodCode);
        edPricePro.setText(productPrice + "");
        spinnerProvider.setOnSearchTextChangedListener(new SearchableListDialog.OnSearchTextChanged() {
            @Override
            public void onSearchTextChanged(String strText) {

            }
        });
        edProdAlert.setText(alert + "");
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void spinnerOfTypeProduct() {

        Log.d("loaddata", "spinnerOfTypeProduct: " + providerDaoList.size());
        if (providerDaoload != null) {
            for (int i = 0; i < providerDaoload.size(); i++) {
                getListProdType.add(providerDaoload.get(i).getProvName());
            }
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getListProdType);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spinnerProvider.setAdapter(spinnerArrayAdapter);
        } else {

        }
    }

    private View.OnClickListener importPictureClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(DetailOfListProductActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                pathFile = data.getData();
                Glide.with(DetailOfListProductActivity.this).load(pathFile).into(imgProduct);
            } else if (requestCode == TAKE_PHOTO_REQUEST) {
                Glide.with(DetailOfListProductActivity.this).load(pathFile).into(imgProduct);
            }
        }
    }

    private View.OnClickListener imvMinusClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(DetailOfListProductActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.minus_product_quatity);
            dialog.setCancelable(true);

            TextView textView = (TextView) dialog.findViewById(R.id.tv_dialog_quatity);
            final EditText editText = (EditText) dialog.findViewById(R.id.ed_dialog_quatity);
            Button button = (Button) dialog.findViewById(R.id.btn_dialog_confirm);

            textView.setText("จำนวนที่ต้องการลด");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!"".equals(editText.getText().toString())) {
                        int sum = productDao.getProductQuantity() - Integer.parseInt(editText.getText().toString());
                        productDao.setProductQuantity(sum);
                        prodAmount = sum;
                    } else {
                        prodAmount = productDao.getProductQuantity();
                        Log.d("amount", "onClick: " + prodAmount);
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };

    private View.OnClickListener imvPlusClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(DetailOfListProductActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.minus_product_quatity);
            dialog.setCancelable(true);

            TextView textView = (TextView) dialog.findViewById(R.id.tv_dialog_quatity);
            final EditText editText = (EditText) dialog.findViewById(R.id.ed_dialog_quatity);
            Button button = (Button) dialog.findViewById(R.id.btn_dialog_confirm);

            textView.setText("จำนวนที่ต้องการเพิ่ม");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!"".equals(editText.getText().toString())) {
                        int sum = productDao.getProductQuantity() + Integer.parseInt(editText.getText().toString());
                        productDao.setProductQuantity(sum);
                        prodAmount = sum;
                    } else {
                        prodAmount = productDao.getProductQuantity();
                        Log.d("amount", "onClick: " + prodAmount);
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };
}
