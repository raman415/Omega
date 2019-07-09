package com.newproject.omega;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // over here we will inflate layout of items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        // List is initialize through constructor
        // we have to fetch each single item one by one and have to bind with viewHolder
        // from position we get exact location

        String icon = categoryModelList.get(position).getCategoryIconlink();
        String name = categoryModelList.get(position).getCategoryName();

        // call function with Reference from ViewHolder class
        holder.setCategory(name, position);
        holder.setCategoryIcon(icon);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIcon;
        private TextView categoryName;


        public ViewHolder(View itemView) {
            super(itemView);


            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_iconName);
        }

        private void setCategoryIcon(String iconUrl) {
            // this function is used to access items from the database
            //// todo: set categoryicons here;
            if (!iconUrl.equals("null")) {
                Glide.with(itemView.getContext()).load(iconUrl).
                        apply(new RequestOptions().placeholder(R.mipmap.redhome)).into(categoryIcon);
            }
        }

        private void setCategory(final String name, final int position) {


            categoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != 0) {
                        Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        // key value relationship
                        categoryIntent.putExtra("categoryName", name);
                        itemView.getContext().startActivity(categoryIntent);

                    }


                }
            });
        }
    }
}
