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
public class MyOrdersFragment extends Fragment {

  private RecyclerView myOrderRecyclerView;

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrderRecyclerView= view.findViewById(R.id.myOrdersRecycler_View);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrderRecyclerView.setLayoutManager(linearLayoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.s8phone,"Samany S9 mate Black","Delivered on Monday,15th june 2019",2));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.transiphonex,"Samany S9 mate Black","Delivered on Monday,15th june 2019",1));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.transiphonex,"Samany S9 mate Black","cancelled",2));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.s8phone,"Samany S9 mate Black","Delivered on Monday,15th june 2019",2));


        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrderRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }

}
