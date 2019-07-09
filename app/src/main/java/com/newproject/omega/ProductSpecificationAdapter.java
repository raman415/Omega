package com.newproject.omega;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    private List<ProductSpecificationModel> productSpecificationModelList;


    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }


    @Override
    public int getItemViewType(int position) {
        switch (productSpecificationModelList.get(position).getType()) {
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITLE;
            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {

            case ProductSpecificationModel.SPECIFICATION_TITLE:
                // By code we are going to add Description : Interesting Concept.
                // Giving value and properties using java code rather then using XML file.
                TextView title = new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                // setting up width and height
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout
                                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                // margins...
                // given below functions taking value in integer whereas in XML we provide value in dp
                // to sort this we have to create a method which act as converter convering dp into integer values.


                layoutParams.setMargins(setDp(16, parent.getContext())
                        , setDp(16, parent.getContext())
                        , setDp(16, parent.getContext())
                        , setDp(8, parent.getContext())); // 8 dp margin from bottom

                title.setLayoutParams(layoutParams);

                return new ViewHolder(title);


            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_specification_item_layout, parent, false);
                return new ViewHolder(view);

            default:
                return null;


        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        switch (productSpecificationModelList.get(position).getType()) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:

                holder.setTitle(productSpecificationModelList.get(position).getTitle());
                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureTitle = productSpecificationModelList.get(position).getFeatureName();
                String featureDetail = productSpecificationModelList.get(position).getFeatureValue();

                holder.setFeatures(featureTitle, featureDetail);
                break;

            default:
                return;


        }


    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView featureName;
        private TextView featureValue;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

        }

        private void setTitle(String titleText) {
            title = (TextView) itemView;
            title.setText(titleText);
        }

        private void setFeatures(String featureTitle, String featureDetail) {
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
            featureName.setText(featureTitle);
            featureValue.setText(featureDetail);
        }
    }

    private int setDp(int dp, Context context) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }
}
