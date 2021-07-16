package be.kauzas.kernel.storage;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for a connection factory.
 */
public interface ConnectionFactory {

    /**
     * {@link Connection} builder method.
     *
     * @param connectionData Connection data to create connection.
     * @return Created connection.
     * @throws SQLException if connection cannot be established.
     */
    Connection from(ConnectionData connectionData) throws SQLException;

}
