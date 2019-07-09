package com.newproject.omega;

public class CategoryModel {
    /* Over here we have to make Model of our recyclerView... of category items
       1. we are going to access our icons from database. so for that make kind of link to firebase
       that link would be load to image view

     */

    private String categoryIconlink;
    private String categoryName;

    public void setCategoryIconlink(String categoryIconlink) {
        this.categoryIconlink = categoryIconlink;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconlink() {
        return categoryIconlink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryModel(String categoryIconlink, String categoryName) {
        this.categoryIconlink = categoryIconlink;
        this.categoryName = categoryName;
    }
}
