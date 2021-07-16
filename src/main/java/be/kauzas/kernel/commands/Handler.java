package be.kauzas.kernel.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler interface for {@link AbstractCommand} execution handling.
 */
public interface Handler extends CommandExecutor {

    /**
     * Method that handle command execution.
     *
     * @param abstractCommand Executed command.
     * @param sender          Sender of the command.
     * @param args            Arguments of the command.
     * @return Execution state.
     */
    boolean handle(AbstractCommand abstractCommand, CommandSender sender, String[] args);

}
