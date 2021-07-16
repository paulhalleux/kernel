package be.kauzas.kernel.commands;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.Environment;
import be.kauzas.kernel.commands.completion.Completable;
import be.kauzas.kernel.commands.composite.SubCommand;
import be.kauzas.kernel.service.ReflectionService;
import be.kauzas.kernel.service.RegisterResult;
import be.kauzas.kernel.utils.ConsoleColor;
import org.bukkit.command.PluginCommand;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class CommandService extends ReflectionService<AbstractCommand> {

    private final List<AbstractCommand> commands;
    private final BasePlugin plugin;
    private final Handler executor;

    /**
     * Constructor of {@link CommandService} asking for the
     * package that contains the menus.
     */
    public CommandService(String pkg, BasePlugin plugin, Handler executor) {
        super(new Reflections(pkg, plugin.getClass().getClassLoader()), plugin);
        this.commands = new ArrayList<>();
        this.plugin = plugin;
        this.executor = executor;
    }

    /**
     * Method that register an {@link AbstractCommand} to the internal
     * list of commands.
     *
     * @param abstractCommand {@link AbstractCommand} to register.
     */
    @Override
    public RegisterResult register(AbstractCommand abstractCommand) {
        if (abstractCommand.getInfos() == null) return RegisterResult.FAILED;
        CommandInfo commandInfo = abstractCommand.getInfos();
        if (!commandInfo.register()) return RegisterResult.IGNORED;
        PluginCommand command = plugin.getCommand(abstractCommand.getTrigger());

        if (!(abstractCommand instanceof SubCommand)) {
            if (command == null) return RegisterResult.FAILED;
            command.setExecutor(executor);
            if (abstractCommand instanceof Completable) {
                Completable completer = (Completable) abstractCommand;
                command.setTabCompleter(completer.getTabCompleter());
            }
        }

        commands.add(abstractCommand);
        return RegisterResult.SUCCESS;
    }

    /**
     * Get list of registered commands.
     *
     * @return List of registered {@link AbstractCommand}.
     */
    @Override
    public List<AbstractCommand> getItems() {
        return commands;
    }

    /**
     * Method that is executed when a command is registered.
     *
     * @param serviceName Command that is registered.
     * @param result      Result of the registration.
     */
    @Override
    public void onRegister(String serviceName, RegisterResult result) {
        if (result == RegisterResult.FAILED && plugin.getEnvironment() != Environment.PRODUCTION)
            System.out.println(String.format(ConsoleColor.RED + "Command %s could not be registered" + ConsoleColor.RESET, ConsoleColor.YELLOW + serviceName + ConsoleColor.RED));
    }

}
