package be.kauzas.kernel.events.command;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.events.AbstractEvent;
import org.bukkit.command.CommandSender;

/**
 * Event called when a player try to execute a command without
 * the permission.
 */
public class CommandPermissionDeniedEvent extends AbstractEvent {

    private final AbstractCommand command;
    private final CommandSender sender;

    /**
     * Constructor of {@link CommandPermissionDeniedEvent}.
     *
     * @param command Executed command.
     * @param sender  Command sender.
     */
    public CommandPermissionDeniedEvent(AbstractCommand command, CommandSender sender) {
        this.command = command;
        this.sender = sender;
    }

    /**
     * Get the executed command.
     *
     * @return Executed command.
     */
    public AbstractCommand getCommand() {
        return command;
    }

    /**
     * Get the sender of the command.
     *
     * @return Command sender.
     */
    public CommandSender getSender() {
        return sender;
    }

}
