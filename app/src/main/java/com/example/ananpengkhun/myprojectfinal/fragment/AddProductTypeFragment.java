package com.example.ananpengkhun.myprojectfinal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductTypeFragment extends Fragment {


    @BindView(R.id.ed_prod_type_code) AppCompatEditText edProdTypeCode;
    @BindView(R.id.ed_prod_type_name) AppCompatEditText edProdTypeName;
    @BindView(R.id.ed_prod_type_des) AppCompatEditText edProdTypeDes;
    @BindView(R.id.btn_add_prod_type_confirm) Button btnAddProdTypeConfirm;

    public AddProductTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_add_product_type, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static Fragment newInstant() {
        return new AddProductTypeFragment();
    }
}
