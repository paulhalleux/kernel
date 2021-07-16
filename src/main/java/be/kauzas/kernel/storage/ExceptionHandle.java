package be.kauzas.kernel.storage;

/**
 * Interface providing a method that
 * handle an exception.
 */
@FunctionalInterface
public interface ExceptionHandle {

    /**
     * Method that handle an exception.
     *
     * @param ex Exception to handle.
     */
    void handle(Exception ex);

}
