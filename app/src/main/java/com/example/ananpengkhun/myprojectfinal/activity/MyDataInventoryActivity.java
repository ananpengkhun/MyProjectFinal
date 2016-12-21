package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProductAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProductTypeAdapter;
import com.example.ananpengkhun.myprojectfinal.adapter.InventoryProviderAdapter;
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
    @BindView(R.id.ll_sliding_bar) LinearLayout llSlidingBar;
    @BindView(R.id.parent) CoordinatorLayout paRent;
    @BindView(R.id.tv_toolbarTitle) TextView tvToolbarTitle;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private Snackbar snackbar;


    private List<ProductDao> productList;
    private List<ProductTypeDao> productTypeList;
    private List<ProviderDao> providerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data_inventory);
        ButterKnife.bind(this);
        setupPageDrawer();
        dummyValue();
        init();
    }

    private void dummyValue() {
        productList = new ArrayList<>();
        productTypeList = new ArrayList<>();
        providerList = new ArrayList<>();


        // product type list dummy
        for (int i = 0; i < 10; i++) {
            ProductTypeDao productTypeDao = new ProductTypeDao();
            productTypeDao.setProdTypeName("ประเภทที่ " + i);
            productTypeList.add(productTypeDao);
        }

        // product list dummy
        for (int i = 0; i < 20; i++) {
            ProductDao productDao = new ProductDao();
            productDao.setProdName("สิ้นค้าชิ้นที่ " + i);
            productDao.setPrice("1,000");
            productList.add(productDao);
        }

        //provider list dummy
        for (int i = 0; i < 3; i++) {
            ProviderDao providerDao = new ProviderDao();
            providerDao.setProvName("บริษัทืั้ " + i);
            providerList.add(providerDao);
        }

    }

    private void init() {
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
                    recyclerView.setAdapter(new InventoryProductTypeAdapter(MyDataInventoryActivity.this, productTypeList));
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
        actionBarDrawerToggle = new ActionBarDrawerToggle(MyDataInventoryActivity.this
                , drawerLayout
                , R.string.open_drawer
                , R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setDrawerState(true);
    }

    public void setDrawerState(boolean isEnabled) {
        if (isEnabled) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.STATE_IDLE);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.syncState();
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.STATE_DRAGGING);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            actionBarDrawerToggle.syncState();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
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
            if(0 == position){
                tvToolbarTitle.setText("ชนิดสินค้า");
            }else if(1 == position){
                tvToolbarTitle.setText("สินค้า");
            }else if(2 == position){
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

            new BottomSheet.Builder(MyDataInventoryActivity.this, R.style.BottomSheet_CustomizedDialog).title("Slide Down").sheet(R.menu.list).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case R.id.help:
                            Log.d(TAG, "onClick: ");
                            break;
                        case R.id.addProduct:
                            break;
                        case R.id.prodct:
                            Intent intent = new Intent(MyDataInventoryActivity.this, MyDataInventoryActivity.class);
                            startActivity(intent);
                            //setDrawerState(true);
                            break;
                    }
                }
            }).show();
        }
    };


}
