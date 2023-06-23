package com.geekbrains.javacore.lesson4;
/*
1. Реализовать логику приложения Магазин из описания к уроку.

2. Добавить перечисление с гендерами. В класс покупателя добавить свойство «пол» со значением созданного перечисления. Добавить геттеры, сеттеры.

3. Добавить в приложение Магазин учет цены товара - в Заказ добавить поле стоимость. Добавить перечисление с размерами скидок - 0, 5, 10, 15, 20%.
Написать метод, при вызове которого на переданный тип товара назначается рандомно скидка из перечисления (меняем значение поля price)

** Товарам добавить категорию. Задать категории Стандарт и Премиум. Если на товар категории Премиум назначилась скидка более 15%,
выбросить исключение TooMuchSaleException(msg), сообщение с ошибкой вывести в консоль, цену товара не менять.
* */

public class MainApp {

    public static void main(String[] args) {

        Shop shop = new Shop();
        int i = 0;
        int discount;
        while (shop.capacity != shop.orders.length && i != shop.dataBase.length) {
            discount = shop.chooseRandomDiscount();
            try {

                shop.orders[shop.capacity] = shop.makePurchase(((Customer) shop.dataBase[i][0]).getPhone(), ((Product) shop.dataBase[i][1]).getTitle(), (int) shop.dataBase[i][2], discount);
                shop.capacity++;
                System.out.println("Order price is " + shop.orders[i].getOrderPrice() + ". Product category: " + ((Product) shop.dataBase[i][1]).getProductCategory());
            } catch (ProductException e) {
                System.out.println(e.getMessage());

            } catch (AmountException e) {
                System.out.print("It was AmountException.Changed amount to 1.\n");
                try {
                    shop.orders[shop.capacity] = shop.makePurchase(((Customer) shop.dataBase[i][0]).getPhone(), ((Product) shop.dataBase[i][1]).getTitle(), 1, discount);
                    shop.capacity++;
                    System.out.println("Order price is " + shop.orders[i].getOrderPrice() + ". Product category: " + ((Product) shop.dataBase[i][1]).getProductCategory());
                } catch (TooMuchSaleException ex){
                    System.out.println(ex.getMessage());
                    shop.orders[shop.capacity] = shop.makePurchase(((Customer) shop.dataBase[i][0]).getPhone(), ((Product) shop.dataBase[i][1]).getTitle(), 1, 0);
                    shop.capacity++;
                    System.out.println("Order price is " + shop.orders[i].getOrderPrice() + ". Product category: " + ((Product) shop.dataBase[i][1]).getProductCategory());
                }

            } catch (TooMuchSaleException e) {
                System.out.println(e.getMessage());
                shop.orders[shop.capacity++] = shop.makePurchase(((Customer) shop.dataBase[i][0]).getPhone(), ((Product) shop.dataBase[i][1]).getTitle(), (int) shop.dataBase[i][2], 0);
                System.out.println("Order price is " + shop.orders[i].getOrderPrice() + ". Product category: " + ((Product) shop.dataBase[i][1]).getProductCategory());
            } catch (CustomerException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Total orders made: " + shop.capacity);
                System.out.println("------------------------------------------------------------------------------------");
            }
            ++i;
        }
    }
}
