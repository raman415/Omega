package com.newproject.omega;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;
    private Button continueBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recycler_view);
        continueBtn = view.findViewById(R.id.cart_continue_btn);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cartItemsRecyclerView.setLayoutManager(linearLayoutManager);


        List<CartItemModel> cartItemModelList = new ArrayList<>();

        cartItemModelList.add(new CartItemModel(0, R.mipmap.transiphonex, "iPhone x",
                2, "$1400", "$1700", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.mipmap.transiphonex, "iPhone x",
                // testing regarding offers applied.
                0, "$1400", "$1700", 1, 1, 0));
        cartItemModelList.add(new CartItemModel(0, R.mipmap.transiphonex, "iPhone x",
                2, "$1400", "$1700", 1, 2, 0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 items) ","$ 1800/-",
                "Free","$1800/-","" +
                "$100"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(),AddAddressActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });

        return view;

    }

}
