package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProductAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProductTypeAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProviderAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProviderViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.Product;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductEachSize;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;

public class MyDataInventoryActivity extends AppCompatActivity {
    private static final String TAG = MyDataInventoryActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @BindView(R.id.vp_horizontal_ntb) ViewPager vpHorizontalNtb;
    @BindView(R.id.ntb_horizontal) NavigationTabBar ntbHorizontal;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.parent) CoordinatorLayout paRent;
    @BindView(R.id.tv_toolbarTitle) TextView tvToolbarTitle;


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private Snackbar snackbar;
    private int changeViewpager;

    private List<ProductDao> productList;
    private List<ProductTypeDao> productTypeList;
    private List<ProviderDao> providerList;

    private List<ProductEachSize> productEachSizes;


    public static final int PRODUCT_TYPE = 1;
    public static final int PRODUCT = 2;
    public static final int PROVIDER = 3;
    public static final int EDIT_PRODUCT = 4;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private DataDao dataDao;
    //private List<DataDao.ProductTypeBean> productTypeDaos;
    private static final String MyPreference = "ProdType_index";
    private List<ProviderDao> providerDaoList;
    private InventoryProductAdapter productAdapter;
    private InventoryProductTypeAdapter inventoryProductTypeAdapter;
    private InventoryProviderAdapter inventoryProviderAdapter;
    private Realm realm;
    private RealmResults<TestProductType> testProductTypes;

    private RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
        @Override
        public void onChange(Realm element) {

            //inventoryProductTypeAdapter.notifyDataSetChanged();
            productTypeList.clear();
            productList.clear();
            providerDaoList.clear();

            init();
            vpHorizontalNtb.setCurrentItem(changeViewpager);


//            productAdapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data_inventory);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        testProductTypes = realm.where(TestProductType.class).findAllAsync();
        realm.addChangeListener(listener);
        setupPageDrawer();
        declarInit();
        init();
    }

    private void declarInit() {
        productAdapter = new InventoryProductAdapter(MyDataInventoryActivity.this);
        inventoryProductTypeAdapter = new InventoryProductTypeAdapter(MyDataInventoryActivity.this);
        inventoryProviderAdapter = new InventoryProviderAdapter(MyDataInventoryActivity.this);
        productList = new ArrayList<>();
        productTypeList = new ArrayList<>();
        providerDaoList = new ArrayList<>();

        providerList = new ArrayList<>();
        if (getIntent().getParcelableExtra("data") != null) {
            dataDao = getIntent().getParcelableExtra("data");
            //Log.d(TAG, "init: "+dataDao.getProductType().get(0).getName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void init() {
        // product type list dummy
        for (int i = 0; i < dataDao.getProductType().size(); i++) {

            ProductTypeDao productTypeDao = new ProductTypeDao();
            productTypeDao.setProdTypeName(dataDao.getProductType().get(i).getName());
            productTypeDao.setProdTypeCode(dataDao.getProductType().get(i).getTypeCode());
            productTypeDao.setProdTypeDes(dataDao.getProductType().get(i).getTypeDes());
            productTypeList.add(productTypeDao);

            if (dataDao.getProductType().get(i).getData() != null) {
                // product list dummy
                for (int j = 0; j < dataDao.getProductType().get(i).getData().size(); j++) {
                    Log.d(TAG, "init: " + i + " :" + dataDao.getProductType().get(i).getData().size());

                    List<ProductEachSize> productEachSizes = new ArrayList<>();
                    ProductDao productDao = new ProductDao();
                    productDao.setProdCode(testProductTypes.get(i).getData().get(j).getNameCode());
                    productDao.setProdName(testProductTypes.get(i).getData().get(j).getNameItem());
                    productDao.setProviderId(testProductTypes.get(i).getData().get(j).getProvider());
                    productDao.setProductImg(testProductTypes.get(i).getData().get(j).getProductImg());
                    productDao.setProdInType(testProductTypes.get(i).getData().get(j).getProductInType());
                    productDao.setProdId(testProductTypes.get(i).getData().get(j).getProductId());
                    productDao.setProductQuantity(testProductTypes.get(i).getData().get(j).getProductQuantity());
                    productDao.setProductPrice(testProductTypes.get(i).getData().get(j).getProductPrice());
                    productDao.setProductAlert(testProductTypes.get(i).getData().get(j).getProductAlert());


                    Product list = testProductTypes.get(i).getData().get(j);

                    if (list.getDataItem() != null) {
                        for (int z = 0; z < list.getDataItem().size(); z++) {

                            Log.d(TAG, "init: " + list.getDataItem().size());
                            //productDao.setProductEachSizes();
                            ProductEachSize productEachSize = new ProductEachSize();
                            productEachSize.setAmongPerWrap(list.getDataItem().get(z).getAmongPerWrap());
                            productEachSize.setContrainUPiecePerBox(list.getDataItem().get(z).getContrainUPiecePerBox());
                            productEachSize.setDiameterOutsize(list.getDataItem().get(z).getDiameterOutsize());
                            productEachSize.setEffordUBaht(list.getDataItem().get(z).getEffordUBaht());
                            productEachSize.setLongPerWrap(list.getDataItem().get(z).getLongPerWrap());
                            productEachSize.setNameItemId(list.getDataItem().get(z).getNameItemId());
                            productEachSize.setNameItemSize(list.getDataItem().get(z).getNameItemSize());
                            productEachSize.setTotalItemBigUnit(list.getDataItem().get(z).getTotalItemBigUnit());
                            productEachSize.setUnit(list.getDataItem().get(z).getUnit());
                            productEachSize.setWeightPerWrap(list.getDataItem().get(z).getWeightPerWrap());

                            ProductEachSize.PriceUBahtBean priceUBahtBean = new ProductEachSize.PriceUBahtBean();
                            priceUBahtBean.setClassEightFive(list.getDataItem().get(z).getPricePerBath().getClassEightFive());
                            priceUBahtBean.setClassFive(list.getDataItem().get(z).getPricePerBath().getClassFive());
                            priceUBahtBean.setClassOne(list.getDataItem().get(z).getPricePerBath().getClassOne());

                            priceUBahtBean.setClassOneThreeFive(list.getDataItem().get(z).getPricePerBath().getClassOneThreeFive());
                            priceUBahtBean.setClassThree(list.getDataItem().get(z).getPricePerBath().getClassThree());
                            priceUBahtBean.setClassTwo(list.getDataItem().get(z).getPricePerBath().getClassTwo());
                            //Log.d(TAG, "init: "+list.getDataItem().get(z).getPriceUBaht().getClassTwo());
                            priceUBahtBean.setPerKilo(list.getDataItem().get(z).getPricePerBath().getPerKilo());
                            priceUBahtBean.setPerMeter(list.getDataItem().get(z).getPricePerBath().getPerMeter());
                            priceUBahtBean.setPerPiece(list.getDataItem().get(z).getPricePerBath().getPerPiece());
                            priceUBahtBean.setPerWrap(list.getDataItem().get(z).getPricePerBath().getPerWrap());
                            productEachSize.setPriceUBaht(priceUBahtBean);

                            productEachSizes.add(productEachSize);

                        }
                        productDao.setProductEachSizes(productEachSizes);

                    }
                    productList.add(productDao);
                }
            }
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/provider");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange111111: " + dataSnapshot.toString());

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProviderDao providerDao = new ProviderDao();
                    providerDao.setProvId(snapshot.getValue(ProviderDao.class).getProvId());
                    providerDao.setProvName(snapshot.getValue(ProviderDao.class).getProvName());
                    providerDao.setProvPhone(snapshot.getValue(ProviderDao.class).getProvPhone());
                    providerDao.setProvEmail(snapshot.getValue(ProviderDao.class).getProvEmail());
                    providerDao.setProvAddress(snapshot.getValue(ProviderDao.class).getProvAddress());
                    providerDaoList.add(providerDao);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);


        vpHorizontalNtb.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View mView = null;
                if (0 == position) {
                    mView = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_list_product_type_recycler, container, false);
                    recyclerView = (RecyclerView) mView.findViewById(R.id.rv);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyDataInventoryActivity.this));
                    inventoryProductTypeAdapter.setListRealm(testProductTypes);
                    inventoryProductTypeAdapter.setProductTypeDaos(dataDao);
                    inventoryProductTypeAdapter.setProductTypeList(productTypeList);
                    recyclerView.setAdapter(inventoryProductTypeAdapter);
                    container.addView(mView);
                } else if (1 == position) {
//                    for (int i = 0; i < testProductTypes.size(); i++) {
//                        if (testProductTypes.get(i).getData() != null) {
//                            // product list dummy
//                            for (int j = 0; j < testProductTypes.get(i).getData().size(); j++) {
//                                //Log.d(TAG, "init: " + i + " :" + dataDao.getProductType().get(i).getData().size());
//
//                                productEachSizes = new ArrayList<>();
//                                ProductDao productDao = new ProductDao();
//                                productDao.setProdCode(testProductTypes.get(i).getData().get(j).getNameCode());
//                                productDao.setProdName(testProductTypes.get(i).getData().get(j).getNameItem());
//                                productDao.setProviderId(testProductTypes.get(i).getData().get(j).getProvider());
//                                productDao.setProductImg(testProductTypes.get(i).getData().get(j).getProductImg());
//                                productDao.setProdInType(testProductTypes.get(i).getData().get(j).getProductInType());
//                                productDao.setProdId(testProductTypes.get(i).getData().get(j).getProductId());
//                                productDao.setProductQuantity(testProductTypes.get(i).getData().get(j).getProductQuantity());
//                                productDao.setProductPrice(testProductTypes.get(i).getData().get(j).getProductPrice());
//                                productDao.setProductAlert(testProductTypes.get(i).getData().get(j).getProductAlert());
//
//
//                                Product list = testProductTypes.get(i).getData().get(j);
//
//                                if (list.getDataItem() != null) {
//                                    for (int z = 0; z < list.getDataItem().size(); z++) {
//
//                                        Log.d(TAG, "init: " + list.getDataItem().size());
//                                        //productDao.setProductEachSizes();
//                                        ProductEachSize productEachSize = new ProductEachSize();
//                                        productEachSize.setAmongPerWrap(list.getDataItem().get(z).getAmongPerWrap());
//                                        productEachSize.setContrainUPiecePerBox(list.getDataItem().get(z).getContrainUPiecePerBox());
//                                        productEachSize.setDiameterOutsize(list.getDataItem().get(z).getDiameterOutsize());
//                                        productEachSize.setEffordUBaht(list.getDataItem().get(z).getEffordUBaht());
//                                        productEachSize.setLongPerWrap(list.getDataItem().get(z).getLongPerWrap());
//                                        productEachSize.setNameItemId(list.getDataItem().get(z).getNameItemId());
//                                        productEachSize.setNameItemSize(list.getDataItem().get(z).getNameItemSize());
//                                        productEachSize.setTotalItemBigUnit(list.getDataItem().get(z).getTotalItemBigUnit());
//                                        productEachSize.setUnit(list.getDataItem().get(z).getUnit());
//                                        productEachSize.setWeightPerWrap(list.getDataItem().get(z).getWeightPerWrap());
//
//                                        ProductEachSize.PriceUBahtBean priceUBahtBean = new ProductEachSize.PriceUBahtBean();
//                                        priceUBahtBean.setClassEightFive(list.getDataItem().get(z).getPricePerBath().getClassEightFive());
//                                        priceUBahtBean.setClassFive(list.getDataItem().get(z).getPricePerBath().getClassFive());
//                                        priceUBahtBean.setClassOne(list.getDataItem().get(z).getPricePerBath().getClassOne());
//
//                                        priceUBahtBean.setClassOneThreeFive(list.getDataItem().get(z).getPricePerBath().getClassOneThreeFive());
//                                        priceUBahtBean.setClassThree(list.getDataItem().get(z).getPricePerBath().getClassThree());
//                                        priceUBahtBean.setClassTwo(list.getDataItem().get(z).getPricePerBath().getClassTwo());
//                                        //Log.d(TAG, "init: "+list.getDataItem().get(z).getPriceUBaht().getClassTwo());
//                                        priceUBahtBean.setPerKilo(list.getDataItem().get(z).getPricePerBath().getPerKilo());
//                                        priceUBahtBean.setPerMeter(list.getDataItem().get(z).getPricePerBath().getPerMeter());
//                                        priceUBahtBean.setPerPiece(list.getDataItem().get(z).getPricePerBath().getPerPiece());
//                                        priceUBahtBean.setPerWrap(list.getDataItem().get(z).getPricePerBath().getPerWrap());
//                                        productEachSize.setPriceUBaht(priceUBahtBean);
//
//                                        productEachSizes.add(productEachSize);
//
//                                    }
//                                    productDao.setProductEachSizes(productEachSizes);
//
//                                }
//                                productList.add(productDao);
//                            }
//                        }
//                    }


//                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType");
//                    databaseReference.addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                        }
//
//                        @Override
//                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                            Log.d("changeData", "onChildChanged: " + dataSnapshot.toString() + "/" + s);
//                            List<DataDao.ProductTypeBean.DataBean> dataBeen = new ArrayList<>();
//                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                if ("data".equals(snapshot.getKey())) {
//                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                                        Log.d("changeData", "onChildChanged: " + snapshot1.toString());
//
//                                        Log.d("changeData", "onChildChangedssss: " + snapshot1.getKey());
//                                        DataDao.ProductTypeBean.DataBean dataBean = new DataDao.ProductTypeBean.DataBean();
//
//
//                                        dataBean.setNameItem(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getNameItem());
//                                        dataBean.setProductQuantity(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductQuantity());
//                                        dataBean.setProductPrice(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductPrice());
//                                        dataBean.setProductImg(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductImg());
//                                        dataBean.setProductId(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductId());
//                                        dataBean.setProductAlert(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductAlert());
//                                        dataBean.setNameCode(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getNameCode());
//                                        dataBean.setProductInType(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductInType());
//                                        dataBean.setProvider(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProvider());
//
//
//
//                                        dataBeen.add(dataBean);
//                                    }
//                                }
//                            }
//                            for(int i=0;i<dataBeen.size();i++){
//                                Log.d("producttt", "onChildChanged j: "+dataBeen.size());
//                                //Log.d("changeData", "onChildChanged: " + dataBeen.get(i).getProductQuantity());
//                                for(int j=0;j<productList.size();j++){
//                                    Log.d("producttt", "onChildChanged j: "+productList.size());
//
//                                    if(dataBeen.get(i).getProductId() == productList.get(j).getProdId()){
//                                        //DataDao.ProductTypeBean.DataBean list = dataDao.getProductType().get(i).getData().get(j);
//                                        productEachSizes = new ArrayList<>();
//
//                                        ProductDao productDao = new ProductDao();
//                                        productDao.setProdCode(dataBeen.get(i).getNameCode());
//                                        productDao.setProdName(dataBeen.get(i).getNameItem());
//                                        productDao.setProductQuantity(dataBeen.get(i).getProductQuantity());
//                                        productDao.setProductPrice(dataBeen.get(i).getProductPrice());
//                                        productDao.setProductImg(dataBeen.get(i).getProductImg());
//                                        productDao.setProdId(dataBeen.get(i).getProductId());
//                                        productDao.setProductAlert(dataBeen.get(i).getProductAlert());
//                                        productDao.setProdInType(dataBeen.get(i).getProductInType());
//                                        productDao.setProviderId(dataBeen.get(i).getProvider());
//
//
//
//                                        if (productList.get(j).getProductEachSizes() != null) {
//                                            for (int z = 0; z < productList.get(j).getProductEachSizes().size(); z++) {
//
//                                                Log.d("producttt", "initssssss: " + productList.get(j).getProductEachSizes().size());
//                                                //productDao.setProductEachSizes();
//                                                ProductEachSize productEachSize = new ProductEachSize();
//                                                productEachSize.setAmongPerWrap(productList.get(j).getProductEachSizes().get(z).getAmongPerWrap());
//                                                productEachSize.setContrainUPiecePerBox(productList.get(j).getProductEachSizes().get(z).getContrainUPiecePerBox());
//                                                productEachSize.setDiameterOutsize(productList.get(j).getProductEachSizes().get(z).getDiameterOutsize());
//                                                productEachSize.setEffordUBaht(productList.get(j).getProductEachSizes().get(z).getEffordUBaht());
//                                                productEachSize.setLongPerWrap(productList.get(j).getProductEachSizes().get(z).getLongPerWrap());
//                                                productEachSize.setNameItemId(productList.get(j).getProductEachSizes().get(z).getNameItemId());
//                                                productEachSize.setNameItemSize(productList.get(j).getProductEachSizes().get(z).getNameItemSize());
//                                                productEachSize.setTotalItemBigUnit(productList.get(j).getProductEachSizes().get(z).getTotalItemBigUnit());
//                                                productEachSize.setUnit(productList.get(j).getProductEachSizes().get(z).getUnit());
//                                                productEachSize.setWeightPerWrap(productList.get(j).getProductEachSizes().get(z).getWeightPerWrap());
//
//                                                ProductEachSize.PriceUBahtBean priceUBahtBean = new ProductEachSize.PriceUBahtBean();
//                                                priceUBahtBean.setClassEightFive(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getClassEightFive());
//                                                priceUBahtBean.setClassFive(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getClassFive());
//                                                priceUBahtBean.setClassOne(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getClassOne());
//
//                                                priceUBahtBean.setClassOneThreeFive(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getClassOneThreeFive());
//                                                priceUBahtBean.setClassThree(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getClassThree());
//                                                priceUBahtBean.setClassTwo(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getClassTwo());
//                                                //Log.d(TAG, "init: "+list.getDataItem().get(z).getPriceUBaht().getClassTwo());
//                                                priceUBahtBean.setPerKilo(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getPerKilo());
//                                                priceUBahtBean.setPerMeter(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getPerMeter());
//                                                priceUBahtBean.setPerPiece(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getPerPiece());
//                                                priceUBahtBean.setPerWrap(productList.get(j).getProductEachSizes().get(z).getPriceUBaht().getPerWrap());
//                                                productEachSize.setPriceUBaht(priceUBahtBean);
//
//                                                productEachSizes.add(productEachSize);
//
//                                            }
//                                            productDao.setProductEachSizes(productEachSizes);
//
//                                        }
//                                        productList.set(j,productDao);
//                                    }
//                                }
//                            }
//                            productAdapter.notifyDataSetChanged();
//
//                        }
//
//                        @Override
//                        public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                        }
//
//                        @Override
//                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });

                    mView = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_list_product_recycler, container, false);
                    recyclerView = (RecyclerView) mView.findViewById(R.id.rv);
                    recyclerView.setItemAnimator(new FadeInAnimator());
                    //recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyDataInventoryActivity.this));
                    productAdapter.setProductList(productList, productTypeList, providerDaoList);
                    productAdapter.setListRealm(testProductTypes);
                    AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(productAdapter);
                    alphaAdapter.setFirstOnly(true);
                    alphaAdapter.setDuration(1500);
                    alphaAdapter.setInterpolator(new OvershootInterpolator(.5f));
                    //Log.d("prices", "instantiateItem: "+productList.get(0).getProductEachSizes().get(0).getPriceUBaht().getClassOne());

                    recyclerView.setAdapter(alphaAdapter);
                    container.addView(mView);
                } else if (2 == position) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/provider");
                    databaseReference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            Log.d(TAG, "onDataChange1111112222: " + dataSnapshot.toString());
                            ProviderDao providerDao = new ProviderDao();
                            providerDao.setProvId(dataSnapshot.getValue(ProviderDao.class).getProvId());
                            providerDao.setProvName(dataSnapshot.getValue(ProviderDao.class).getProvName());
                            providerDao.setProvPhone(dataSnapshot.getValue(ProviderDao.class).getProvPhone());
                            providerDao.setProvEmail(dataSnapshot.getValue(ProviderDao.class).getProvEmail());
                            providerDao.setProvAddress(dataSnapshot.getValue(ProviderDao.class).getProvAddress());

                            Log.d(TAG, "onDataChange1111112222: " + providerDao.getProvName());
                            for (int i = 0; i < providerDaoList.size(); i++) {
                                if (providerDaoList.get(i).getProvId() == providerDao.getProvId()) {
                                    providerDaoList.set(i, providerDao);
                                    inventoryProviderAdapter.notifyDataSetChanged();
                                }
                            }

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    if (providerDaoList.size() > 0) {
                        mView = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_list_provider_recycler, container, false);
                        recyclerView = (RecyclerView) mView.findViewById(R.id.rv);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MyDataInventoryActivity.this));
                        inventoryProviderAdapter.setData(providerDaoList);
                        recyclerView.setAdapter(inventoryProviderAdapter);
                        container.addView(mView);
                    }
                }
                return mView;

            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MyDataInventoryActivity.this, R.drawable.boxes),
                        Color.parseColor(colors[1]))
                        .title("Heart")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MyDataInventoryActivity.this, R.drawable.box),
                        Color.parseColor(colors[1]))
                        .title("Cup")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MyDataInventoryActivity.this, R.drawable.truck),
                        Color.parseColor(colors[1]))
                        .title("Diploma")
                        .build()
        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        ContextCompat.getDrawable(MyDataInventoryActivity.this,R.drawable.ic_fourth),
//                        Color.parseColor(colors[3]))
//                        .title("Flag")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        ContextCompat.getDrawable(MyDataInventoryActivity.this,R.drawable.ic_fifth),
//                        Color.parseColor(colors[4]))
//                        .title("Medal")
//                        .build()
//        );

        ntbHorizontal.setModels(models);
        ntbHorizontal.setViewPager(vpHorizontalNtb, 0);
        ntbHorizontal.setBgColor(ContextCompat.getColor(MyDataInventoryActivity.this, R.color.colorPrimary));
        ntbHorizontal.setIsTinted(false);
        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        ntbHorizontal.setBehaviorEnabled(true);

        ntbHorizontal.setOnTabBarSelectedIndexListener(TabBarSelected);
        ntbHorizontal.setOnPageChangeListener(pageChanged);

        fab.setOnClickListener(fabClicklistener);

    }

    private void setupPageDrawer() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(toolbarClicklistener);
    }


    private NavigationTabBar.OnTabBarSelectedIndexListener TabBarSelected = new NavigationTabBar.OnTabBarSelectedIndexListener() {
        @Override
        public void onStartTabSelected(NavigationTabBar.Model model, int index) {

        }

        @Override
        public void onEndTabSelected(NavigationTabBar.Model model, int index) {
            model.hideBadge();
        }
    };

    private ViewPager.OnPageChangeListener pageChanged = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //Log.d(TAG, "onPageScrolled: "+position);
            changeViewpager = position;
            if (0 == position) {
                tvToolbarTitle.setText("ชนิดสินค้า");
            } else if (1 == position) {
                tvToolbarTitle.setText("สินค้า");
            } else if (2 == position) {
                tvToolbarTitle.setText("ผู้จำหน่าย");
            }
        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected: " + position);
            if (0 == position) {
                inventoryProductTypeAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG, "onPageScrollStateChanged: " + testProductTypes.size());

        }
    };

    private View.OnClickListener fabClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            for (int i = 0; i < ntbHorizontal.getModels().size(); i++) {
//                final NavigationTabBar.Model model = ntbHorizontal.getModels().get(i);
//                ntbHorizontal.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final String title = String.valueOf(new Random().nextInt(15));
//                        if (!model.isBadgeShowed()) {
//                            model.setBadgeTitle(title);
//                            model.showBadge();
//                        } else model.updateBadgeTitle(title);
//                    }
//                }, i * 100);
//            }
//
//            paRent.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    snackbar = Snackbar.make(ntbHorizontal, "Coordinator NTB", Snackbar.LENGTH_SHORT);
//                    snackbar.getView().setBackgroundColor(Color.parseColor("#9b92b3"));
//                    ((TextView) snackbar.getView().findViewById(R.id.snackbar_text))
//                            .setTextColor(Color.parseColor("#423752"));
//                    snackbar.show();
//                }
//            },1000);

            new BottomSheet.Builder(MyDataInventoryActivity.this, R.style.BottomSheet_CustomizedDialog).title("Slide Down").sheet(R.menu.list_fab).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedViewPager(which);
                }
            }).show();
        }
    };

    private void selectedViewPager(int which) {
        if (0 == changeViewpager) {
            if (which == R.id.add_new_record) {
                customDialog(changeViewpager);
            }
        } else if (1 == changeViewpager) {
            if (which == R.id.add_new_record) {
                customDialog(changeViewpager);
            }
        } else if (2 == changeViewpager) {
            if (which == R.id.add_new_record) {
                customDialog(changeViewpager);
            }
        }

    }

    private void customDialog(int target) {
        if (0 == target) {
            final Dialog dialog = new Dialog(MyDataInventoryActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.fragment_add_product_type);
            dialog.setCancelable(true);

            final AppCompatEditText edProdTypeCode = (AppCompatEditText) dialog.findViewById(R.id.ed_prod_type_code);
            final AppCompatEditText edProdTypeName = (AppCompatEditText) dialog.findViewById(R.id.ed_prod_type_name);
            final AppCompatEditText edProdTypeDes = (AppCompatEditText) dialog.findViewById(R.id.ed_prod_type_des);
            Button btnAddProdTypeConfirm = (Button) dialog.findViewById(R.id.btn_add_prod_type_confirm);

            btnAddProdTypeConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + dataDao.getProductType().size());
                    if ((!"".equals(edProdTypeCode.getText().toString())) &&
                            (!"".equals(edProdTypeName.getText().toString())) &&
                            (!"".equals(edProdTypeDes.getText().toString()))
                            ) {
                        sp = getSharedPreferences(MyPreference, MODE_PRIVATE);
                        if (0 == sp.getInt("index", 0)) {
                            editor = sp.edit();
                            editor.putInt("index", dataDao.getProductType().size());
                            editor.apply();
                        } else {
                            editor = sp.edit();
                            int i = sp.getInt("index", 0);
                            editor.putInt("index", i + 1);
                            editor.apply();
                        }


                        int index = sp.getInt("index", 0);
                        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

                        HashMap<String, Object> postValues = new HashMap<>();
                        postValues.put("name", edProdTypeName.getText().toString());
                        postValues.put("status", "success");
                        postValues.put("typeCode", edProdTypeCode.getText().toString());
                        postValues.put("typeDes", edProdTypeDes.getText().toString());
                        postValues.put("typeId", index + 1);

                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/productType/" + index, postValues);
                        mRootRef.updateChildren(childUpdates);


                        ProductTypeDao productTypeDao = new ProductTypeDao();
                        productTypeDao.setProdTypeName(edProdTypeName.getText().toString());
                        productTypeDao.setProdTypeCode(edProdTypeCode.getText().toString());
                        productTypeDao.setProdTypeDes(edProdTypeDes.getText().toString());
                        productTypeList.add(productTypeDao);
                        dialog.dismiss();

                    }

                }
            });


            dialog.show();
        } else if (1 == target) {
            Intent intent = new Intent(MyDataInventoryActivity.this, AddProductOnFabActivity.class);
            intent.putExtra("data", dataDao);
            intent.putParcelableArrayListExtra("dataType", (ArrayList<ProductTypeDao>) productTypeList);
            startActivityForResult(intent, PRODUCT);
        } else if (2 == target) {
            final Dialog dialog = new Dialog(MyDataInventoryActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.fragment_add_provider);
            dialog.setCancelable(true);

            final AppCompatEditText edProvName = (AppCompatEditText) dialog.findViewById(R.id.ed_prov_name);
            final AppCompatEditText edProvAddress = (AppCompatEditText) dialog.findViewById(R.id.ed_prov_address);
            final AppCompatEditText edProvPhone = (AppCompatEditText) dialog.findViewById(R.id.ed_prov_phone);
            final AppCompatEditText edProvEmail = (AppCompatEditText) dialog.findViewById(R.id.ed_prov_email);
            Button btnAddProvConfirm = (Button) dialog.findViewById(R.id.btn_add_prov_confirm);

            btnAddProvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((!"".equals(edProvName.getText().toString())) &&
                            (!"".equals(edProvAddress.getText().toString())) &&
                            (!"".equals(edProvPhone.getText().toString())) &&
                            (!"".equals(edProvEmail.getText().toString()))
                            ) {

                        sp = getSharedPreferences(MyPreference, MODE_PRIVATE);
                        if (0 == sp.getInt("index_provider", 0)) {
                            editor = sp.edit();
                            editor.putInt("index_provider", providerDaoList.size() + 1);
                            editor.apply();
                        } else {
                            editor = sp.edit();
                            int i = sp.getInt("index_provider", 0);
                            editor.putInt("index_provider", i + 1);
                            editor.apply();
                        }

                        int index = sp.getInt("index_provider", 0);

                        //ProviderDao providerDao = new ProviderDao();
                        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference databaseReference = mRootRef.child("provider");


                        String key = databaseReference.push().getKey();
                        HashMap<String, Object> postValues = new HashMap<>();

                        postValues.put("provId", index);
                        postValues.put("provName", edProvName.getText().toString());
                        postValues.put("provAddress", edProvAddress.getText().toString());
                        postValues.put("provEmail", edProvEmail.getText().toString());
                        postValues.put("provPhone", edProvPhone.getText().toString());

                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/provider/" + key, postValues);
                        mRootRef.updateChildren(childUpdates);

                        ProviderDao providerDao1 = new ProviderDao();
                        providerDao1.setProvId(index);
                        providerDao1.setProvName(edProvName.getText().toString());
                        providerDao1.setProvAddress(edProvAddress.getText().toString());
                        providerDao1.setProvEmail(edProvEmail.getText().toString());
                        providerDao1.setProvPhone(edProvPhone.getText().toString());
                        providerDaoList.add(providerDao1);


                        dialog.dismiss();
                    } else {

                    }
                }
            });

            dialog.show();
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyDataInventoryActivity.PRODUCT_TYPE) {
            Log.d(TAG, "onActivityResult: product type");

            if (data.getExtras().getInt("index") != -1) {
                Log.d(TAG, "onActivityResult: " + data.getExtras().getInt("index"));
            }
        }
        if (requestCode == PRODUCT) {
            if (data != null) {
                Log.d(TAG, "onActivityResult: " + data.getStringExtra("nameItem"));
                ProductDao productDao = new ProductDao();
                productDao.setProdName(data.getStringExtra("nameItem"));
                productDao.setProdCode(data.getStringExtra("nameCode"));
                productDao.setProviderId(data.getIntExtra("provider", -1));
                productDao.setProductImg(data.getStringExtra("productImg"));
                productDao.setProdInType(data.getIntExtra("productType", -1));
                productDao.setProdId(data.getIntExtra("productId", -1));
                productDao.setProductPrice(data.getIntExtra("productPrice", -1));
                productDao.setProductQuantity(data.getIntExtra("productQuantity", -1));
                productDao.setProductAlert(data.getIntExtra("productAlert", -1));


                productList.add(productDao);


                List<DataDao.ProductTypeBean.DataBean> dataBeenlist = new ArrayList<>();
                DataDao.ProductTypeBean.DataBean dataBean = new DataDao.ProductTypeBean.DataBean();
                dataBean.setProvider(data.getIntExtra("provider", -1));
                dataBean.setProductUnit(data.getStringExtra("productUnit"));
                dataBean.setNameCode(data.getStringExtra("nameCode"));
                dataBean.setNameItem(data.getStringExtra("nameItem"));
                dataBean.setProductAlert(data.getIntExtra("productAlert", -1));
                dataBean.setProductId(data.getIntExtra("productId", -1));
                dataBean.setProductPrice(data.getIntExtra("productPrice", -1));
                dataBean.setProductQuantity(data.getIntExtra("productQuantity", -1));
                dataBean.setProductImg(data.getStringExtra("productImg"));
                dataBeenlist.add(dataBean);

                dataDao.getProductType().get(data.getIntExtra("productType", -1)).setData(dataBeenlist);
                productAdapter.notifyDataSetChanged();
                inventoryProductTypeAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == PROVIDER) {
//            ProviderDao providerDao = new ProviderDao();
//            providerDao.setProvPhone(data.getStringExtra("provPhone"));
//            providerDao.setProvEmail(data.getStringExtra("provEmail"));
//            providerDao.setProvAddress(data.getStringExtra("provAddress"));
//            providerDao.setProvName(data.getStringExtra("provName"));
//            Log.d("provider_index", "onActivityResult: "+data.getIntExtra("provider_index",-1));
//            providerDaoList.set(data.getIntExtra("provider_index",-1),providerDao);
//            inventoryProviderAdapter.notifyDataSetChanged();


        } else if (requestCode == EDIT_PRODUCT) {


//            for(int i=0;i<productList.size();i++){
//                if(productList.get(i).getProdId() == data.getIntExtra("productId",-1)){
//                    ProductDao productDao = new ProductDao();
//                    productDao.setProdName(data.getStringExtra("nameItem"));
//                    productDao.setProdCode(data.getStringExtra("nameCode"));
//                    productDao.setProductAlert(data.getIntExtra("productAlert",-1));
//                    if(!"".equals(data.getStringExtra("productImg"))){
//                        productDao.setProductImg(data.getStringExtra("productImg"));
//                    }
//                    productDao.setProdInType(data.getIntExtra("ProdInType",-1));
//                    productDao.setProductPrice(data.getIntExtra("productPrice",-1));
//                    productDao.setProductQuantity(data.getIntExtra("productQuantity",-1));
//                    productDao.setProdId(data.getIntExtra("productId",-1));
//                    productDao.setProviderId(data.getIntExtra("provider",-1));
//
//
//                    //intent.putExtra("productUnit",productDao.ge());
//                    productList.set(i,productDao);
//                    productAdapter.notifyDataSetChanged();
//
//                }
//            }


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
