package be.kauzas.kernel.commands.composite;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.service.IgnoreService;

@IgnoreService
public abstract class SubCommand extends AbstractCommand {

    private final Class<? extends CompositeCommand> base;

    public SubCommand(Class<? extends CompositeCommand> base, String trigger, String... aliases) {
        super(trigger, aliases);
        this.base = base;
    }

    public Class<? extends CompositeCommand> getBase() {
        return base;
    }

}
