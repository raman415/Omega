package com.newproject.omega;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.ViewHolder> {
    private List<RewardModel> rewardModelList;

    private Boolean useMinilayout = false;


    public MyRewardsAdapter(List<RewardModel> rewardModelList,Boolean useMinilayout) {
        this.rewardModelList = rewardModelList;
        this.useMinilayout = useMinilayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (useMinilayout){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_rewards_item_layout, parent,false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent,false);
        }


      // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = rewardModelList.get(position).getTitle();
        String date = rewardModelList.get(position).getExpireDate();
        String body = rewardModelList.get(position).getCoupenBody();

        holder.setData(title,date,body);

    }

    @Override
    public int getItemCount() {
        return rewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView coupenTitle;
        private TextView coupenExpireDate;
        private TextView coupenBody;

        public ViewHolder(View itemView) {
            super(itemView);

            coupenTitle = itemView.findViewById(R.id.rewards_coupen_title);
            coupenExpireDate = itemView.findViewById(R.id.rewards_coupen_validity);
            coupenBody = itemView.findViewById(R.id.rewards_coupen_body);
        }
        private void setData(final String title, final String date, final String body){

            coupenTitle.setText(title);
            coupenExpireDate.setText(date);
            coupenBody.setText(body);


            if (useMinilayout){
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ProductDetailsActivity.coupenTitle.setText(title);
                        ProductDetailsActivity.coupenExpiryDate.setText(date);
                        ProductDetailsActivity.coupenbody.setText(body);
                        ProductDetailsActivity.showDialogRecyclerView();
                    }
                });
            }

        }
    }
}
