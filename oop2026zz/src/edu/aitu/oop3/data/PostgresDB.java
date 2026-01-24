package edu.aitu.oop3.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private String url= "aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require\"";
    private String user= "postgres.gmnhoitoccbzuveofkns";
    private String password="askarova0910";

    public PostgresDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}