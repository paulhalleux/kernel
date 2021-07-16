package be.kauzas.kernel.service;

import java.util.List;

/**
 * Service interface for items
 * registering.
 *
 * @param <T> Type of the handled items.
 */
public interface Service<T> {

    /**
     * Register an item.
     *
     * @param t Item to register.
     */
    RegisterResult register(T t);

    /**
     * Get list of registered items.
     *
     * @return List of items.
     */
    List<T> getItems();

    /**
     * Callback executed on service
     * registration.
     *
     * @param serviceName Name of the registered item.
     * @param result      Result of the registration.
     */
    void onRegister(String serviceName, RegisterResult result);

}
