package com.newproject.omega;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> horizontalProductScrollModelList;


    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
    this.horizontalProductScrollModelList= horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        // All ways we have to show 4 items that why we are returning four items over here.
        //return 4;
        return horizontalProductScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView == null){
            // Concept
            // Over here, when the activity loads checks we something to show or not
            // if it do not have then simply paste given layout which provide with help of ids
            // then finally we have return view which had been inflated. to that layout
            view = LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            // First we are going to bind our widgets with ids.. or DATA Binding
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            // this clikc listener will attach product details layouts to the clickable grid items.
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(),ProductDetailsActivity.class);
                   parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.hsProductImage);
            TextView productTitle = view.findViewById(R.id.hsProductTitle);
            TextView productDescription = view.findViewById(R.id.hsProductDescription);
            TextView productPrice = view.findViewById(R.id.hsProductPrice);

            // After this, we set our data on variables

            productImage.setImageResource(horizontalProductScrollModelList.get(position).getProductImage());
            productTitle.setText(horizontalProductScrollModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductScrollModelList.get(position).getProductDescription());
            productPrice.setText(horizontalProductScrollModelList.get(position).getProductPrice());

        }else {
           view=convertView;
        }
        return view;
    }
}
