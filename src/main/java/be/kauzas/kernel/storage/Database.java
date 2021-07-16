package be.kauzas.kernel.storage;

import java.sql.Connection;

public interface Database {

    Connection getConnection();

    boolean isOnline();

    void setup();

}
