package be.kauzas.kernel.storage;

import java.sql.Connection;

/**
 * Interface providing a method
 * that throw an exception.
 */
@FunctionalInterface
public interface ActionThrowingException {

    /**
     * Connection consumer that throws an exception.
     *
     * @param con Connection.
     * @throws Exception Thrown exception.
     */
    void execute(Connection con) throws Exception;

}
