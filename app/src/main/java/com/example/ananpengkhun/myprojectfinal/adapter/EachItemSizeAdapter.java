package com.example.ananpengkhun.myprojectfinal.adapter;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.EachItemSizeViewHolder;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/27/16.
 */

public class EachItemSizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DetailOfListProductActivity detailOfListProductActivity;

    public EachItemSizeAdapter(DetailOfListProductActivity detailOfListProductActivity) {
        this.detailOfListProductActivity = detailOfListProductActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_size_item, parent, false);
        return new EachItemSizeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EachItemSizeViewHolder){
//            setTextView(productDao.getProdCode(),
//                    productDao.getProdName(),
//                    productDao.getPrice(),
//                    productDao.getProdAmount(),
//                    productDao.getProdUnit(),
//                    productDao.getProdAlert());
            final EachItemSizeViewHolder eachItemSizeViewHolder = (EachItemSizeViewHolder) holder;
            eachItemSizeViewHolder.imvBoxForEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eachItemSizeViewHolder.imvBoxForEdit.isSelected()) {

                        eachItemSizeViewHolder.imvBoxForEdit.setSelected(false);

                        //textView Visible
                        eachItemSizeViewHolder.tvChooseSpinner.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvCodeProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvNamePro.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvPricePro.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvAmountProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvUnitProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvProviderProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvAlertProd.setVisibility(View.VISIBLE);

                        //EditText Gone
                        eachItemSizeViewHolder.edAlertProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.edUnitProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.edAmountProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.edPriceProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.edNameProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.spinner.setVisibility(View.GONE);
                        eachItemSizeViewHolder.spinnerProvider.setVisibility(View.GONE);
                        eachItemSizeViewHolder.edCodeProd.setVisibility(View.GONE);

//                        prodCode = edCodeProd.getText().toString();
//                        prodName = edNameProd.getText().toString();
//                        prodPrice = edPriceProd.getText().toString();
//                        prodAmount = Integer.parseInt(edAmountProd.getText().toString());
//                        prodUnit = edUnitProd.getText().toString();
//                        prodAlert = Integer.parseInt(edAlertProd.getText().toString());
//                        setTextView(prodCode, prodName, prodPrice, prodAmount, prodUnit, prodAlert);
//
//
//                        if (spinner.getSelectedItem() == null) {
//                            Log.d("s", "onClick: ");
//                        } else {
//                            tvChooseSpinner.setText(spinner.getSelectedItem().toString());
//                        }
//
//                        if (spinnerProvider.getSelectedItem() == null) {
//                            Log.d("s", "onClick: ");
//                        } else {
//                            tvProviderProd.setText(spinnerProvider.getSelectedItem().toString());
//                        }

                        //save data


                    } else {
                        //edit data
                        eachItemSizeViewHolder.imvBoxForEdit.setSelected(true);
                        //textView Gone
                        eachItemSizeViewHolder.tvAlertProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvProviderProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvUnitProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvAmountProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvPricePro.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvNamePro.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvChooseSpinner.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvCodeProd.setVisibility(View.GONE);
                        //EditText Visible
                        eachItemSizeViewHolder.edAlertProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edUnitProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edAmountProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edPriceProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edNameProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.spinner.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.spinnerProvider.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edCodeProd.setVisibility(View.VISIBLE);

//                        if (swap) {
//                            swap = false;
//                            setTextEdit(productDao.getProdCode(),
//                                    productDao.getProdName(),
//                                    productDao.getPrice(),
//                                    productDao.getProdAmount(),
//                                    productDao.getProdUnit(),
//                                    productDao.getProdAlert());
//
//                        } else {
//                            setTextEdit(prodCode, prodName, prodPrice, prodAmount, prodUnit, prodAlert);
//
//                        }
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
