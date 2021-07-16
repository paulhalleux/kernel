package be.kauzas.kernel.commands;

import java.util.Arrays;

public abstract class AbstractCommand implements RunnableCommand {

    private final String trigger;
    private final String[] aliases;

    public AbstractCommand(String trigger, String... aliases) {
        this.trigger = trigger;
        this.aliases = aliases;
    }

    public String getTrigger() {
        return trigger;
    }

    public String[] getAliases() {
        return aliases;
    }

    public boolean hasTrigger(String trigger) {
        return trigger.equalsIgnoreCase(this.trigger)
                || Arrays.stream(aliases).anyMatch(string -> string.equalsIgnoreCase(trigger));
    }

    public CommandInfo getInfos() {
        return this.getClass().getAnnotation(CommandInfo.class);
    }

}
