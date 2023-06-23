package com.geekbrains.javacore.lesson4;


public class Order {

    private Customer customer;
    private Product product;
    private int amount;
    private final float orderPrice;

    public Order(Customer customer, Product product, int amount, int discount) throws TooMuchSaleException {
        this.customer = customer;
        this.product = product;
        this.amount = amount;

        if (product.getProductCategory() == ProductCategory.PREMIUM && discount > 15) {
            throw new TooMuchSaleException("Discount is too much!");
        } else {
            float beforeDiscount = product.getPrice() * amount;
            this.orderPrice = Math.round((beforeDiscount - beforeDiscount * discount / 100) * 100.0) / 100.0f;

        }
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getOrderPrice() {
        return orderPrice;
    }
}
