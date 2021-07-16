package be.kauzas.kernel.commands;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.options.Restricted;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.List;

/**
 * {@link CommandExecutor} implementation that handle
 * {@link AbstractCommand} execution.
 */
public class CommandHandler implements Handler {

    private final BasePlugin plugin;

    /**
     * Constructor of {@link CommandHandler} asking for the plugin
     * to register command in and that contains the command service.
     *
     * @param plugin Plugin that contains the command service.
     */
    public CommandHandler(BasePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<AbstractCommand> commands = plugin.getCommandService().getItems();
        for (AbstractCommand abstractCommand : commands) {
            if (abstractCommand.hasTrigger(command.getName())) {
                return handle(abstractCommand, sender, args);
            }
        }
        return false;
    }

    @Override
    public boolean handle(AbstractCommand abstractCommand, CommandSender sender, String[] args) {
        CommandInfo infos = abstractCommand.getInfos();
        if (infos.sender().equals(ConsoleCommandSender.class) || infos.sender().isAssignableFrom(sender.getClass())) {
            if (abstractCommand instanceof Restricted) {
                Restricted restricted = (Restricted) abstractCommand;
                if (!restricted.hasPermission(sender)) {
                    restricted.onPermissionDenied(sender);
                    return false;
                }
            }
            abstractCommand.execute(sender, args);
        }
        return false;
    }

}
