package com.newproject.omega;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryActivityRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryActivityRecyclerView = findViewById(R.id.categoryActivityRecyclerView);


        ///////////// Banner Slider


        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.newwarningicon, "#077ae4"));
        sliderModelList.add(new SliderModel(R.drawable.banner, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.round_youtube_searched_for_white_48dp, "#077ae4"));

        sliderModelList.add(new SliderModel(R.mipmap.round_youtube_searched_for_white_48dp, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.cross, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.red_email, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.twotone_home_black_48dp, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.baseline_local_grocery_store_black_48dp, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.alertwarning, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.baseline_add_circle_outline_white_48dp, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.newwarningicon, "#077ae4"));
        sliderModelList.add(new SliderModel(R.drawable.banner, "#077ae4"));
        //after blinking this last banner is seen

        sliderModelList.add(new SliderModel(R.drawable.banner, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.round_youtube_searched_for_white_48dp, "#077ae4"));
        sliderModelList.add(new SliderModel(R.mipmap.cross, "#077ae4"));


        ///////////// Banner Slider


        /////// HorizontalScroll Product layout

        // Creating our adapter over here. after that layoutManager
        List<HorizontalProductScrollModel> horizontalProductScrollModelList =
                new ArrayList<>();

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.s8phone,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));
        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.ic_menu_camera,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));
        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.banner,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.puzzledboy,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.common_full_open_on_phone,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.common_google_signin_btn_icon_dark_focused,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.s8phone,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.ic_menu_gallery,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));

        horizontalProductScrollModelList.add(
                new HorizontalProductScrollModel(R.drawable.common_google_signin_btn_text_light_normal,
                        "SamSung S8", "Oled-UltraHD Display", "$1200"));


        // now we have to pass list in the adapter


        /////// HorizontalScroll Product layout

        //////// GridView Product Layout


        ////////////////////////
        /* Below RecyclerView is very special kind of recyclerView
        it allow us to add as many other multiples recyclerViews in any order
        * */


        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryActivityRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();

        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.common_google_signin_btn_icon_dark_focused,
                "#ff0000"));
        homePageModelList.add(new HomePageModel(1, R.drawable.banner, "#000000"));
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the Day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the Day", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(1, R.drawable.puzzledboy, "#ffff00"));

        homePageModelList.add(new HomePageModel(1, R.drawable.s8phone, "#ff0000"));
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.puzzledboy, "#be557c"));

        HomePageAdapter homePageAdapter = new HomePageAdapter(homePageModelList);
        categoryActivityRecyclerView.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();


        ///////////////////////


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {
            // todo: search
            return true;
        }
        else if (id == android.R.id.home){
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
