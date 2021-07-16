package be.kauzas.kernel.storage;

/**
 * Implementation of {@link ConnectionData} for MySQL database.
 */
public class MySqlConnectionData implements ConnectionData {

    private final String username;
    private final String password;
    private final String path;
    private final int port;
    private final String database;

    /**
     * Constructor of {@link MySqlConnectionData}.
     *
     * @param username Username of the database user.
     * @param password Password of the database user.
     * @param path     Address of the database.
     * @param port     Port of the database address.
     * @param database Database name.
     */
    public MySqlConnectionData(String username, String password, String path, int port, String database) {
        this.username = username;
        this.password = password;
        this.path = path;
        this.port = port;
        this.database = database;
    }

    /**
     * Get the driver name.
     *
     * @return Driver name.
     */
    @Override
    public String getDriverName() {
        return "com.mysql.cj.jdbc.Driver";
    }

    /**
     * Get the username.
     *
     * @return Username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get the password.
     *
     * @return Password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get the connection string.
     *
     * @return Connection string.
     */
    @Override
    public String getConnectionString() {
        return "jdbc:mysql://" + path + ":" + port + "/" + database + "?serverTimezone=UTC&useUnicode=true&useSSL=false";
    }

}
