package be.kauzas.kernel.storage;

import java.sql.Connection;

@FunctionalInterface
public interface ActionThrowingException {
    void execute(Connection con) throws Exception;
}
