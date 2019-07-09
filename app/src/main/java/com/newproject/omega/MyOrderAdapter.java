package com.newproject.omega;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int res = myOrderItemModelList.get(position).getOrderProductImage();
        int rating = myOrderItemModelList.get(position).getRating();
        String title = myOrderItemModelList.get(position).getOrderProductTitle();
        String deliveryStatusDate = myOrderItemModelList.get(position).getOrderDeliveryStatus();

        holder.setOrderData(res, title, deliveryStatusDate, rating);

    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       /*
       *
       * i learn something very interesting .
       * previously i did mistake and i declared my linearLayout rateNowContainer
       * outside of ViewHolder class
       * this means viewholder class have only access of the late item of recyclerView through which
       * onBindViewHolder gone through
       *
       * so the concept is that this subclass holds the access of new generated view
       * or view-customized layout if you want to access each and every newly generated view-layout
       * then please declared their Context or data type inside this Viewholder class
       *
       * */
        private ImageView orderProductImage;
        private ImageView orderDeliveryIndicator;
        private TextView orderProducTitle;
        private TextView deliveryStatusDate;
        private LinearLayout rateNowContainer;



        public ViewHolder(final View itemView) {
            super(itemView);
            orderProductImage = itemView.findViewById(R.id.orders_product_image);
            orderProducTitle = itemView.findViewById(R.id.wish_product_title);
            orderDeliveryIndicator = itemView.findViewById(R.id.order_indicator);
            deliveryStatusDate = itemView.findViewById(R.id.order_delivered_date);
            rateNowContainer = itemView.findViewById(R.id.rate_now_container);

            // when we want to some action on over layout-view which is generated using RecyclerView

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent =
                            new Intent(itemView.getContext(),OrderDetailsActivity.class);

                    itemView.getContext().startActivity(orderDetailsIntent);
                    
                }
            });


        }

        private void setOrderData(int res, String title, String date, int rating) {
            orderProductImage.setImageResource(res);
            orderProducTitle.setText(title);
            if (date.equals("cancelled")) {
                orderDeliveryIndicator.setImageTintList(ColorStateList
                        .valueOf(itemView.getContext().getResources().getColor(R.color.btnColor)));
            } else {
                orderDeliveryIndicator.setImageTintList(ColorStateList
                        .valueOf(itemView.getContext().getResources().getColor(R.color.succesGreen)));
            }
            deliveryStatusDate.setText(date);


            // first we are going to put listener on all stars
            // Below for loop will going to attach onClickListener
            // more specifically it apply click-listener on each child one by one

            //we want default rating from customer or user
           setRating(rating);
            for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
                final int starPosition = x;
                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // this method is resposible for changing color
                        // passing position of each star
                        // e.g if star is 2 and 2 value is passed
                        setRating(starPosition);
                    }
                });

            }

        }
        private void setRating(int starPosition) {
            // this loop will set color of each star gray
            for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
                ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
                // over here all the star will be applied gray color
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CECACA")));
                if (x <= starPosition) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }

            }
        }


    }
}
