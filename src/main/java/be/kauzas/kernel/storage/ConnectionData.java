package be.kauzas.kernel.storage;

public interface ConnectionData {

    String getDriverName();

    String getUsername();

    String getPassword();

    String getConnectionString();

}
