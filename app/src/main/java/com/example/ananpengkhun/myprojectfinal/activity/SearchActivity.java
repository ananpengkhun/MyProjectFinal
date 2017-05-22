package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.ananpengkhun.myprojectfinal.dao.ProductEachSize;
import com.example.ananpengkhun.myprojectfinal.dao.Productsize;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.example.ananpengkhun.myprojectfinal.dao.ReportDao;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_search) RelativeLayout activitySearch;
    @BindView(R.id.img_product) ImageView imgProduct;
    @BindView(R.id.ed_code_prod) AppCompatEditText edCodeProd;
    @BindView(R.id.tv_code_prod) TextView tvCodeProd;
    @BindView(R.id.ed_name_prod) AppCompatEditText edNameProd;
    @BindView(R.id.tv_namePro) TextView tvNamePro;
    @BindView(R.id.ed_price_pro) AppCompatEditText edPricePro;
    @BindView(R.id.tv_price_pro) TextView tvPricePro;
    @BindView(R.id.ll_prod_price) LinearLayout llProdPrice;
    @BindView(R.id.tv_provider_prod) TextView tvProviderProd;
    @BindView(R.id.spinner_provider) SearchableSpinner spinnerProvider;
    @BindView(R.id.ed_prod_amount) LinearLayout edProdAmount;
    @BindView(R.id.tv_prod_amount) TextView tvProdAmount;
    @BindView(R.id.ll_amount) LinearLayout llAmount;
    @BindView(R.id.ed_prod_alert) EditText edProdAlert;
    @BindView(R.id.tv_prod_alert) TextView tvProdAlert;
    @BindView(R.id.ll_alert) LinearLayout llAlert;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;
    @BindView(R.id.rc_size_item) RecyclerView rcSizeItem;

    @BindView(R.id.imv_minus) ImageView imvMinus;
    @BindView(R.id.imv_plus) ImageView imvPlus;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PHOTO_REQUEST = 2;
    //private DataDao dataDao;
    private String SelectedData;
    private List<ProviderDao> providerDaoload;
    private EachItemSizeAdapter eachItemSizeAdapter;
    private List<ProductEachSize> productEachSizes;
    private List<ProductDao> productList;
    private List<String> getListProdType;

    private Button takeByCamera;
    private Button takeByGallery;
    private Uri pathFile;

    private int indexSearch;
    private String prodCode;
    private String prodName;
    private int prodPrice;
    private int prodAmount;
    private int spinProvider;
    private String prodUnit;
    private int prodAlert;
    private boolean swap = true;
    private int indexProvider;
    private Realm realm;
    private RealmResults<TestProductType> testProductTypes;
    private int productId;

    private Realm realmReport;
    private int total;
    private int existReport = 0;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        RealmConfiguration realmConfigurationReport = new RealmConfiguration.Builder()
                .name("report.realm")
                .build();
        realmReport = Realm.getInstance(realmConfigurationReport);

        realm = Realm.getDefaultInstance();
        testProductTypes = realm.where(TestProductType.class).findAll();
        providerDaoload = new ArrayList<>();
        productList = new ArrayList<>();
        getListProdType = new ArrayList<>();
        imgProduct.setEnabled(false);
        setData();
        setupView();

    }

    private void init() {
        Log.d("search", "init: " + SelectedData);
        Log.d("search", "init: " + testProductTypes.size());
        for (int i = 0; i < testProductTypes.size(); i++) {
            if (testProductTypes.get(i).getData() != null) {
                // product list dummy
                for (int j = 0; j < testProductTypes.get(i).getData().size(); j++) {
                    //Log.d(TAG, "init: "+i+" :"+dataDao.getProductType().get(i).getData().size());
                    List<Product> dataBean = testProductTypes.get(i).getData();
                    productEachSizes = new ArrayList<>();
                    ProductDao productDao = new ProductDao();
                    productDao.setProdCode(dataBean.get(j).getNameCode());
                    productDao.setProdName(dataBean.get(j).getNameItem());
                    productDao.setProviderId(dataBean.get(j).getProvider());
                    productDao.setProductImg(dataBean.get(j).getProductImg());
                    productDao.setProdInType(dataBean.get(j).getProductInType());
                    productDao.setProdId(dataBean.get(j).getProductId());
                    productDao.setProductQuantity(dataBean.get(j).getProductQuantity());
                    productDao.setProductPrice(dataBean.get(j).getProductPrice());
                    productDao.setProductAlert(dataBean.get(j).getProductAlert());


                    List<Productsize> list = dataBean.get(j).getDataItem();

                    if (list != null) {
                        for (int z = 0; z < list.size(); z++) {

                            //Log.d(TAG, "init: " + list.getDataItem().size());
                            //productDao.setProductEachSizes();
                            ProductEachSize productEachSize = new ProductEachSize();
                            productEachSize.setAmongPerWrap(list.get(z).getAmongPerWrap());
                            productEachSize.setContrainUPiecePerBox(list.get(z).getContrainUPiecePerBox());
                            productEachSize.setDiameterOutsize(list.get(z).getDiameterOutsize());
                            productEachSize.setEffordUBaht(list.get(z).getEffordUBaht());
                            productEachSize.setLongPerWrap(list.get(z).getLongPerWrap());
                            productEachSize.setNameItemId(list.get(z).getNameItemId());
                            productEachSize.setNameItemSize(list.get(z).getNameItemSize());
                            productEachSize.setTotalItemBigUnit(list.get(z).getTotalItemBigUnit());
                            productEachSize.setUnit(list.get(z).getUnit());
                            productEachSize.setWeightPerWrap(list.get(z).getWeightPerWrap());
                            productEachSize.setProductSizeAlert(list.get(z).getProductSizeAlert());
                            productEachSize.setIndexInProduct(list.get(z).getIndexInProduct());

                            ProductEachSize.PriceUBahtBean priceUBahtBean = new ProductEachSize.PriceUBahtBean();
                            priceUBahtBean.setClassEightFive(list.get(z).getPricePerBath().getClassEightFive());
                            priceUBahtBean.setClassFive(list.get(z).getPricePerBath().getClassFive());
                            priceUBahtBean.setClassOne(list.get(z).getPricePerBath().getClassOne());

                            priceUBahtBean.setClassOneThreeFive(list.get(z).getPricePerBath().getClassOneThreeFive());
                            priceUBahtBean.setClassThree(list.get(z).getPricePerBath().getClassThree());
                            priceUBahtBean.setClassTwo(list.get(z).getPricePerBath().getClassTwo());
                            //Log.d(TAG, "init: "+list.getDataItem().get(z).getPriceUBaht().getClassTwo());
                            priceUBahtBean.setPerKilo(list.get(z).getPricePerBath().getPerKilo());
                            priceUBahtBean.setPerMeter(list.get(z).getPricePerBath().getPerMeter());
                            priceUBahtBean.setPerPiece(list.get(z).getPricePerBath().getPerPiece());
                            priceUBahtBean.setPerWrap(list.get(z).getPricePerBath().getPerWrap());
                            productEachSize.setPriceUBaht(priceUBahtBean);

                            productEachSizes.add(productEachSize);

                        }
                        productDao.setProductEachSizes(productEachSizes);

                    }
                    productList.add(productDao);
                }
            }
        }


        if (productList != null) {
            for (int i = 0; i < productList.size(); i++) {

                if (SelectedData.equals(productList.get(i).getProdName())) {
                    Log.d("search", "init++++++++: " + productList.get(i).getProdName());
                    //show data after selected from search.
                    indexSearch = i;
                    productId = productList.get(i).getProdId();
                    tvNamePro.setText(productList.get(i).getProdName());
                    tvCodeProd.setText(productList.get(i).getProdCode());
                    total = productList.get(i).getProductQuantity();

                    if (productList.get(i).getProductQuantity() == 0) {
                        llAmount.setVisibility(View.GONE);
                    } else {
                        llAmount.setVisibility(View.VISIBLE);
                        tvProdAmount.setText(productList.get(i).getProductQuantity() + "");
                    }

                    if (productList.get(i).getProductPrice() == 0 && productList.get(i).getProductEachSizes().size() != 0) {
                        llProdPrice.setVisibility(View.GONE);
                    } else {
                        llProdPrice.setVisibility(View.VISIBLE);
                        tvPricePro.setText(productList.get(i).getProductPrice() + "");
                    }
                    for (int z = 0; z < providerDaoload.size(); z++) {
                        if (providerDaoload.get(z).getProvId() == productList.get(i).getProviderId()) {
                            tvProviderProd.setText(providerDaoload.get(z).getProvName());
                            indexProvider = z;
                        }
                    }

                    if (productList.get(i).getProductAlert() == 0 && productList.get(i).getProductEachSizes().size() != 0) {
                        llAlert.setVisibility(View.GONE);
                    } else {
                        llAlert.setVisibility(View.VISIBLE);
                        tvProdAlert.setText(productList.get(i).getProductAlert() + "");
                    }

                    if (!"".equals(productList.get(i).getProductImg())) {
                        Log.d("image", "init: " + productList.get(i).getProductImg());
                        Glide.with(SearchActivity.this).load(productList.get(i).getProductImg()).placeholder(ContextCompat.getDrawable(SearchActivity.this, R.drawable.folder)).into(imgProduct);
                    }

                    rcSizeItem.setHasFixedSize(true);
                    rcSizeItem.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    eachItemSizeAdapter = new EachItemSizeAdapter(SearchActivity.this, productList.get(i).getProductEachSizes(), productList.get(i));
                    eachItemSizeAdapter.setRealm(realm);
                    eachItemSizeAdapter.setRealmReport(realmReport);
                    rcSizeItem.setAdapter(eachItemSizeAdapter);
                }
            }
        }

        imvBoxForEdit.setOnClickListener(imvClicklistener);
    }

    private void setData() {
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
                if (getIntent().getCharSequenceExtra("selected") != null) {
                    //dataDao = getIntent().getParcelableExtra("data");
                    SelectedData = getIntent().getCharSequenceExtra("selected").toString();
                }
                spinnerOfTypeProduct();
                init();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);

    }

    private void setupView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(toolbarClicklistener);
    }

    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        finish();
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
                //if (productList.get(indexSearch).getProductPrice() != 0) {
                tvPricePro.setVisibility(View.VISIBLE);
                //}
//                if (productList.get(indexSearch).getProductQuantity() != 0) {
                tvProdAmount.setVisibility(View.VISIBLE);
                //}
                tvProviderProd.setVisibility(View.VISIBLE);
                //if (productList.get(indexSearch).getProductAlert() != 0) {
                tvProdAlert.setVisibility(View.VISIBLE);
                //}


                //EditText Gone
                edProdAmount.setVisibility(View.GONE);
                edNameProd.setVisibility(View.GONE);
                edCodeProd.setVisibility(View.GONE);
                edPricePro.setVisibility(View.GONE);
                spinnerProvider.setVisibility(View.GONE);
                edProdAlert.setVisibility(View.GONE);


                //prodAmount = Integer.parseInt(edProdAmount.getText().toString());
                prodAmount = productList.get(indexSearch).getProductQuantity();
                prodName = edNameProd.getText().toString();
                prodCode = edCodeProd.getText().toString();
                if ("".equals(edPricePro.getText().toString())) {
                    prodPrice = 0;
                } else {
                    prodPrice = Integer.parseInt(edPricePro.getText().toString());
                }
                spinProvider = spinnerProvider.getSelectedItemPosition();
                if ("".equals(edProdAlert.getText().toString())) {
                    prodAlert = 0;
                } else {
                    prodAlert = Integer.parseInt(edProdAlert.getText().toString());
                }
                setTextView(prodAmount, prodName, prodCode, prodPrice, (spinProvider == -1) ? -2 : spinProvider, prodAlert);

                // save data
                //report

                if (prodAmount < total) {
                    Log.d("leave", "onClick top prod: " + prodAmount);
                    Log.d("leave", "onClick top total: " + total);

                    RealmResults<ReportDao> reportDaos = realmReport.where(ReportDao.class).findAll();
                    DateFormat df = new SimpleDateFormat("d/MM/yyyy");
                    final String now = df.format(new Date());
                    try {
                        date = df.parse(now);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //final String now = "10/มี.ค./2017";
                    for (int i = 0; i < reportDaos.size(); i++) {
                        String strdate = df.format(reportDaos.get(i).getDate());
                        if (prodName.equals(reportDaos.get(i).getProdNameRep())
                                && now.equals(strdate)) {
                            existReport = 1;
                        }
                    }
                    if (existReport != 1) {
                        realmReport.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {

                                ReportDao reportDao = realm.createObject(ReportDao.class);
                                reportDao.setProdNameRep(prodName);
                                int leave = total - prodAmount;
                                Log.d("leave", "execute total: " + total);
                                Log.d("leave", "execute prodamount: " + prodAmount);
                                Log.d("leave", "execute: " + leave);
                                reportDao.setProductIdRep(productId);
                                reportDao.setProductSizeIdRep(0);
                                reportDao.setProdQuantityRep(leave);
                                reportDao.setDate(date);
                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Log.d("report", "onSuccess: ");
                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Log.d("report", "onError: ");
                            }
                        });
                    } else {
                        RealmResults<ReportDao> reportDao = realmReport.where(ReportDao.class).equalTo("prodNameRep", prodName).findAll();
                        for (int i = 0; i < reportDao.size(); i++) {
                            Log.d("leave", "onClick date: " + now);
                            String strdate = df.format(reportDaos.get(i).getDate());
                            if (now.equals(strdate)) {
                                realmReport.beginTransaction();
                                reportDao.get(i).setProdNameRep(prodName);
                                int leave = total - prodAmount;
                                Log.d("leave", "execute total222: " + total);
                                Log.d("leave", "execute prodamount22: " + prodAmount);
                                Log.d("leave", "execute leave: " + leave);
                                Log.d("leave", "execute getpro: " + reportDao.get(i).getProdQuantityRep());
                                reportDao.get(i).setProdQuantityRep(reportDao.get(i).getProdQuantityRep() + leave);
                                realmReport.commitTransaction();
                            }
                        }
                    }


                }
                ReportDao reportDao = realmReport.where(ReportDao.class).equalTo("productIdRep", productId).findFirst();
                if (reportDao != null) {
                    realmReport.beginTransaction();
                    reportDao.setProdNameRep(prodName);
                    realmReport.commitTransaction();
                }
                //realm edit
                Product product = realm.where(Product.class).equalTo("productId", productId).findFirst();
                realm.beginTransaction();
                product.setNameItem(prodName);
                product.setNameCode(prodCode);
                product.setProductQuantity(prodAmount);
                if (pathFile != null) {
                    product.setProductImg(pathFile.toString());
                }
                product.setProductPrice(prodPrice);
                if (spinProvider != -1) {
                    product.setProvider(providerDaoload.get(spinProvider).getProvId());
                }
                product.setProductAlert(prodAlert);
                realm.commitTransaction();

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType/" + productList.get(indexSearch).getProdInType());
                //Log.d("providers", "onDataChange: "+providerDao.getProvId());
                Query query = mRootRef.child("data").orderByChild("productId").equalTo(productList.get(indexSearch).getProdId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            //Log.d("providers", "onDataChange: "+snapshot.toString());
                            snapshot.getRef().child("productQuantity").setValue(prodAmount);
                            //Log.d("insave", "onDataChange: "+pathFile.toString());
                            if (pathFile != null) {
                                snapshot.getRef().child("productImg").setValue(pathFile.toString());
                            }
                            snapshot.getRef().child("nameItem").setValue(prodName);
                            snapshot.getRef().child("nameCode").setValue(prodCode);
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

                total = prodAmount;
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
                    setTextEdit(productList.get(indexSearch).getProductQuantity(),
                            productList.get(indexSearch).getProdName(),
                            productList.get(indexSearch).getProdCode(),
                            productList.get(indexSearch).getProductPrice(),
                            providerDaoload.get(indexProvider).getProvName(),
                            productList.get(indexSearch).getProductAlert());
                } else {
                    Log.d("detailpro", "onClick: " + spinProvider);
                    setTextEdit(prodAmount,
                            prodName,
                            prodCode,
                            prodPrice,
                            (spinProvider == -1) ? "" : providerDaoload.get(spinProvider).getProvName(),
                            prodAlert);
                }

            }
        }
    };

    private void setTextView(int prodAmount, String prodName, String prodCode, int prodPrice, int spinProvider, int Alert) {
        tvProdAmount.setText(prodAmount + "");
        tvNamePro.setText(prodName);
        tvCodeProd.setText(prodCode);
        tvPricePro.setText(prodPrice + "");
        if (spinProvider == -2) {
            tvProviderProd.setText(providerDaoload.get(indexProvider).getProvName());
        } else {
            tvProviderProd.setText(getListProdType.get(spinProvider));
        }
        tvProdAlert.setText(Alert + "");
    }

    private void setTextEdit(int productQuantity, String prodName, String prodCode, int productPrice, String provName, int alert) {
        Log.d("detailpro", "setTextEdit: " + provName);
        //edProdAmount.setText(productQuantity + "");
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

    private void spinnerOfTypeProduct() {

        //Log.d("loaddata", "spinnerOfTypeProduct: " + providerDaoload.size());
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
            final Dialog dialog = new Dialog(SearchActivity.this);
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
                Glide.with(SearchActivity.this).load(pathFile).into(imgProduct);
            } else if (requestCode == TAKE_PHOTO_REQUEST) {
                Glide.with(SearchActivity.this).load(pathFile).into(imgProduct);
            }
        }
    }

    private View.OnClickListener imvMinusClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(SearchActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.minus_product_quatity);
            dialog.setCancelable(true);

            TextView textView = (TextView) dialog.findViewById(R.id.tv_dialog_quatity);
            final EditText editText = (EditText) dialog.findViewById(R.id.ed_dialog_quatity);
            final Button button = (Button) dialog.findViewById(R.id.btn_dialog_confirm);

            button.setEnabled(false);
            button.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.gray));

            textView.setText("จำนวนที่ต้องการลด");

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.d("ontextchange", "onTextChanged: " + charSequence);
                    if (!"".equals(charSequence.toString())) {
                        if (productList.get(indexSearch).getProductQuantity() > Integer.parseInt(charSequence.toString())) {
                            button.setEnabled(true);
                            button.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorPrimary));
                        } else {
                            button.setEnabled(false);
                            button.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.gray));
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!"".equals(editText.getText().toString())) {
                        if (productList.get(indexSearch).getProductQuantity() > Integer.parseInt(editText.getText().toString())) {
                            int sum = productList.get(indexSearch).getProductQuantity() - Integer.parseInt(editText.getText().toString());
                            productList.get(indexSearch).setProductQuantity(sum);
                            prodAmount = sum;
                        }
                    } else {
                        prodAmount = productList.get(indexSearch).getProductQuantity();
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
            final Dialog dialog = new Dialog(SearchActivity.this);
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
                        int sum = productList.get(indexSearch).getProductQuantity() + Integer.parseInt(editText.getText().toString());
                        productList.get(indexSearch).setProductQuantity(sum);
                        prodAmount = sum;
                    } else {
                        prodAmount = productList.get(indexSearch).getProductQuantity();
                        Log.d("amount", "onClick: " + prodAmount);
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
