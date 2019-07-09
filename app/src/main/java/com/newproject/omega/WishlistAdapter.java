package com.newproject.omega;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    List<WishlistModel> wishlistModelList;
    private Boolean wishList;

    public WishlistAdapter(List<WishlistModel> wishlistModelList,Boolean wishList) {
        this.wishList = wishList;
        this.wishlistModelList = wishlistModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);

        // Everytime, new View of each and every item_layout is created.
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resourse = wishlistModelList.get(position  ).getProductImage();
        String title = wishlistModelList.get(position).getProductTitle();
        int freeCouens = wishlistModelList.get(position).getFreeCoupens();
        String ratings = wishlistModelList.get(position).getRating();
        int totalRatings = wishlistModelList.get(position).getTotalRatings();
        String productPrice = wishlistModelList.get(position).getProductPrice();
        String cuttedPrice = wishlistModelList.get(position).getCuttedPrice();
        String paymentMethod = wishlistModelList.get(position).getPaymentMethod();


        holder.setData(resourse,title,freeCouens,ratings,totalRatings,productPrice,cuttedPrice,paymentMethod);
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView freecoupens;
        private ImageView coupenIcon;
        private TextView totalRatings;
        private TextView rating;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deleteBtn;
        private  View priceCutDivider;


        public ViewHolder(View itemView){
            super(itemView);
            productImage = itemView.findViewById(R.id.wish_product_image);
            productTitle = itemView.findViewById(R.id.wish_product_title);
            freecoupens = itemView.findViewById(R.id.Wishtv_free_coupon);
            coupenIcon = itemView.findViewById(R.id.Wishfree_coupen_icon);
            rating = itemView.findViewById(R.id.Wishtv_productRating_miniView);
            totalRatings = itemView.findViewById(R.id.WishtotalRating);
            productPrice = itemView.findViewById(R.id.wishProduct_price);
            cuttedPrice = itemView.findViewById(R.id.wish_cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            priceCutDivider = itemView.findViewById(R.id.price_cut_divider);

        }
        private void setData(int res, String title, int freecoupensNo, String avgRate, int totalRatingsNO, String price, String cuttedPriceValue, String payMethod){
            productImage.setImageResource(res);
            productTitle.setText(title);

            if (freecoupensNo != 0){
                coupenIcon.setVisibility(View.VISIBLE);
                if (freecoupensNo == 1){
                    freecoupens.setText("Free  ["+freecoupensNo+"] coupen");

                }
                else {
                    freecoupens.setText("Free  ["+freecoupensNo+"] coupens");

                }
            }else{
                coupenIcon.setVisibility(View.INVISIBLE);
                freecoupens.setVisibility(View.INVISIBLE);
            }

            rating.setText(avgRate + "");
            totalRatings.setText(totalRatingsNO + " (ratings)");
            productPrice.setText(price );
            cuttedPrice.setText(cuttedPriceValue);
            paymentMethod.setText(payMethod);

            if (wishList){
                deleteBtn.setVisibility(View.VISIBLE);
            }else {
                deleteBtn.setVisibility(View.INVISIBLE);
            }
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"Delete ",Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent( itemView.getContext(),ProductDetailsActivity.class);
                            itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
