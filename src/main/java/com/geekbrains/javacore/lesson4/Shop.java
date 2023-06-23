package com.geekbrains.javacore.lesson4;

import java.util.Random;

public class Shop {


    Product[] products = null;
    Customer[] customers = null;
    Order[] orders = null;
    Object[][] dataBase = null;

    int capacity = 0;


    public Shop() {
        products = new Product[]

                {
                        new Product("Milk", 100.5f, ProductCategory.PREMIUM),
                        new Product("Bread", 70.8f, ProductCategory.STANDARD),
                        new Product("Meat", 1000, ProductCategory.PREMIUM),
                        new Product("Chocolate", 225.6f, ProductCategory.PREMIUM),
                        new Product("Tea", 300, ProductCategory.STANDARD)

                };

        customers = new Customer[]{
                new Customer("Ivan", "Semenov", 25, "+79604564444", Sex.MALE),
                new Customer("Elena", "Kolosova", 32, "+796045654232", Sex.FEMALE)
        };

        dataBase = new Object[][]{
                {customers[0], products[0], 1},
                {customers[1], products[1], 2},
                {customers[0], products[2], 101},
                {customers[1], new Product("Orange", 20, ProductCategory.PREMIUM), 5},
                {new Customer("Robert", "DeNitro", 44, "+1-3445555444", Sex.MALE), products[3], 2}};

        int ORDERSIZE = 5;
        orders = new Order[ORDERSIZE];
    }


    public int chooseRandomDiscount() {
        Random random = new Random();
        Discount[] discounts = Discount.values();
        int result = discounts[random.nextInt(5)].getValue();
        System.out.println("Random discount is: " + result + "%");
        return result;
    }

    public Order makePurchase(String phone, String title, int amount, int discount) throws CustomerException, ProductException, AmountException, TooMuchSaleException {


        Customer customer = null;
        Product product = null;
        for (Customer c : customers) {
            if (c.getPhone().equals(phone)) {
                customer = c;
            }
        }

        for (Product p : products) {
            if (p.getTitle().equals(title)) {
                product = p;
            }
        }
        if (customer == null) {
            throw new CustomerException("Customer not found");
        }

        if (product == null) {
            throw new ProductException("Product not found: " + title);
        }


        if ((amount > 100) || (amount < 1)) {
            throw new AmountException("Amount is not correct");
        }
        return new Order(customer, product, amount, discount);
    }
}
