package com.company;

import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Мой кошелек");
        System.out.println("1 Внести деньги в кошелек");
        System.out.println("2 Внести покупку");
        System.out.println("3 Отобразить баланс");
        System.out.println("4 Показать все покупки");
        System.out.println("5 Выход из программы");
        System.out.println(" ");
        System.out.print("Введите одну из цифр от 1 до 5 и нажмите на Enter ");
        Scanner console = new Scanner(System.in);
        int number = console.nextInt();
        switch (number) {
            case 1:
                System.out.print("Введите сумму для внесения денег в кошелек: ");
                Scanner addMoney = new Scanner(System.in);
                int money = addMoney.nextInt();
                DatabaseHandler dbHandler = new DatabaseHandler(); //объект класса DatabaseHandler
                dbHandler.enterDataIn(addMoney);
            case 2:
                break;
            case 3:
            break;
            case 4:
                break;
            case 5:
                //System.exit(0);
                break;
            default:
                System.out.println("Вы ввели не существующий номер из меню");
                break;
        }
    }
}
