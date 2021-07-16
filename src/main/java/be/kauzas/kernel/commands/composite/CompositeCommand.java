package be.kauzas.kernel.commands.composite;

import be.kauzas.kernel.KernelPlugin;
import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.service.IgnoreService;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.stream.Collectors;

@IgnoreService
public abstract class CompositeCommand extends AbstractCommand {

    private final KernelPlugin plugin;

    public CompositeCommand(KernelPlugin plugin, String trigger, String... aliases) {
        super(trigger, aliases);
        this.plugin = plugin;
    }

    public abstract void main(CommandSender sender, String[] args);

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) main(sender, args);
        else for (AbstractCommand subCommand : plugin.getCommandService().getItems().stream()
                .filter(command -> (command instanceof SubCommand && ((SubCommand) command).getBase() == getClass()))
                .collect(Collectors.toList())) {
            if (subCommand.hasTrigger(args[0])) {
                plugin.getCommandHandler().handle(subCommand, sender, Arrays.copyOfRange(args, 1, args.length));
                break;
            }
        }
    }

}
