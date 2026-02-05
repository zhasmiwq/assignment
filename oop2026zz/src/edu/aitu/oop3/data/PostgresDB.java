package edu.aitu.oop3.data;

import edu.aitu.oop3.db.DataConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDB implements IDB {

    private final String url;
    private final String user;
    private final String password;

    public PostgresDB() {
        Properties p = DataConnection.loadDbProps();
        this.url = p.getProperty("DB_URL");
        this.user = p.getProperty("DB_USER");
        this.password = p.getProperty("DB_PASSWORD");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
