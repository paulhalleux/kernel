package be.kauzas.kernel.commands;

import org.bukkit.command.CommandSender;

/**
 * Functional interface that represent the
 * command execution method.
 */
@FunctionalInterface
public interface RunnableCommand {

    /**
     * CommandInfo execution method.
     *
     * @param sender {@link CommandSender} of the command.
     * @param args   List of arguments given by the sender.
     */
    void execute(CommandSender sender, String[] args);

}
