package com.newproject.omega;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {


    public HomeFrag() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;

    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.HORIZONTAL);

        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<CategoryModel>();

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);


        firebaseFirestore = FirebaseFirestore.getInstance();

        /*categoryModelList.add(new CategoryModel("link ", "Home"));
        categoryModelList.add(new CategoryModel("link ", "Electronics"));
        categoryModelList.add(new CategoryModel("link ", "Appliances"));
        categoryModelList.add(new CategoryModel("link ", "furniture"));
        categoryModelList.add(new CategoryModel("link ", "Fashion"));
        categoryModelList.add(new CategoryModel("link ", "Toys"));
        categoryModelList.add(new CategoryModel("link ", "Sports"));
        categoryModelList.add(new CategoryModel("link ", "Wall Arts"));
        categoryModelList.add(new CategoryModel("link ", "Books"));
        categoryModelList.add(new CategoryModel("link ", "Shoes"));
        categoryModelList.add(new CategoryModel("link ", "T- shirts"));*/
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),
                                        documentSnapshot.get("categoryName").toString()));
                            }

                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                        }


                    }
                });




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

        testing = view.findViewById(R.id.homePageRecyclerView);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

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
        testing.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();


        ///////////////////////

        return view;
    }


}
