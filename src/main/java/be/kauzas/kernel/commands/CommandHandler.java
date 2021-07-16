package be.kauzas.kernel.commands;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.events.command.CommandExecuteEvent;
import be.kauzas.kernel.events.command.CommandPermissionDeniedEvent;
import be.kauzas.kernel.options.Restricted;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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
                return handle(abstractCommand, sender, label, args);
            }
        }
        return false;
    }

    @Override
    public boolean handle(AbstractCommand abstractCommand, CommandSender sender, String trigger, String[] args) {
        CommandInfo infos = abstractCommand.getInfos();
        CommandInfo.Sender allowed = infos.sender();
        if (allowed == CommandInfo.Sender.BOTH
                || (allowed == CommandInfo.Sender.PLAYER && sender instanceof Player)
                || (allowed == CommandInfo.Sender.CONSOLE && sender instanceof ConsoleCommandSender)) {
            if (abstractCommand instanceof Restricted) {
                Restricted restricted = (Restricted) abstractCommand;
                if (!restricted.hasPermission(sender)) {
                    restricted.onPermissionDenied(sender);
                    Bukkit.getPluginManager().callEvent(new CommandPermissionDeniedEvent(abstractCommand, sender));
                    return false;
                }
            }
            abstractCommand.execute(sender, args);
            Bukkit.getPluginManager().callEvent(new CommandExecuteEvent(abstractCommand, sender, trigger));
        }
        return false;
    }

}
