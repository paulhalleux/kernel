package be.kauzas.kernel.storage;

import be.kauzas.kernel.exceptions.storage.TransactionNotSupportedException;
import be.kauzas.kernel.exceptions.storage.UnableToRollbackException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    private final Connection con;
    private ExceptionHandle rollbackAction;
    private ActionThrowingException commitAction;

    public static Transaction from(Connection con) {
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new TransactionNotSupportedException(ex);
        }
        return new Transaction(con);
    }

    public Transaction(Connection con) {
        this.con = con;
    }

    public Transaction commit(ActionThrowingException sequence) {
        this.commitAction = sequence;
        return this;
    }

    public Transaction onRollback(ExceptionHandle sequence) {
        this.rollbackAction = sequence;
        return this;
    }

    public void execute() throws UnableToRollbackException {
        try {
            commitAction.execute(con);
            con.commit();
        } catch (Exception ex) {
            try {
                con.rollback();
                rollbackAction.handle(ex);
            } catch (SQLException e) {
                throw new UnableToRollbackException(e);
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new TransactionNotSupportedException(ex);
            }
        }
    }
}

