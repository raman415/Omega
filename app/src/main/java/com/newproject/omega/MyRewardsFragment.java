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
public class MyRewardsFragment extends Fragment {

    private RecyclerView rewardsRecyclerView;

    public MyRewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);

        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewardsRecyclerView.setLayoutManager(linearLayoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("CashBack", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Discount", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Discount", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));

        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("CashBack", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("CashBack", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Discount", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("CashBack", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("CashBack", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Discount", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("CashBack", "Till June 2nd,2016", "GET 20% cash back on any product above $ 200/- and below $3000/-"));


        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList,false);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return view;
    }

}
