package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDb {
    public static void main(String[] args){
        String JDBC_URL = "jdbc:postgresql://localhost:5432/sample?useSSL=false&amp;serverTimezone=UTC";
        String user = "postgres";
        String password = "rolito";

        try {
            System.out.println("Connecting to database: " + JDBC_URL);

            Connection myConn =
                    DriverManager.getConnection(JDBC_URL, user, password);

            System.out.println("Connection successful!!!");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
