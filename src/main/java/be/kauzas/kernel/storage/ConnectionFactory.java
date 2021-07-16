package be.kauzas.kernel.storage;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    Connection from(ConnectionData connectionData) throws SQLException;

}
