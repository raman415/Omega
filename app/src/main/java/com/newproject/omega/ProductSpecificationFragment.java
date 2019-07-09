package com.newproject.omega;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment {


    private RecyclerView productSpecificationRecylerView;

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);


        productSpecificationRecylerView = view.findViewById(R.id.productSpecificationRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        productSpecificationRecylerView.setLayoutManager(linearLayoutManager);

        // dume lists

        List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();

        productSpecificationModelList.add(new ProductSpecificationModel(0,"General"));

        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));

        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));

        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4 GB"));


        ProductSpecificationAdapter productSpecificationAdapter =
                new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecylerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }

}
