package be.kauzas.kernel.storage;

import java.sql.Connection;

/**
 * Interface for a Database implementation
 * that provide base methods.
 */
public interface Database {

    /**
     * Get the connection to the database.
     *
     * @return Connection to the database.
     */
    Connection getConnection();

    /**
     * Check if the database is online and if
     * we can connect to.
     *
     * @return true if database id online.
     */
    boolean isOnline();

    /**
     * Setup the database.
     */
    void setup();

}
