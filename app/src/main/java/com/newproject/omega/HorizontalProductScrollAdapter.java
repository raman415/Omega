package com.newproject.omega;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HorizontalProductScrollAdapter
        extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList){
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }


    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_scroll_item_layout,parent,false);




        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {


        // Data binding is almost done.

        int resourse = (horizontalProductScrollModelList.get(position).getProductImage());
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();

        holder.setProductImage(resourse);
        holder.setProductTitle(title);
        holder.setProductDescription(description);
        holder.setProductPrice(price);



    }

    @Override
    public int getItemCount() {

        if (horizontalProductScrollModelList.size()>8){
            return 8;
        }else {
            return horizontalProductScrollModelList.size();
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(final View itemView) {
            super(itemView);// all upper defined variables are inside itemView


            productImage = itemView.findViewById(R.id.hsProductImage);
            productTitle = itemView.findViewById(R.id.hsProductTitle);
            productDescription = itemView.findViewById(R.id.hsProductDescription);
            productPrice = itemView.findViewById(R.id.hsProductPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });



        }

        private void setProductImage(int resourse){
            productImage.setImageResource(resourse);
        }

        private void setProductTitle(String title){
            productTitle.setText(title);
        }

        private void setProductDescription(String description){
            productDescription.setText(description);
        }

        private void setProductPrice(String price){
            productPrice.setText(price);
        }
    }
}
