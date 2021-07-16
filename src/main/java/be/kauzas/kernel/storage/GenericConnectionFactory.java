package be.kauzas.kernel.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericConnectionFactory implements ConnectionFactory {

    @Override
    public Connection from(ConnectionData connectionData) {
        Connection result = null;

        try {
            Class.forName(connectionData.getDriverName());
            result = DriverManager.getConnection(connectionData.getConnectionString(), connectionData.getUsername(), connectionData.getPassword());
        } catch (SQLException throwable) {
            System.out.printf("Database connection failed. (%s)%n", connectionData.getClass().getSimpleName());
        } catch (ClassNotFoundException exception) {
            System.out.printf("Driver '%s' could not be found.%n", connectionData.getDriverName());
        }

        return result;
    }

}