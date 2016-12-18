package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.btn_click) Button btnClick;
    @BindView(R.id.ll_sliding_bar) LinearLayout llSlidingBar;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupPageDrawer();
        init();

    }

    private void init() {
        btnClick.setOnClickListener(btnSlideUpClicklisner);
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

    private View.OnClickListener btnSlideUpClicklisner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new BottomSheet.Builder(MainActivity.this,R.style.BottomSheet_CustomizedDialog).title("Slide Down").sheet(R.menu.list).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case R.id.help:
                            Log.d(TAG, "onClick: ");
                            break;
                        case R.id.addProduct :
                            Log.d(TAG, "onClick: add product");
                            break;
                        case R.id.prodct :
                            Intent intent = new Intent(MainActivity.this,MyDataInventoryActivity.class);
                            startActivity(intent);
                            setDrawerState(true);
                            break;
                    }
                }
            }).show();
        }
    };

    public void setDrawerState(boolean isEnabled) {
        if ( isEnabled ) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.STATE_IDLE);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.syncState();
        }else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.STATE_DRAGGING);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            actionBarDrawerToggle.syncState();
        }
    }


}
