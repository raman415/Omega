package com.newproject.omega;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Deal of the Day");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.viewAll_recyclerView);
        gridView = findViewById(R.id.grid_View);


        int layoutCode = getIntent().getIntExtra("layout_code", -1);

        if (layoutCode == 0) {


            recyclerView.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.s8phone, "Samsung S9+", 1, "3", 149, "$ 1300/-", "$ 1700/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.s8phone, "Samsung S9+", 0, "3", 149, "$ 1300/-", "$ 1700/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.s8phone, "Samsung S9+", 2, "3", 149, "$ 1300/-", "$ 1700/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.s8phone, "Samsung S9+", 0, "3", 149, "$ 1300/-", "$ 1700/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.s8phone, "Samsung S9+", 5, "3", 149, "$ 1300/-", "$ 1700/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.s8phone, "Samsung S9+", 1, "3", 149, "$ 1300/-", "$ 1700/-", "Cash on delivery"));


            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if (layoutCode == 1) {


            gridView.setVisibility(View.VISIBLE);


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


            GridProductLayoutAdapter gridProductLayoutAdapter =
                    new GridProductLayoutAdapter(horizontalProductScrollModelList);

            gridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
