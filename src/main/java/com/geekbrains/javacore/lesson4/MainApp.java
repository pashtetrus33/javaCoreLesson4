package com.geekbrains.javacore.lesson4;
/*
* ����� ��������� ��������-��������.
1. �������� ������ ���������� (���, �������, �������), ����� (��������, ����) � ����� (������ ����������, ������ �����, ������������� ����������).
2. ������� ������ ����������� (���������������� 2 ��������), ������ ������� (���������������� 5 ���������) � ������ ������� (������ �� 5 ���������).
3. ������� ����������� ����� ���������� ������� �� ���������� �����������, ���������������� ����� ������� ������. ����� ������ ������� ������ ������.
4. ���� � ����� ������� �������������� ���������� � ����� ������ ��������� ���������� CustomerException, ���� ������� �������������� �����, �����
������ ��������� ���������� ProductException, ���� ���� �������� ������������� ��� ������� ������ �������� ���������� (��������, 100),
*  ����� ������ ��������� ���������� AmountException.

5. ������� ����� ���������� ������� ��������� ��� ����� �������, ����� ��������� ������ ������� ������������� ����������. ���������� ���������� ���-
������ ������� (� �������� �������): � ���� ��� ������� �������� ����� � ������� � ������� ��������� �� ������, �� ��������� ������ �������;

� ���� ���� �������� �������� ���������� � ������ ����� � ���������� 1;
� ���� ��� ������� �������� ������������ � ��������� ������ ����������
� �����������.

6. ������� � ������� �������� ���������� ����������� ������� ����� ���������� ��������� ���� ����������.
*
* */

public class MainApp {

    static Customer[] customers = null;
    static Product[] products = null;

    public static void main(String[] args) {


        customers = new Customer[]{
                new Customer("Ivan", "Semenov", 25, "+79604564444"),
                new Customer("Petr", "Kolosov", 32, "+796045654232")
        };

        products = new Product[]{
                new Product("Milk", 100.5f),
                new Product("Bread", 70.8f),
                new Product("Meat", 1000),
                new Product("Chocolate", 225.6f),
                new Product("Tea", 300),

        };

        Order[] orders = new Order[5];

        String[] phones = {"+79604564444", "+796045654232", "+734556555", "+796045654232", "+79604564444"};
        String[] productTitles = {"Milk", "Water", "Meat", "Tea", "Chocolate"};
        int[] amounts = {4, 5, 101, 0, -1};

        int count =0;
        for (int i = 0; i < 5; i++) {
            try {
                orders[i] = makePurchase(phones[i], productTitles[i], amounts[i]);
                count++;
            } catch (ProductException e) {
                System.out.println(e.getMessage());
            } catch (AmountException e) {
                orders[i] = makePurchase(phones[i], productTitles[i], 1);
                System.out.println("�������� �� 1");
                count++;

            } catch (CustomerException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("���������� ����������� �������: " + count);
    }

    public static Order makePurchase(String phone, String title, int amount) throws CustomerException, ProductException, AmountException {
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
            throw new ProductException("Product not found");
        }

        if ((amount > 100) || (amount < 1)) {
            throw new AmountException("Amount is not correct");
        }
        return new Order(customer, product, amount);
    }
}
