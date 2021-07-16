package be.kauzas.kernel.events.command;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.events.AbstractEvent;
import org.bukkit.command.CommandSender;

/**
 * Event called when a player execute a command.
 */
public class CommandExecuteEvent extends AbstractEvent {

    private final AbstractCommand command;
    private final CommandSender sender;
    private final String trigger;

    /**
     * Constructor of {@link CommandExecuteEvent}.
     *
     * @param command Executed command.
     * @param sender  Command sender.
     * @param trigger Trigger used by the sender.
     */
    public CommandExecuteEvent(AbstractCommand command, CommandSender sender, String trigger) {
        this.command = command;
        this.sender = sender;
        this.trigger = trigger;
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

    /**
     * Get the trigger used to execute the command.
     *
     * @return Used trigger.
     */
    public String getTrigger() {
        return trigger;
    }

}
