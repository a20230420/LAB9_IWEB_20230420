package org.example.lab09.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {
    public Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }


        String user = "root";
        String password = "12345678";
        String url = "jdbc:mysql://localhost:3306/lab9_productos?serverTimezone=America/Lima";

        return DriverManager.getConnection(url, user, password);
    }
}
