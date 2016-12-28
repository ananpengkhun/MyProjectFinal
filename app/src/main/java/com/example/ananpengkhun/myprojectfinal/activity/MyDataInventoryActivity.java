package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProductAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProductTypeAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProviderAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

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


    public static final int PRODUCT_TYPE = 1;
    public static final int PRODUCT = 2;
    public static final int PROVIDER = 3;

    private DataDao dataDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data_inventory);
        ButterKnife.bind(this);
        setupPageDrawer();
        dummyValue();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void dummyValue() {


        providerList = new ArrayList<>();

//        for (int i = 0; i < 6; i++) {
//            ProductDao productDao = new ProductDao();
//            productDao.setProdCode("000" + i);
//            productDao.setProdName("สิ้นค้าชิ้นที่ " + i);
//            productDao.setPrice("1,000");
//            productDao.setProdAmount(100);
//            productDao.setProdUnit("m");
//            if (productTypeList != null) {
//                productDao.setProdType(productTypeList);
//            } else {
//                productDao.setProdType(null);
//
//            }
//
//            if (providerList != null) {
//                productDao.setProdProvider(providerList);
//            } else {
//                productDao.setProdProvider(null);
//            }
//            productDao.setProdAlert(5);
//            productList.add(productDao);
//
//        }


    }

    private void init() {
        productList = new ArrayList<>();
        productTypeList = new ArrayList<>();

        if (getIntent().getParcelableExtra("data") != null) {
            dataDao = getIntent().getParcelableExtra("data");
            //Log.d(TAG, "init: "+dataDao.getProductType().get(0).getName());
        }

        // product type list dummy
        for (int i = 0; i < dataDao.getProductType().size(); i++) {
            ProductTypeDao productTypeDao = new ProductTypeDao();
            productTypeDao.setProdTypeName(dataDao.getProductType().get(i).getName());
            productTypeDao.setProdTypeCode(dataDao.getProductType().get(i).getTypeCode());
            productTypeDao.setProdTypeDes(dataDao.getProductType().get(i).getTypeDes());
            productTypeList.add(productTypeDao);

            // product list dummy
            for (int j = 0; j < dataDao.getProductType().get(i).getData().size(); j++) {
                ProductDao productDao = new ProductDao();
                productDao.setProdCode(dataDao.getProductType().get(i).getData().get(j).getNameCode());
                productDao.setProdName(dataDao.getProductType().get(i).getData().get(j).getNameItem());
                productList.add(productDao);


                ProviderDao providerDao = new ProviderDao();
                providerDao.setProvName(dataDao.getProductType().get(i).getData().get(j).getProvider());
                providerDao.setProvAddress("street :" + j);
                providerDao.setProvPhone("080000000" + j);
                providerDao.setProvEmail("men_2537za@" + j + ".com");
                providerList.add(providerDao);

            }
        }


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
                    recyclerView.setAdapter(new InventoryProductTypeAdapter(MyDataInventoryActivity.this, productTypeList, dataDao));
                    container.addView(mView);
                } else if (1 == position) {
                    mView = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_list_product_recycler, container, false);
                    recyclerView = (RecyclerView) mView.findViewById(R.id.rv);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyDataInventoryActivity.this));
                    recyclerView.setAdapter(new InventoryProductAdapter(MyDataInventoryActivity.this, productList));
                    container.addView(mView);
                } else if (2 == position) {
                    mView = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_list_provider_recycler, container, false);
                    recyclerView = (RecyclerView) mView.findViewById(R.id.rv);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyDataInventoryActivity.this));
                    recyclerView.setAdapter(new InventoryProviderAdapter(MyDataInventoryActivity.this, providerList));
                    container.addView(mView);
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
            //Log.d(TAG, "onPageSelected: "+position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
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

//            Button button1 = (Button) dialog.findViewById(R.id.button1);
//            button1.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext()
//                            , "Close dialog", Toast.LENGTH_SHORT);
//                    dialog.cancel();
//                }
//            });
//
//            TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
//            textView1.setText("Custom Dialog");
//            TextView textView2 = (TextView) dialog.findViewById(R.id.textView2);
//            textView2.setText("Try it yourself");

            dialog.show();
        } else if (1 == target) {
            Intent intent = new Intent(MyDataInventoryActivity.this, AddProductOnFabActivity.class);
            startActivity(intent);
        } else if (2 == target) {
            final Dialog dialog = new Dialog(MyDataInventoryActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.fragment_add_provider);
            dialog.setCancelable(true);

//            Button button1 = (Button) dialog.findViewById(R.id.button1);
//            button1.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext()
//                            , "Close dialog", Toast.LENGTH_SHORT);
//                    dialog.cancel();
//                }
//            });
//
//            TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
//            textView1.setText("Custom Dialog");
//            TextView textView2 = (TextView) dialog.findViewById(R.id.textView2);
//            textView2.setText("Try it yourself");

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyDataInventoryActivity.PRODUCT_TYPE) {
            Log.d(TAG, "onActivityResult: product type");

            if (data.getExtras().getInt("index") != -1) {
                Log.d(TAG, "onActivityResult: " + data.getExtras().getInt("index"));
            }
        }
    }


}
