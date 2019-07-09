package com.newproject.omega;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModel> sliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.slider_layout,container,false);

         ConstraintLayout bannerContainer = view.findViewById(R.id.banner_container);
         // it will fetch bac
         bannerContainer.setBackgroundTintList(ColorStateList
                 .valueOf(Color.parseColor(sliderModelList.get(position).getBackgroundColor())));
        // we have to access image
        ImageView imageViewBanner = view.findViewById(R.id.banner_slide);
        // to fetch one by one image
        imageViewBanner.setImageResource(sliderModelList.get(position).getBanner());
        container.addView(view,0);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }
}
