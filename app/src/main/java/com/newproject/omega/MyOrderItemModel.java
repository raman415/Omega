package com.newproject.omega;

public class MyOrderItemModel {
    private int orderProductImage;

    private String orderProductTitle;
    private String orderDeliveryStatus;
    private int rating;

    public int getOrderProductImage() {
        return orderProductImage;
    }

    public void setOrderProductImage(int orderProductImage) {
        this.orderProductImage = orderProductImage;
    }

    public String getOrderProductTitle() {
        return orderProductTitle;
    }

    public void setOrderProductTitle(String orderProductTitle) {
        this.orderProductTitle = orderProductTitle;
    }

    public String getOrderDeliveryStatus() {
        return orderDeliveryStatus;
    }

    public void setOrderDeliveryStatus(String orderDeliveryStatus) {
        this.orderDeliveryStatus = orderDeliveryStatus;
    }

    public MyOrderItemModel(int orderProductImage, String orderProductTitle, String orderDeliveryStatus,int rating) {
        this.orderProductImage = orderProductImage;
        this.orderProductTitle = orderProductTitle;
        this.orderDeliveryStatus = orderDeliveryStatus;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
