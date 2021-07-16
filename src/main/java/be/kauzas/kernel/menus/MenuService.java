package be.kauzas.kernel.menus;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.Environment;
import be.kauzas.kernel.exceptions.MenuAlreadyRegisteredException;
import be.kauzas.kernel.listeners.MenuClickListener;
import be.kauzas.kernel.service.ReflectionService;
import be.kauzas.kernel.service.RegisterResult;
import be.kauzas.kernel.utils.ConsoleColor;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that is used to register {@link AbstractMenu}
 * to allow them to be handled by the inventory click event of
 * the {@link MenuClickListener}.
 */
public class MenuService extends ReflectionService<AbstractMenu> {

    private final BasePlugin plugin;
    private final List<AbstractMenu> menus;

    /**
     * Constructor of {@link MenuService} asking for the
     * package that contains the menus.
     */
    public MenuService(String pkg, BasePlugin plugin) {
        super(new Reflections(pkg, plugin.getClass().getClassLoader()), plugin);
        this.plugin = plugin;
        this.menus = new ArrayList<>();
    }

    /**
     * Method that register an {@link AbstractMenu} to the internal
     * list of menus that will be used in the inventory click event.
     *
     * @param menu {@link AbstractMenu} to register.
     * @throws MenuAlreadyRegisteredException when a menu with the same
     *                                        unique id is already registered.
     */
    @Override
    public RegisterResult register(AbstractMenu menu) {
        if (isRegistered(menu)) {
            throw new MenuAlreadyRegisteredException(menu);
        }
        this.menus.add(menu);
        return RegisterResult.SUCCESS;
    }

    /**
     * Get list of registered menus.
     *
     * @return List of registered {@link AbstractMenu}.
     */
    @Override
    public List<AbstractMenu> getItems() {
        return menus;
    }

    /**
     * Method that is executed when a menu is registered.
     *
     * @param serviceName Menu that is registered.
     * @param result      Result of the registration.
     */
    @Override
    public void onRegister(String serviceName, RegisterResult result) {
        if (result == RegisterResult.FAILED && plugin.getEnvironment() != Environment.PRODUCTION)
            System.out.println(String.format(ConsoleColor.RED + "Menu %s could not be registered" + ConsoleColor.RESET, ConsoleColor.YELLOW + serviceName + ConsoleColor.RED));
    }

    /**
     * Check if an {@link AbstractMenu} has already been registered.
     *
     * @param menu {@link AbstractMenu} to check if he is already registered.
     * @return true if the menu is already registered, otherwise false.
     */
    private boolean isRegistered(AbstractMenu menu) {
        return menus.stream().anyMatch(m -> m.getUniqueId().equalsIgnoreCase(menu.getUniqueId()));
    }

}
