package be.kauzas.kernel.listeners;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.Environment;
import be.kauzas.kernel.service.ReflectionService;
import be.kauzas.kernel.service.RegisterResult;
import be.kauzas.kernel.utils.ConsoleColor;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener service that handle
 * automatic listener registration.
 */
public class ListenerService extends ReflectionService<Listener> {

    private final List<Listener> items;
    private final BasePlugin plugin;
    private final PluginManager manager;

    /**
     * Constructor of {@link ListenerService} asking for the
     * name of the package that contains the listeners.
     */
    public ListenerService(String pkg, BasePlugin plugin) {
        super(new Reflections(pkg, plugin.getClass().getClassLoader()), plugin);
        this.items = new ArrayList<>();
        this.plugin = plugin;
        this.manager = Bukkit.getPluginManager();
    }

    /**
     * Method that register a {@link Listener}.
     *
     * @param listener {@link Listener} to register.
     */
    @Override
    public RegisterResult register(Listener listener) {
        manager.registerEvents(listener, plugin);
        items.add(listener);
        return RegisterResult.SUCCESS;
    }

    /**
     * Get list of registered listeners.
     *
     * @return List of registered {@link Listener}.
     */
    @Override
    public List<Listener> getItems() {
        return items;
    }

    /**
     * Method that is executed when a listener is registered.
     *
     * @param serviceName Registered listener.
     * @param result      Result of the registration.
     */
    @Override
    public void onRegister(String serviceName, RegisterResult result) {
        if (result == RegisterResult.FAILED && plugin.getEnvironment() != Environment.PRODUCTION)
            System.out.println(String.format(ConsoleColor.RED + "Listener %s could not be registered" + ConsoleColor.RESET, ConsoleColor.YELLOW + serviceName + ConsoleColor.RED));
    }

}
