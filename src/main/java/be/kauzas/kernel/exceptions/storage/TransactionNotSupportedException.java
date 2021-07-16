package be.kauzas.kernel.exceptions.storage;

import java.sql.SQLException;

public class TransactionNotSupportedException extends RuntimeException {

    public TransactionNotSupportedException(SQLException ex) {
        super("Transaction are not supported by this DBMS or this driver", ex);
    }

}
