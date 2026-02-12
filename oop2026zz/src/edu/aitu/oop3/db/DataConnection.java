package edu.aitu.oop3.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataConnection {
    private static final String URL =
            "jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.gmnhoitoccbzuveofkns";
    private static final String PASSWORD = loadPassword();

    public static String loadPassword() {
        Properties props = new Properties();
        try (InputStream input = DataConnection.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found. Put it into src/resources/");
            }

            props.load(input);
            String value = props.getProperty("DB_PASSWORD");
            if (value == null || value.isBlank()) {
                throw new RuntimeException("DB_PASSWORD is not set in config.properties");
            }
            return value;

        } catch (Exception e) {
            throw new RuntimeException("Cannot load DB_PASSWORD from config.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
