package study.config;

import study.properties.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
    private static final Database INSTANCE = new Database();
    private Connection Connection;

    private Database() {
        //Initialize connection with DB
        try {
            String connectionUrl = PropertyReader.getConnectionUrlForPostgres();
            String username = PropertyReader.getUserForPostgres();
            String password = PropertyReader.getPasswordForPostgres();
            this.Connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Create connection exception");
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getPostgresConnection() {
        return Connection;
    }

    public int executeUpdate(String query) {
        try(Statement statement = INSTANCE.getPostgresConnection().createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            Connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
