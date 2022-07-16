package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        DatabaseHandler dbHandler = new DatabaseHandler(); // объект класса DatabaseHandler
        do {
        System.out.println("Мой кошелек");
        System.out.println("1 Внести деньги в кошелек");
        System.out.println("2 Внести покупку");
        System.out.println("3 Отобразить баланс");
        System.out.println("4 Показать все покупки");
        System.out.println("5 Выход из программы");
        System.out.println(" ");
        System.out.print("Введите одну из цифр от 1 до 5 и нажмите на Enter ");
        int number = 0;
            try {
                number = sc.nextInt();
                if (number <= 0 || number > 5)
                    throw new Exception();
            } catch (Exception e) {
                System.err.println("Ошибка ввода! Пункт меню должен числом быть от 1 до 5!\n");
                sc.nextLine();
                continue;
            }
            switch (number) {
                case 1: {
                    System.out.print("Введите сумму для внесения денег в кошелек: ");
                    int addMoney = 0;
                    try {
                        addMoney = sc.nextInt();
                        if (addMoney <= 0)
                            throw new Exception();
                        dbHandler.IncreaseBalance(addMoney);
                        System.out.println("Баланс успешно пополнен!\n");
                    } catch (Exception e) {
                        System.err.println("Ошибка ввода! Сумма должна быть только целым положительным числом!\n");
                        sc.nextLine();
                        continue;
                    }
                    break;
                }
                case 2: {
                    sc.nextLine();
                    System.out.print("Введите название покупки: ");
                    String name = sc.nextLine();
                    System.out.print("Введите стоимость покупки: ");
                    int price = 0;
                    try {
                        price = sc.nextInt();
                        if (price <= 0)
                            throw new Exception();
                        dbHandler.AddPurchase(name, price);
                        System.out.println("Покупка успешно внесена!\n");
                    } catch (Exception e) {
                        System.err.println("Ошибка ввода! Стоимость должна быть только целым положительным числом!\n");
                        sc.nextLine();
                        continue;
                    }
                    break;
                }
                case 3:
                    System.out.println("\nТекущий баланс = " + dbHandler.GetBalance());
                    break;
                case 4:{
                    System.out.println("Все покупки:");
                    dbHandler.PrinttAllPurchases();
                    System.out.println();
                    break;
                }
                case 5:
                    flag = false;
                    break;
            }
        } while (flag);
        sc.close();
    }
}
