package be.kauzas.kernel.commands.completion;

import java.util.List;

public class CompletionArgument {

    private final String name;
    private final String permission;
    private final List<CompletionArgument> subArguments;

    public CompletionArgument(String name, String permission, List<CompletionArgument> subArguments) {
        this.name = name;
        this.permission = permission;
        this.subArguments = subArguments;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public boolean hasName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public List<CompletionArgument> getSubArguments() {
        return subArguments;
    }

    public void addArgument(CompletionArgument argument) {
        this.subArguments.add(argument);
    }

}
