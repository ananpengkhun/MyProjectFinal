package com.example.ananpengkhun.myprojectfinal.adapter;

import android.content.Context;
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
import com.example.ananpengkhun.myprojectfinal.dao.Product;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductEachSize;
import com.example.ananpengkhun.myprojectfinal.dao.Productsize;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by ananpengkhun on 12/27/16.
 */

public class EachItemSizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ProductDao dao;
    private Context mContext;
    private List<ProductEachSize> productDao;

    private boolean swap = true;
    private Realm realm;


    public EachItemSizeAdapter(Context mContext, List<ProductEachSize> productDao, ProductDao dao) {
        this.mContext = mContext;
        this.productDao = productDao;
        this.dao = dao;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_size_item, parent, false);
        return new EachItemSizeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof EachItemSizeViewHolder) {
//            setTextView(productDao.getProdCode(),
//                    productDao.getProdName(),
//                    productDao.getPrice(),
//                    productDao.getProdAmount(),
//                    productDao.getProdUnit(),
//                    productDao.getProdAlert());
            final EachItemSizeViewHolder eachItemSizeViewHolder = (EachItemSizeViewHolder) holder;
            eachItemSizeViewHolder.tvNameProdSize.setText(productDao.get(position).getNameItemSize());
            eachItemSizeViewHolder.tvUnitProd.setText(productDao.get(position).getUnit());
            eachItemSizeViewHolder.tvPriceClassOne.setText(productDao.get(position).getPriceUBaht().getClassOne());
            eachItemSizeViewHolder.tvPriceClassTwo.setText(productDao.get(position).getPriceUBaht().getClassTwo());
            Log.d("eachitem", "onBindViewHolder: " + productDao.get(position).getPriceUBaht().getClassTwo());
            eachItemSizeViewHolder.tvPriceClassThree.setText(productDao.get(position).getPriceUBaht().getClassThree());
            eachItemSizeViewHolder.tvPriceClassFive.setText(productDao.get(position).getPriceUBaht().getClassFive());
            eachItemSizeViewHolder.tvPriceClassEightFive.setText(productDao.get(position).getPriceUBaht().getClassEightFive());
            eachItemSizeViewHolder.tvPriceClassOneThreeFive.setText(productDao.get(position).getPriceUBaht().getClassOneThreeFive());
            eachItemSizeViewHolder.tvPricePerKilo.setText(productDao.get(position).getPriceUBaht().getPerKilo());
            eachItemSizeViewHolder.tvPricePerMeter.setText(productDao.get(position).getPriceUBaht().getPerMeter());
            eachItemSizeViewHolder.tvPricePerPiece.setText(productDao.get(position).getPriceUBaht().getPerPiece());
            eachItemSizeViewHolder.tvPricePerWrap.setText(productDao.get(position).getPriceUBaht().getPerWrap());
            eachItemSizeViewHolder.tvEfford.setText(productDao.get(position).getEffordUBaht());
            eachItemSizeViewHolder.tvAmountPerWrap.setText(productDao.get(position).getAmongPerWrap());
            eachItemSizeViewHolder.tvAlertProd.setText(productDao.get(position).getTotalItemBigUnit());
            eachItemSizeViewHolder.tvAlert.setText(String.valueOf(productDao.get(position).getProductSizeAlert()));


            eachItemSizeViewHolder.imvBoxForEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (eachItemSizeViewHolder.imvBoxForEdit.isSelected()) {

                        eachItemSizeViewHolder.imvBoxForEdit.setSelected(false);

//                        textView Visible
//                        eachItemSizeViewHolder.tvChooseSpinner.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvCodeProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvNamePro.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPricePro.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvAmountProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvUnitProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvProviderProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvAlertProd.setVisibility(View.VISIBLE);
//
//                        EditText Gone
                        eachItemSizeViewHolder.edAlertProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.edUnitProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.edAmountProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.edPriceProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.edNameProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.spinner.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.spinnerProvider.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.edCodeProd.setVisibility(View.GONE);
//
                        //quantity.set(position, eachItemSizeViewHolder.edAlertProd.getText().toString());
                        ProductEachSize each = new ProductEachSize();
                        ProductEachSize.PriceUBahtBean priceUBahtBean = new ProductEachSize.PriceUBahtBean();
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassOne());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassTwo());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassThree());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassFive());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassEightFive());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassOneThreeFive());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getPerKilo());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getPerMeter());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getPerPiece());
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getPerWrap());

                        each.setPriceUBaht(priceUBahtBean);
                        each.setUnit(productDao.get(position).getUnit());
                        each.setNameItemSize(productDao.get(position).getNameItemSize());
                        each.setTotalItemBigUnit(eachItemSizeViewHolder.edAlertProd.getText().toString());
                        each.setEffordUBaht(productDao.get(position).getEffordUBaht());
                        each.setAmongPerWrap(productDao.get(position).getAmongPerWrap());
                        each.setProductSizeAlert(productDao.get(position).getProductSizeAlert());
                        each.setIndexInProduct(productDao.get(position).getIndexInProduct());
                        each.setNameItemId(productDao.get(position).getNameItemId());
                        productDao.set(position,each);
                        notifyDataSetChanged();
//                        prodName = edNameProd.getText().toString();
//                        prodPrice = edPriceProd.getText().toString();
//                        prodAmount = Integer.parseInt(edAmountProd.getText().toString());
//                        prodUnit = edUnitProd.getText().toString();
//                        prodAlert = Integer.parseInt(edAlertProd.getText().toString());
                        //eachItemSizeViewHolder.tvAlertProd.setText(quantity.get(position));


//                        save data
                        //edit realm
                        Productsize size = realm.where(Productsize.class).equalTo("nameItemId",productDao.get(position).getNameItemId()).findFirst();
                        realm.beginTransaction();
                        size.setTotalItemBigUnit(eachItemSizeViewHolder.edAlertProd.getText().toString());
                        realm.commitTransaction();

                        //edit firebase
                        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType/" + dao.getProdInType());
                        mRootRef.child("data/"+ productDao.get(position).getIndexInProduct() +"/dataItem/"+position+"/totalItemBigUnit").setValue(eachItemSizeViewHolder.edAlertProd.getText().toString());


                    } else {
                        //edit data

                        eachItemSizeViewHolder.imvBoxForEdit.setSelected(true);
//                        textView Gone
                        eachItemSizeViewHolder.tvAlertProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvProviderProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvUnitProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvAmountProd.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvPricePro.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvNamePro.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvChooseSpinner.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.tvCodeProd.setVisibility(View.GONE);
//                        EditText Visible
                        eachItemSizeViewHolder.edAlertProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.edUnitProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.edAmountProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.edPriceProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.edNameProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.spinner.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.spinnerProvider.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.edCodeProd.setVisibility(View.VISIBLE);
//
                        if (swap) {
                            swap = false;
                            eachItemSizeViewHolder.edAlertProd.setText(productDao.get(position).getTotalItemBigUnit());

                        } else {
                            eachItemSizeViewHolder.edAlertProd.setText(eachItemSizeViewHolder.tvAlertProd.getText());
                        }
                    }

                }

            });
        }
    }

    @Override
    public int getItemCount() {
        if (productDao == null) {
            return 0;
        }
        return productDao.size();
    }


    public void setRealm(Realm realm) {
        this.realm = realm;
    }
}
