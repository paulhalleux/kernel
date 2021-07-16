package be.kauzas.kernel.storage;

public class MySqlConnectionData implements ConnectionData {

    private final String username;
    private final String password;
    private final String path;
    private final int port;
    private final String database;

    public MySqlConnectionData(String username, String password, String path, int port, String database) {
        this.username = username;
        this.password = password;
        this.path = path;
        this.port = port;
        this.database = database;
    }

    @Override
    public String getDriverName() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConnectionString() {
        return "jdbc:mysql://" + path + ":" + port + "/" + database + "?serverTimezone=UTC&useUnicode=true&useSSL=false";
    }

}
