package edu.aitu.oop3.data;

import edu.aitu.oop3.db.DataConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() throws SQLException {
        return DataConnection.getConnection();
    }
}
