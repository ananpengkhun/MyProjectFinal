package com.example.ananpengkhun.myprojectfinal.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.MainActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.ProductExpiredAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.btn_click) Button btnClick;
    @BindView(R.id.rc_expired_product) RecyclerView rcExpiredProduct;


    private MoveFragmentPage moveFragmentPage;
    private ProductExpiredAdapter productExpiredAdapter;
    private List<ProductDao> productDaolist;

    private DataDao dataDao;
    //private List<DataDao.ProductTypeBean> productTypeDaos;

    public interface MoveFragmentPage{
        void pageSelected(int index);
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        moveFragmentPage = (MoveFragmentPage) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        //if(bundle != null){
        dataDao = bundle.getParcelable("data");
        productDaolist = new ArrayList<>();



        //}
        for(int i=0;i<dataDao.getProductType().size();i++){
            if(dataDao.getProductType().get(i).getData() != null) {
                for (int j = 0; j < dataDao.getProductType().get(i).getData().size(); j++) {
                    if (dataDao.getProductType().get(i).getData().get(j).getProductAlert() > dataDao.getProductType().get(i).getData().get(j).getProductQuantity()) {
                        ProductDao productDao = new ProductDao();
                        productDao.setProdName(dataDao.getProductType().get(i).getData().get(j).getNameItem());
                        productDao.setProdCode(dataDao.getProductType().get(i).getData().get(j).getNameCode());
                        productDao.setProviderId(dataDao.getProductType().get(i).getData().get(j).getProductId());
                        if (dataDao.getProductType().get(i).getData().get(j).getProductImg() != null) {
                            productDao.setProductImg(dataDao.getProductType().get(i).getData().get(j).getProductImg());
                        }
                        productDaolist.add(productDao);

                    }
                }
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.from(container.getContext()).inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        productExpiredAdapter = new ProductExpiredAdapter(getActivity());
        productExpiredAdapter.setData(productDaolist);
        rcExpiredProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcExpiredProduct.setHasFixedSize(true);
        rcExpiredProduct.setAdapter(productExpiredAdapter);



        btnClick.setOnClickListener(btnSlideUpClicklisner);
    }

    private View.OnClickListener btnSlideUpClicklisner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new BottomSheet.Builder(getActivity(),R.style.BottomSheet_CustomizedDialog).title("Slide Down").sheet(R.menu.list).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case R.id.help:
                            Log.d(TAG, "onClick: ");
                            break;
                        case R.id.addProduct :
                            new BottomSheet.Builder(getActivity(),R.style.BottomSheet_CustomizedDialog).title("Slide Down").sheet(R.menu.list_by_add).listener(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case R.id.prodctByType:
                                            Log.d(TAG, "prodctByType: ");
                                            moveFragmentPage.pageSelected(2);
                                            break;
                                        case R.id.productByAdd :
                                            Log.d(TAG, "onClick: productByAdd");
                                            moveFragmentPage.pageSelected(1);
                                            break;
                                        case R.id.providerByAdd :
                                            Log.d(TAG, "onClick: providerByAdd");
                                            moveFragmentPage.pageSelected(3);
                                            break;
                                    }
                                }
                            }).show();
                            break;
                        case R.id.prodct :
                            Intent intent = new Intent(getActivity(),MyDataInventoryActivity.class);
                            if(dataDao != null){
                                intent.putExtra("data",dataDao);
                            }
                            startActivity(intent);
                            //setDrawerState(true);
                            break;
                    }
                }
            }).show();
        }
    };

    public static Fragment newInstant(DataDao dataDao) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", dataDao);
        //Log.d(TAG, "newInstant: "+dataDao.size());

        //DataDao dataDao1 = bundle.getParcelable("data");
        //Log.d(TAG, "newInstant: "+dataDao1.getProductType().get(0).getName());


        //Log.d(TAG, "newInstant: "+dataDao.getProductType().get(0).getName());
        mainFragment.setArguments(bundle);
        return mainFragment;
    }
}
