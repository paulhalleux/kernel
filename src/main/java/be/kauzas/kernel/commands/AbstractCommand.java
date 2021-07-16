package be.kauzas.kernel.commands;

import java.util.Arrays;

/**
 * Represent an executable player.
 * Implementations of {@link AbstractCommand} must be decorated
 * with {@link CommandInfo} annotation to be registered with the
 * {@link CommandService}.
 */
public abstract class AbstractCommand implements RunnableCommand {

    private final String trigger;
    private final String[] aliases;

    /**
     * Constructor of {@link AbstractCommand}.
     *
     * @param trigger Trigger of the command.
     * @param aliases Aliases of the command.
     */
    public AbstractCommand(String trigger, String... aliases) {
        this.trigger = trigger;
        this.aliases = aliases;
    }

    /**
     * Get the trigger of the command.
     *
     * @return Trigger of the command.
     */
    public String getTrigger() {
        return trigger;
    }

    /**
     * Get the aliases of the command.
     *
     * @return Aliases of the command.
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Check if the command has a certain trigger
     * or alias.
     *
     * @param trigger Searched trigger.
     * @return true if the command has the searched trigger or alias.
     */
    public boolean hasTrigger(String trigger) {
        return trigger.equalsIgnoreCase(this.trigger)
                || Arrays.stream(aliases).anyMatch(string -> string.equalsIgnoreCase(trigger));
    }

    /**
     * Get the {@link CommandInfo} annotation of the command.
     *
     * @return CommandInfo of the command or null.
     */
    public CommandInfo getInfos() {
        return this.getClass().getAnnotation(CommandInfo.class);
    }

}
