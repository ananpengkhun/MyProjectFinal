package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.MainMenuAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.kmpautotextview.KMPAutoComplTextView;


public class MainActivity extends AppCompatActivity implements MainFragment.MoveFragmentPage {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.ll_sliding_bar) LinearLayout llSlidingBar;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @BindView(R.id.vp_pager_fragment) ViewPager vpPagerFragment;
    @BindView(R.id.tv_navData) TextView tvNavData;
    @BindView(R.id.tv_navAddData) TextView tvNavAddData;
    @BindView(R.id.img_backHome) ImageView imgBackHome;
    @BindView(R.id.tvAutoCompl) KMPAutoComplTextView tvAutoCompl;
    @BindView(R.id.imv_clear_text) ImageView imvClearText;


    private BottomSheet dialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainMenuAdapter mainMenuAdapter;
    private String TAG = MainActivity.class.getSimpleName();

    //private DataDao dataDao;
    private List<DataDao.ProductTypeBean> productTypeDaos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupPageDrawer();
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerLayout.getDrawerLockMode(Gravity.LEFT) == DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private void init() {

        if(getIntent().getParcelableArrayListExtra("data") != null){
            productTypeDaos = getIntent().getParcelableArrayListExtra("data");
        }

        mainMenuAdapter = new MainMenuAdapter(getSupportFragmentManager(),productTypeDaos);
        vpPagerFragment.setAdapter(mainMenuAdapter);

        tvNavAddData.setOnClickListener(AddDataClicklistener);
        tvNavData.setOnClickListener(DataClicklistener);
        imgBackHome.setOnClickListener(imgBackHomeClicklistener);
        imvClearText.setOnClickListener(CleatTextClicklistener);

        vpPagerFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: ");
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    private void setupPageDrawer() {
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this
                , drawerLayout
                , R.string.open_drawer
                , R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // dummy data for search on navigationBar
        List<String> data = new ArrayList<String>();
        data.add("Red roses for wedding");
        data.add("Bouquet with red roses");
        data.add("Single red rose flower");

        tvAutoCompl.setDatas(data);
        tvAutoCompl.setOnPopupItemClickListener(new KMPAutoComplTextView.OnPopupItemClickListener() {
            @Override
            public void onPopupItemClick(CharSequence charSequence) {
                //Toast.makeText(MainActivity.this, charSequence.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                tvAutoCompl.setText("");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.d(TAG, "onOptionsItemSelected: ");
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


//    public void setDrawerState(boolean isEnabled) {
//        if (isEnabled) {
//            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//            actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.STATE_IDLE);
//            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//            actionBarDrawerToggle.syncState();
//        } else {
//            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//            actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.STATE_DRAGGING);
//            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//            actionBarDrawerToggle.syncState();
//        }
//    }

    @Override
    public void pageSelected(int index) {
        vpPagerFragment.setCurrentItem(index, true);
    }

    @Override
    public void onBackPressed() {
        if (vpPagerFragment.getCurrentItem() == 0) {
            super.onBackPressed();
        } else if (vpPagerFragment.getCurrentItem() != 0) {
            vpPagerFragment.setCurrentItem(0, true);
        }

    }

    private View.OnClickListener AddDataClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            dialog = new BottomSheet.Builder(MainActivity.this, R.style.BottomSheet_CustomizedDialog).title("Slide Down").sheet(R.menu.list_by_add).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case R.id.prodctByType:
                            Log.d(TAG, "prodctByType: ");
                            vpPagerFragment.setCurrentItem(2, true);
                            break;
                        case R.id.productByAdd:
                            Log.d(TAG, "onClick: productByAdd");
                            vpPagerFragment.setCurrentItem(1, true);
                            break;
                        case R.id.providerByAdd:
                            Log.d(TAG, "onClick: providerByAdd");
                            vpPagerFragment.setCurrentItem(3, true);
                            break;
                    }
                }
            }).show();

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Log.d(TAG, "onDismiss: close");
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            });

            if (dialog.isShowing()) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

        }
    };

    private View.OnClickListener DataClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MyDataInventoryActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener imgBackHomeClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            vpPagerFragment.setCurrentItem(0, true);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    };

    private View.OnClickListener CleatTextClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tvAutoCompl.getText().clear();
        }
    };

}
