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
public class MyWishListFragment extends Fragment {

   private RecyclerView wishListRecyclerView;
    public MyWishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_wish_list, container, false);

        wishListRecyclerView = view.findViewById(R.id.mywish_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishListRecyclerView.setLayoutManager(linearLayoutManager);


        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.drawable.s8phone,"Samsung S9+",1,"3",149,"$ 1300/-","$ 1700/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.s8phone,"Samsung S9+",0,"3",149,"$ 1300/-","$ 1700/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.s8phone,"Samsung S9+",2,"3",149,"$ 1300/-","$ 1700/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.s8phone,"Samsung S9+",0,"3",149,"$ 1300/-","$ 1700/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.s8phone,"Samsung S9+",5,"3",149,"$ 1300/-","$ 1700/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.s8phone,"Samsung S9+",1,"3",149,"$ 1300/-","$ 1700/-","Cash on delivery"));



        // over here second agrument we have passed true because we give boolean variable in the constructor
        // of the WishlistAdapter where it gona check we are calling this adapter from which activity or
        // fragment then it true will decide whether to show remove button or not.
        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList,true);
        wishListRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();
        return view;
    }

}
