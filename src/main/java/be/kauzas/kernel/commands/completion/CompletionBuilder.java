package be.kauzas.kernel.commands.completion;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.exceptions.EmptyArgumentListException;
import be.kauzas.kernel.utils.Builder;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Builder that create a {@link TabCompleter} for an {@link AbstractCommand}.
 */
public class CompletionBuilder implements Builder<TabCompleter> {

    private final List<CompletionArgument> arguments;
    private CompletionArgument last;

    /**
     * Constructor of {@link CompletionBuilder}.
     */
    public CompletionBuilder() {
        this.arguments = new LinkedList<>();
    }

    /**
     * Add a root argument to the completion.
     *
     * @param arg        Argument name.
     * @param permission Argument permission.
     * @return Current builder.
     */
    public CompletionBuilder addArgument(String arg, String permission) {
        CompletionArgument argument = new CompletionArgument(arg, permission, new ArrayList<>());
        last = argument;
        arguments.add(argument);
        return this;
    }

    /**
     * Add a root argument to the completion.
     *
     * @param arg Argument name.
     * @return Current builder.
     */
    public CompletionBuilder addArgument(String arg) {
        return addArgument(arg, null);
    }

    /**
     * Select the last added argument and return
     * an {@link ArgumentBuilder} for this argument.
     *
     * @return ArgumentBuilder for the last added argument.
     */
    public ArgumentBuilder select() {
        if (last == null)
            throw new EmptyArgumentListException("Cannot select last added argument with empty argument list.");
        return new ArgumentBuilder(last, this);
    }

    /**
     * Build a {@link TabCompleter} based on the list of arguments and
     * their sub arguments.
     *
     * @return Built {@link TabCompleter}.
     */
    @Override
    public TabCompleter build() {
        return (commandSender, command, s, args) -> {
            String previousArgument = args.length == 1 ? null : args[args.length - 2];
            List<CompletionArgument> output = new ArrayList<>();
            List<CompletionArgument> arguments = this.arguments.stream().filter(completionArgument -> hasPermission(commandSender, completionArgument)).collect(Collectors.toList());
            if (previousArgument == null)
                output.addAll(arguments);
            else getOptions(commandSender, previousArgument, arguments, output);
            return output.stream()
                    .filter(argument -> argument.getName().toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                    .map(CompletionArgument::getName).collect(Collectors.toList());
        };
    }

    private void getOptions(CommandSender sender, String previous, List<CompletionArgument> arguments, List<CompletionArgument> output) {
        Stream<CompletionArgument> stream = arguments.stream();
        CompletionArgument match = stream.filter(argument -> argument.hasName(previous)).findFirst().orElse(null);
        if (match != null) {
            output.addAll(match.getSubArguments().stream()
                    .filter(completionArgument -> hasPermission(sender, completionArgument))
                    .collect(Collectors.toList()));
        } else {
            for (CompletionArgument argument : arguments) {
                getOptions(sender, previous, argument.getSubArguments(), output);
            }
        }
    }

    /**
     * Check if the command sender has permission for
     * a certain {@link CompletionArgument}.
     *
     * @param sender             Command sender.
     * @param completionArgument Argument.
     * @return true if the sender has permission, otherwise false.
     */
    private boolean hasPermission(CommandSender sender, CompletionArgument completionArgument) {
        return completionArgument.getPermission() == null || sender.hasPermission(completionArgument.getPermission());
    }

    /**
     * Get the list of root arguments for the
     * completer.
     *
     * @return List of {@link CompletionArgument}.
     */
    public List<CompletionArgument> getArguments() {
        return arguments;
    }

}
