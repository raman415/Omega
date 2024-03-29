package com.newproject.omega;

import java.util.List;

public class HomePageModel {
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;
    //////////// Strip AD
    private int type;
    private int resourse;
    private String backgroundColor;

    public HomePageModel(int type, int resourse, String backgroundColor) {
        this.type = type;
        this.resourse = resourse;
        this.backgroundColor = backgroundColor;

    }
    //////////// Strip AD

    //////////////////// Banner slider code
    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    private List<SliderModel> sliderModelList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    //////////////////// Banner slider code

    //////////// Strip AD
    public int getResourse() {
        return resourse;
    }

    public void setResourse(int resourse) {
        this.resourse = resourse;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    //////////// Strip AD

    /////////// HORIZONTAL PRODUCT LAYOUT && Grid Product Layout
    private String title;
    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HomePageModel( int type,String title, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.title = title;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }
    /////////// HORIZONTAL PRODUCT LAYOUT && Grid Product Layout


}
