package be.kauzas.kernel.commands.composite;

import be.kauzas.kernel.KernelPlugin;
import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.service.IgnoreService;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Composite command that is build based on
 * multiple commands. These commands search for
 * {@link SubCommand} with {@link SubCommand#getBase()}
 * of the same type.
 */
@IgnoreService
public abstract class CompositeCommand extends AbstractCommand {

    private final KernelPlugin plugin;

    /**
     * Constructor of {@link CompositeCommand}.
     *
     * @param plugin  Main {@link KernelPlugin} for getting sub commands.
     * @param trigger Trigger of the command.
     * @param aliases Aliases of the command.
     */
    public CompositeCommand(KernelPlugin plugin, String trigger, String... aliases) {
        super(trigger, aliases);
        this.plugin = plugin;
    }

    /**
     * Method that is executed when command is used without
     * any arguments.
     *
     * @param sender Sender of the command.
     */
    public abstract void main(CommandSender sender);

    /**
     * Method that handle execution.
     *
     * @param sender {@link CommandSender} of the command.
     * @param args   List of arguments given by the sender.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) main(sender);
        else for (AbstractCommand subCommand : plugin.getCommandService().getItems().stream()
                .filter(command -> (command instanceof SubCommand && ((SubCommand) command).getBase() == getClass()))
                .collect(Collectors.toList())) {
            if (subCommand.hasTrigger(args[0])) {
                plugin.getCommandHandler().handle(subCommand, sender, args[0], Arrays.copyOfRange(args, 1, args.length));
                break;
            }
        }
    }

}
