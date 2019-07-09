package com.newproject.omega;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        // creating recyclerViewPool
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        // before creating the layout this method is going to get called
        // like this method is going to return what kind of layout we are dealing with.

        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            // this Banner_Slider directly means what is kind of ViewType
            // which can we easily accessed in onCreateViewHolder() ---> int viewType parameter
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.sliding_ad_bannner, parent, false);

                return new BannerSliderViewHolder(bannerSliderView);
            case HomePageModel.STRIP_AD_BANNER:
                View stripAdView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.strip_ad_layout, parent, false);

                return new StripAdBannerViewHolder(stripAdView);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.horizontal_scroll_layout, parent, false);

                return new HorizontalProductViewHolder(horizontalProductView);

            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grid_product_layout, parent, false);

                return new GridProductViewHolder(gridProductView);


            default:
                return null;


        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // this method is used for data binding
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList =
                        homePageModelList.get(position).getSliderModelList();
                // Over here we cannot access ViewHolder directly we need to do casting
                // Because we are going to access multi-ViewHolder.
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                int resource = homePageModelList.get(position).getResourse();
                String color = homePageModelList.get(position).getBackgroundColor();

                ((StripAdBannerViewHolder) holder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList =
                        homePageModelList.get(position).getHorizontalProductScrollModelList();

                ((HorizontalProductViewHolder) holder)
                        .setHorizontalLayout(horizontalProductScrollModelList, horizontalLayoutTitle);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> gridProductScrollModelList =
                        homePageModelList.get(position).getHorizontalProductScrollModelList();

                ((GridProductViewHolder) holder)
                        .setGridProductLayout(gridProductScrollModelList, gridLayoutTitle);
                break;


            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }


    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        // bug is that as we use bannerSlider during the scroll of up and down ...
        // new recyclerView is created again and again which increases it's speed.
        private ViewPager bannerSliderViewPager;
        private int currentPage ;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;

        public BannerSliderViewHolder(View itemView) {
            super(itemView);

            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            currentPage = 2;
            if( timer != null){
                timer.cancel();
                // fixed bug of timer which increase the speed of slide animation
            }
            arrangedList = new ArrayList<>();

            // Over here my sliderModelList is simply copied in the ArrangedList.
            for (int i = 0; i < sliderModelList.size() ; i++) {

                arrangedList.add(i,sliderModelList.get(i));

            }

            arrangedList.add(0,sliderModelList.get(sliderModelList.size()-2));
            arrangedList.add(1,sliderModelList.get(sliderModelList.size()-1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            SliderAdapter sliderAdapter = new SliderAdapter(arrangedList);
            // if we don't set our adapter nothing will be displayed.
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);
            // it our by default banner is setted
            bannerSliderViewPager.setCurrentItem(currentPage);
            // going to create Page listener
            // whenever if the page scroll down with any methon then below method will be called.
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    // Means animation from one to other is completed now if it's free on that time we run page looper
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangedList);
            // this Touch listener is used when user tap on any slide it will get stopped
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    pageLooper(arrangedList);
                    stopBannerSlideShow();
                    // if user tapped off his finger the slide must go again in motion
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrangedList);
                    }

                    return false;
                }
            });

        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            // for infinitly scroll view
            // it will attach first page to last page
            // interesting logic to provide the illusion of infinite loop.
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                // over here we have to set animation false because we never want to know the user
                // he/she has shifted on previous view.
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                // over here we have to set animation false because we never want to know the user
                // he/she has shifted on previous view.
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }


        }

        // going to make animation start and stop system
        private void startBannerSlideShow(final List<SliderModel> sliderModelList) {
            // creating new thread use time
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSlideShow() {
            timer.cancel();
        }


    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        // we have access our views simply imageView and Background

        private ImageView stripImage;
        private ConstraintLayout stripAdContanier;

        public StripAdBannerViewHolder(View itemView) {
            super(itemView);
            stripImage = itemView.findViewById(R.id.stripAdImage);
            stripAdContanier = itemView.findViewById(R.id.stripAdContainer);
        }

        private void setStripAd(int resource, String color) {
            stripImage.setImageResource(resource);
            stripImage.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {

        private TextView horizontalLayoutTitle;
        private Button viewAllBtn;
        private RecyclerView horizontalRecyclerView;

        public HorizontalProductViewHolder(View itemView) {
            super(itemView);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontalScrollTitle);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontalScrollRecyclerView);
            viewAllBtn = itemView.findViewById(R.id.horizontalScrollBtn);

            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);

        }

        private void setHorizontalLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {

            horizontalLayoutTitle.setText(title);

            if (horizontalProductScrollModelList.size() > 8) {
                viewAllBtn.setVisibility(View.VISIBLE);
                viewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewAllIntent = new Intent(itemView.getContext(),ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code",0);

                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                viewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter =
                    new HorizontalProductScrollAdapter(horizontalProductScrollModelList);


            // Over here, we are setting up how to view our container.
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);

            horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutTitle;
        private Button gridViewAllButton;
        private GridLayout gridProductLayout;

       // private GridView gridView; we are not this anymore.

        public GridProductViewHolder(View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.gridProductLayoutTitle);
            gridViewAllButton = itemView.findViewById(R.id.gridProductLayoutbtn);
            gridProductLayout = itemView.findViewById(R.id.grid_layout);
           // gridView = itemView.findViewById(R.id.gridProductLayoutView);

        }

        private void setGridProductLayout(List<HorizontalProductScrollModel>
                                                  horizontalProductScrollModelList, String title) {
            gridLayoutTitle.setText(title);

          //  gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));
            // As we can see the above code which simply fetchs our data from the list.
            // but now we use different technique.

            for (int x = 0 ;x < 4;x++){
                ImageView productImageView = gridProductLayout.getChildAt(x).findViewById(R.id.hsProductImage);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.hsProductTitle);
                TextView productDescription = gridProductLayout.getChildAt(x).findViewById(R.id.hsProductDescription);
                TextView productPrice= gridProductLayout.getChildAt(x).findViewById(R.id.hsProductPrice);


                productImageView.setImageResource(horizontalProductScrollModelList.get(x).getProductImage());
                productTitle.setText(horizontalProductScrollModelList.get(x).getProductTitle());
                productDescription.setText(horizontalProductScrollModelList.get(x).getProductDescription());
                productPrice.setText(horizontalProductScrollModelList.get(x).getProductPrice());
                gridProductLayout.getChildAt(x).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffacd")));

                gridProductLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });

            }



            gridViewAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewAllIntent = new Intent(itemView.getContext(),ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code",1);

                    itemView.getContext().startActivity(viewAllIntent);
                }
            });

        }
    }
}
