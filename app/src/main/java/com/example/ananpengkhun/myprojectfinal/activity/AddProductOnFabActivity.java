package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.Product;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import io.realm.RealmResults;

public class AddProductOnFabActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PHOTO_REQUEST = 2;
    private static final String MyPreference = "ProdType_index";
    private String TAG = AddProductOnFabActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imv_add_picture) ImageView imvAddPicture;
    @BindView(R.id.ed_prod_code) AppCompatEditText edProdCode;
    @BindView(R.id.ed_prod_name) AppCompatEditText edProdName;
    @BindView(R.id.ed_prod_price) AppCompatEditText edProdPrice;
    @BindView(R.id.ed_prod_amount) AppCompatEditText edProdAmount;
    @BindView(R.id.ed_prod_unit) AppCompatEditText edProdUnit;
    @BindView(R.id.spinner_product_type) SearchableSpinner spinnerProductType;
    @BindView(R.id.spinner_provider) SearchableSpinner spinnerProvider;
    @BindView(R.id.ed_prod_alert) AppCompatEditText edProdAlert;
    @BindView(R.id.btn_add_product_confirm) Button btnAddProductConfirm;
    @BindView(R.id.parent) CoordinatorLayout parent;
    @BindView(R.id.activity_add_product_on_fab) RelativeLayout activityAddProductOnFab;


    private Button takeByCamera;
    private Button takeByGallery;
    private Uri pathFile;
    //private DataDao dataDao;
    //private List<DataDao.ProductTypeBean> productTypeDaos;
    private List<ProviderDao> providerDaoList;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int max;
    //    private List<ProductTypeDao> productTypeList;
    private List<ProductDao> productList;

    private Realm realm;
    private RealmResults<TestProductType> testProductTypes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_on_fab);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        testProductTypes = realm.where(TestProductType.class).findAll();
        setupPageDrawer();
        providerDaoList = new ArrayList<>();
        init();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                spinnerOfTypeProduct();
//                processData();
//            }
//        }, 300);


    }

    private void processData() {
        imvAddPicture.setOnClickListener(importPictureClicklistener);
        btnAddProductConfirm.setOnClickListener(addProductClickListener);
    }


    private void setupPageDrawer() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(toolbarClicklistener);
    }

    private void init() {
        Intent intent = getIntent();
        if (intent.getParcelableArrayListExtra("data") != null) {
            productList = getIntent().getParcelableArrayListExtra("data");
        }
//        if (intent.getParcelableArrayListExtra("dataType") != null) {
//            productTypeList = getIntent().getParcelableArrayListExtra("dataType");
//            Log.d("addProduct", "init: " + productTypeList.size());
//        }
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
                    providerDaoList.add(providerDao);
                }
                spinnerOfTypeProduct();
                processData();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                pathFile = data.getData();
                Glide.with(AddProductOnFabActivity.this).load(pathFile).into(imvAddPicture);
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

    private void spinnerOfTypeProduct() {
        //if (productList != null) {
        List<String> listProdType = new ArrayList<>();
        for (int i = 0; i < testProductTypes.size(); i++) {
            listProdType.add(testProductTypes.get(i).getName());
        }
        Log.d(TAG, "spinnerOfTypeProduct: " + listProdType.size());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listProdType);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerProductType.setAdapter(spinnerArrayAdapter);
//        } else {
//
//        }


        if (providerDaoList != null) {
            List<String> getListProdType = new ArrayList<>();
            for (int i = 0; i < providerDaoList.size(); i++) {
                getListProdType.add(providerDaoList.get(i).getProvName());
            }
            ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getListProdType);
            spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spinnerProvider.setAdapter(spinnerArrayAdapter2);
        } else {

        }


    }


    private View.OnClickListener addProductClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//
//
//
//
//
//            spinnerProductType.getSelectedItemPosition();
//
//

            Log.d("spinner", "onClick spinnerProductType: " + spinnerProductType.getSelectedItemPosition());
            Log.d("spinner", "onClick spinnerProvider: " + spinnerProvider.getSelectedItemPosition());
            if (spinnerProductType.getSelectedItemPosition() != -1 &&
                    spinnerProvider.getSelectedItemPosition() != -1 &&
                    !("".equals(edProdCode.getText().toString().trim())) &&
                    !("".equals(edProdName.getText().toString().trim())) &&
                    !("".equals(edProdAlert.getText().toString())) &&
                    !("".equals(edProdPrice.getText().toString())) &&
                    !("".equals(edProdAmount.getText().toString())) &&
                    !("".equals(edProdUnit.getText().toString().trim())) &&
                    pathFile != null
                    ) {

                sp = getSharedPreferences(MyPreference, MODE_PRIVATE);

                max = 0;
                Log.d("addproduct", "onClick: " + sp.getInt("indexProduct", 0));
                max = productList.get(0).getProdId();
                for (int j = 0; j < productList.size(); j++) {
                    if (productList.get(j).getProdId() > max) {
                        max = productList.get(j).getProdId();
                    }
                }
                int indexData;
                if (testProductTypes.get(spinnerProductType.getSelectedItemPosition()).getData() == null) {
                    indexData = 0;
                } else {
                    indexData = testProductTypes.get(spinnerProductType.getSelectedItemPosition()).getData().size();
                }

                Log.d("start", "onClick: " + indexData);
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

                HashMap<String, Object> postValues = new HashMap<>();
                postValues.put("productImg", pathFile.toString());
                postValues.put("nameCode", edProdCode.getText().toString());
                postValues.put("nameItem", edProdName.getText().toString());
                postValues.put("productAlert", Integer.parseInt(edProdAlert.getText().toString()));
                postValues.put("productId", max + 1);
                postValues.put("productPrice", Integer.parseInt(edProdPrice.getText().toString()));
                postValues.put("productQuantity", Integer.parseInt(edProdAmount.getText().toString()));
                postValues.put("productUnit", edProdUnit.getText().toString());
                postValues.put("provider", providerDaoList.get(spinnerProvider.getSelectedItemPosition()).getProvId());
                postValues.put("productInType", spinnerProductType.getSelectedItemPosition());

                Map<String, Object> childUpdates = new HashMap<>();

                childUpdates.put("/productType/" + spinnerProductType.getSelectedItemPosition() + "/data/" + indexData, postValues);
                mRootRef.updateChildren(childUpdates);

//                Intent intent = new Intent();
//                intent.putExtra("productImg", pathFile.toString());
//                intent.putExtra("productType", spinnerProductType.getSelectedItemPosition());
//                intent.putExtra("nameCode", edProdCode.getText().toString());
//                intent.putExtra("nameItem", edProdName.getText().toString());
//                intent.putExtra("productAlert", Integer.parseInt(edProdAlert.getText().toString()));
//                intent.putExtra("productId", max + 1);
//                intent.putExtra("productPrice", Integer.parseInt(edProdPrice.getText().toString()));
//                intent.putExtra("productQuantity", Integer.parseInt(edProdAmount.getText().toString()));
//                intent.putExtra("productUnit", edProdUnit.getText().toString());
//                intent.putExtra("provider", providerDaoList.get(spinnerProvider.getSelectedItemPosition()).getProvId());
//                setResult(MyDataInventoryActivity.PRODUCT_TYPE, intent);
                realm.executeTransactionAsync(new Realm.Transaction() {
                                                  @Override
                                                  public void execute(Realm realm) {
                                                      //TestProductType testProduct = realm.createObject(TestProductType.class);
                                                      RealmResults<TestProductType> testProductTypes = realm.where(TestProductType.class).findAll();
                                                      //TestProductType testProductType = realm.createObject(TestProductType.class);
                                                      for (int i = 0; i < testProductTypes.size(); i++) {
                                                          if (testProductTypes.get(i).getTypeId() == spinnerProductType.getSelectedItemPosition() + 1) {
//                                                          if (spinnerProductType.getSelectedItemPosition() == testProductTypes.getTypeId() - 1) {
                                                              Product product = new Product();
                                                              product.setNameCode(edProdCode.getText().toString());
                                                              product.setNameItem(edProdName.getText().toString());
                                                              product.setProvider(providerDaoList.get(spinnerProvider.getSelectedItemPosition()).getProvId());
                                                              product.setProductImg(pathFile.toString());
                                                              product.setProductInType(spinnerProductType.getSelectedItemPosition());
                                                              product.setProductId(max + 1);
                                                              product.setProductQuantity(Integer.parseInt(edProdAmount.getText().toString()));
                                                              product.setProductPrice(Integer.parseInt(edProdPrice.getText().toString()));
                                                              product.setProductAlert(Integer.parseInt(edProdAlert.getText().toString()));
                                                              testProductTypes.get(spinnerProductType.getSelectedItemPosition()).getData().add(product);
                                                              //testProductType.getData().add(product);
                                                          }
//                                                          }
                                                      }
                                                  }
                                              }, new Realm.Transaction.OnSuccess() {
                                                  @Override
                                                  public void onSuccess() {
                                                      Log.d(TAG, "onSuccess: completed adding");
                                                  }
                                              }, new Realm.Transaction.OnError() {
                                                  @Override
                                                  public void onError(Throwable error) {
                                                      Log.d(TAG, "onError:" + error.getMessage());
                                                  }
                                              }

                );

                finish();

            } else

            {
                Toast.makeText(AddProductOnFabActivity.this, "Insert Fail.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
