package com.geekbrains.javacore.lesson4;

public class Product {

    private String title;
    private float price;
    private ProductCategory productCategory;

    public Product(String title, float price, ProductCategory productCategory) {
        this.title = title;
        this.price = price;
        this.productCategory = productCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
