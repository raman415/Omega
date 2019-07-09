package com.newproject.omega;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new CartItemViewHolder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewHolder(cartTotalView);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int resourse = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupens = cartItemModelList.get(position).getFreeCoupens();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPrice();
                int offersApplied = cartItemModelList.get(position).getOffersApplied();
                ((CartItemViewHolder) holder).setItemDetails(resourse, title, freeCoupens, productPrice, cuttedPrice, offersApplied);
                break;
            case CartItemModel.TOTAL_AMOUNT:

                String totalItems = cartItemModelList.get(position).getTotalItems();
                String totalItemPrice = cartItemModelList.get(position).getTotalItemPrice();
                String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount = cartItemModelList.get(position).getTotalAmount();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();

                ((CartTotalAmountViewHolder) holder).setTotalAmount(totalItems, totalItemPrice, deliveryPrice, totalAmount, savedAmount);

                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }


    // Actually this kind of the class is actually used for
    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private ImageView freeCoupensIcon;
        private TextView freeCoupens;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offeredApplied;
        private TextView coupensApplied;
        private TextView productQuantity;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.wish_product_image);
            productTitle = itemView.findViewById(R.id.wish_product_title);
            freeCoupens = itemView.findViewById(R.id.Wishtv_free_coupon);
            freeCoupensIcon = itemView.findViewById(R.id.Wishfree_coupen_icon);
            productPrice = itemView.findViewById(R.id.product_price);
            offeredApplied = itemView.findViewById(R.id.offers_applied);
            cuttedPrice = itemView.findViewById(R.id.wish_cutted_price);
            coupensApplied = itemView.findViewById(R.id.coupens_Applied);
            productQuantity = itemView.findViewById(R.id.product_quantity);

        }

        private void setItemDetails(int res, String title, int freeCoupensNo,
                                    String productPriceText, String cuttedPriceText, int offeresAppliedNO) {
            productImage.setImageResource(res);
            productTitle.setText(title);

            if (freeCoupensNo > 0) {
                freeCoupensIcon.setVisibility(View.VISIBLE);
                freeCoupens.setVisibility(View.VISIBLE);
                if (freeCoupensNo == 1) {
                    freeCoupens.setText("free (" + freeCoupensNo + ") Coupen");

                } else {
                    freeCoupens.setText("free ("+ freeCoupensNo + ")Coupens");

                }

            } else {
                freeCoupensIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);

            }

            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);

            if (offeresAppliedNO > 0) {
                offeredApplied.setVisibility(View.VISIBLE);
               /*
               * android.content.res.Resources$NotFoundException: String resource ID #0x1
               * offeredApplied.setText(offeresAppliedNO);
               * over here method accepts only string type of value where we are trying to provide integer value so that
               * why error is coming.
               * */ offeredApplied.setText("["+offeresAppliedNO+"] Offers Applied ");
            } else {
                offeredApplied.setVisibility(View.INVISIBLE);
                // offeredApplied.setText(offeresAppliedNO);
            }
            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog quantityDialog = new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantity_dialog);
                    quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    quantityDialog.setCancelable(false);
                    final EditText quantityNumber = quantityDialog.findViewById(R.id.dialog_quantity_no);
                    Button cancelBtn = quantityDialog.findViewById(R.id.quantity_cancel_btn);
                    Button okBtn = quantityDialog.findViewById(R.id.quantity_ok_btn);

                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityDialog.dismiss();
                        }
                    });

                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productQuantity.setText("Qty: "+quantityNumber.getText());
                            quantityDialog.dismiss();
                        }
                    });
                    quantityDialog.show();

                }
            });

            //  productQuantity.setVisibility(View.VISIBLE);
        }
    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewHolder(View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);

            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemTxt, String totalItemPriceTxt,
                                    String deliveryPriceTxt, String totalAmountTxt, String savedAmtTxt) {
            totalItems.setText(totalItemTxt);
            totalItemPrice.setText(totalItemPriceTxt);
            deliveryPrice.setText(deliveryPriceTxt);
            totalAmount.setText(totalAmountTxt);
            savedAmount.setText(savedAmtTxt);
        }
    }
}
