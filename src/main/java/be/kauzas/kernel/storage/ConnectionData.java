package be.kauzas.kernel.storage;

/**
 * Interface providing methods
 * for database connection.
 */
public interface ConnectionData {

    /**
     * Get the name of the driver to load.
     *
     * @return Name of the driver.
     */
    String getDriverName();

    /**
     * Get the username.
     *
     * @return Username for connection.
     */
    String getUsername();

    /**
     * Get the password.
     *
     * @return Password for connection.
     */
    String getPassword();

    /**
     * Get the connection string.
     *
     * @return Connection string.
     */
    String getConnectionString();

}
