package be.kauzas.kernel.commands.completion;

import be.kauzas.kernel.exceptions.EmptyArgumentListException;
import be.kauzas.kernel.utils.Builder;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletionBuilder implements Builder<TabCompleter> {

    private final List<CompletionArgument> arguments;
    private CompletionArgument last;

    public CompletionBuilder() {
        this.arguments = new LinkedList<>();
    }

    public CompletionBuilder addArgument(String arg, String permission) {
        CompletionArgument argument = new CompletionArgument(arg, permission, new ArrayList<>());
        last = argument;
        arguments.add(argument);
        return this;
    }

    public CompletionBuilder addArgument(String arg) {
        return addArgument(arg, null);
    }

    public ArgumentBuilder select() {
        if (last == null)
            throw new EmptyArgumentListException("Cannot select last added argument with empty argument list.");
        return new ArgumentBuilder(last, this);
    }

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

    private boolean hasPermission(CommandSender sender, CompletionArgument completionArgument) {
        return completionArgument.getPermission() == null || sender.hasPermission(completionArgument.getPermission());
    }

    public List<CompletionArgument> getArguments() {
        return arguments;
    }

}
