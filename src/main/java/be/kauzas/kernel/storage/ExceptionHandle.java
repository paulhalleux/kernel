package be.kauzas.kernel.storage;

@FunctionalInterface
public interface ExceptionHandle {
    void handle(Exception ex);
}
