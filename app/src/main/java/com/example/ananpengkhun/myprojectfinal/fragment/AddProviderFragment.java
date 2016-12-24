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
public class AddProviderFragment extends Fragment {


    @BindView(R.id.ed_prov_name) AppCompatEditText edProvName;
    @BindView(R.id.ed_prov_address) AppCompatEditText edProvAddress;
    @BindView(R.id.ed_prov_phone) AppCompatEditText edProvPhone;
    @BindView(R.id.ed_prov_email) AppCompatEditText edProvEmail;
    @BindView(R.id.btn_add_prov_confirm) Button btnAddProvConfirm;

    public AddProviderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_add_provider, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static Fragment newInstant() {
        return new AddProviderFragment();
    }
}
