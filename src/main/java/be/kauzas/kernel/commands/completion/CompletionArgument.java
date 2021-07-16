package be.kauzas.kernel.commands.completion;

import java.util.List;

/**
 * Tab completion argument representation
 * with sub arguments, name and permission.
 */
public class CompletionArgument {

    private final String name;
    private final String permission;
    private final List<CompletionArgument> subArguments;

    /**
     * Constructor of {@link CompletionArgument} asking for the name (trigger), the permission
     * and the sub arguments of the argument.
     *
     * @param name         Name of the argument.
     * @param permission   Permission to access the argument.
     * @param subArguments List of sub arguments.
     */
    public CompletionArgument(String name, String permission, List<CompletionArgument> subArguments) {
        this.name = name;
        this.permission = permission;
        this.subArguments = subArguments;
    }

    /**
     * Get the name of the argument.
     *
     * @return Name of the argument.
     */
    public String getName() {
        return name;
    }

    /**
     * Get permission to access the argument.
     *
     * @return Permission of the argument.
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Check if the argument has a certain name.
     *
     * @param name Name to test.
     * @return true if the name equals ignoring the case.
     */
    public boolean hasName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    /**
     * Get the list of sub arguments.
     *
     * @return List of sub arguments.
     */
    public List<CompletionArgument> getSubArguments() {
        return subArguments;
    }

    /**
     * Add a sub argument to the current argument.
     *
     * @param argument {@link CompletionArgument} to add.
     */
    public void addArgument(CompletionArgument argument) {
        this.subArguments.add(argument);
    }

}
