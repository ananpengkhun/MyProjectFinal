package com.example.ananpengkhun.myprojectfinal.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.btn_click) Button btnClick;

    private MoveFragmentPage moveFragmentPage;

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
                            startActivity(intent);
                            //setDrawerState(true);
                            break;
                    }
                }
            }).show();
        }
    };

    public static Fragment newInstant() {
        return new MainFragment();
    }
}
