package be.kauzas.kernel.exceptions.storage;

import java.sql.SQLException;

public class UnableToRollbackException extends RuntimeException {

    public UnableToRollbackException(SQLException ex) {
        super(ex);
    }

}
