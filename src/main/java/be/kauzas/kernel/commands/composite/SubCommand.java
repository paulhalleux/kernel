package be.kauzas.kernel.commands.composite;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.service.IgnoreService;

/**
 * Sub command of a {@link CompositeCommand}.
 * Has same behaviour than AbstractCommand.
 */
@IgnoreService
public abstract class SubCommand extends AbstractCommand {

    private final Class<? extends CompositeCommand> base;

    /**
     * Constructor of {@link SubCommand}.
     *
     * @param base    Base {@link CompositeCommand} class.
     * @param trigger Trigger of the sub command.
     * @param aliases Aliases of the sub command
     */
    public SubCommand(Class<? extends CompositeCommand> base, String trigger, String... aliases) {
        super(trigger, aliases);
        this.base = base;
    }

    /**
     * Get the main command of the sub command.
     *
     * @return Base {@link CompositeCommand} class.
     */
    public Class<? extends CompositeCommand> getBase() {
        return base;
    }

}
