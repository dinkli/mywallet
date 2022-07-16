package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void PrinttAllPurchases() {
        // выбрать все покупки
        String select = "SELECT * FROM " + Const.PURCHASES_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                System.out.println(resultSet.getString(Const.PURCHASES_NAMEPRODACT) + " - " + resultSet.getInt(Const.PURCHASES_PRICE));
                ++counter;
            }
            if (counter == 0)
                System.out.println("Список покупок пуст!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddPurchase(String name, int price) {
        // внести покупку
        String insert = "INSERT INTO " + Const.PURCHASES_TABLE + " (" + Const.PURCHASES_NAMEPRODACT + ", "
                + Const.PURCHASES_PRICE + ") VALUES(?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setInt(2, price);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int GetBalance() {
        // узнать текущий баланс
        String select = "SELECT " + Const.TBALANCE_BALANCE + " FROM " + Const.TBALANCE_TABLE;
        int currBalance = 0;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next())
                currBalance = resultSet.getInt(Const.TBALANCE_BALANCE);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currBalance;
    }

    public void IncreaseBalance(int addMoney) {
        // обновить текущий баланс
        String update = "UPDATE " + Const.TBALANCE_TABLE + " SET " + Const.TBALANCE_BALANCE + " = ? LIMIT 1";
        try {
            PreparedStatement prStUp = getDbConnection().prepareStatement(update);
            prStUp.setInt(1, GetBalance() + addMoney);
            prStUp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
