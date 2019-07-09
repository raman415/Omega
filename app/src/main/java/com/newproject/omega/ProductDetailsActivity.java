package com.newproject.omega;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.newproject.omega.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;


    private Button coupenRedeembtn;


    public static TextView coupenTitle;
    public static TextView coupenExpiryDate;
    public static TextView coupenbody;

    private static RecyclerView coupenRecyclerView;
    private static LinearLayout selectedCoupen;


    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    private Button buyNowBtn;

    /////// Rating layout
    LinearLayout rateNowContainer;
    /////// Rating layout

    private FloatingActionButton addToWishListBtn;
    private static Boolean ALREADY_ADDED_TO_WISHLIST = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productImagesViewPager = findViewById(R.id.productImagesViewPager);
        viewPagerIndicator = findViewById(R.id.viewPagerIndicator);
        addToWishListBtn = (FloatingActionButton) findViewById(R.id.add_to_wishlist_button);
        productDetailsViewPager = findViewById(R.id.product_detailsViewPager);
        productDetailsTabLayout = findViewById(R.id.product_details_tabLayout);
        buyNowBtn = findViewById(R.id.buy_now_btn);

        ///// Coupen Redeem Code.....
       /* coupenRedeembtn = findViewById(R.id.btn_coupon_redemption);

        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_redeem_dialog);
        checkCoupenPriceDialog.setCancelable(true);
        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupen_toggle_recycler_view);
        coupenRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerView);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupens_container);

        coupenTitle = checkCoupenPriceDialog.findViewById(R.id.rewards_coupen_title);
        coupenExpiryDate = checkCoupenPriceDialog.findViewById(R.id.rewards_coupen_validity);
        coupenbody = checkCoupenPriceDialog.findViewById(R.id.rewards_coupen_body);


        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.coupen_original_price);
        TextView discountedPrice = checkCoupenPriceDialog.findViewById(R.id.coupen_discounted_price);


        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coupenRecyclerView.setLayoutManager(layoutManager);


        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Cashback", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Cashback", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd,june 2016", "Get 20% cash back"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till 2nd,june 2016", "Get 20% cash back"));


        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList, true);
        coupenRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();


        toggleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRecyclerView();

            }
        });


        coupenRedeembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCoupenPriceDialog.show();
            }
        });*/


        //// Coupen Redeem code.....

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.transiphonex);
        productImages.add(R.drawable.s8phone);
        productImages.add(R.drawable.banner);
        productImages.add(R.drawable.ic_menu_share);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        // we have to connect both of these
        // it really new concept understand

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);

        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setImageTintList(ColorStateList
                            .valueOf(Color.parseColor("#9B9696")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setImageTintList(getResources().getColorStateList(R.color.btnColor));
                }
            }
        });

        // Syncronizing ViewPager and Tablayout or simply attaching both of them with each other.
        productDetailsViewPager.
                setAdapter(new ProductDetailsAdapter(getSupportFragmentManager()
                        , productDetailsTabLayout.getTabCount()));

        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ////////// Rating Layout

        rateNowContainer = findViewById(R.id.rate_now_container);

        // first we are going to put listener on all stars
        // Below for loop will going to attach onClickListener
        // more specifically it apply click-listener on each child one by one

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


        ////////// Rating Layout

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(ProductDetailsActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });


    }

    public static void showDialogRecyclerView(){
        if (coupenRecyclerView.getVisibility()==View.GONE){
            coupenRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        }else{
            coupenRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // todo: search
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {
            return true;
        } else if (id == R.id.main_cart_icon) {
            // todo: cart
            Intent cartIntent = new Intent(ProductDetailsActivity.this, MainActivity.class);
            showCart = true;
            startActivity(cartIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
