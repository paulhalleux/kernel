package be.kauzas.kernel;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.commands.Handler;
import be.kauzas.kernel.menus.AbstractMenu;
import be.kauzas.kernel.service.Service;
import org.bukkit.event.Listener;

/**
 * Interface that contains base plugin
 * methods.
 */
public interface KernelPlugin {

    /**
     * Get the service that handle {@link AbstractMenu} of the plugin.
     *
     * @return Menu service.
     */
    Service<AbstractMenu> getMenuService();

    /**
     * Get the service that handle {@link Listener} of the plugin.
     *
     * @return Listener service.
     */
    Service<Listener> getListenerService();

    /**
     * Get the service that handle {@link AbstractCommand} of the plugin.
     *
     * @return CommandInfo service.
     */
    Service<AbstractCommand> getCommandService();

    /**
     * Get the current production {@link Environment}.
     *
     * @return Current environment.
     */
    Environment getEnvironment();

    /**
     * Get the handler for the commands.
     *
     * @return Command handler.
     */
    Handler getCommandHandler();

}
