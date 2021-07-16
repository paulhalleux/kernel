package be.kauzas.kernel.commands.completion;

import be.kauzas.kernel.commands.AbstractCommand;
import be.kauzas.kernel.exceptions.EmptyArgumentListException;
import be.kauzas.kernel.exceptions.UnreachableBuilderException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Builder class that build a {@link CompletionArgument} used
 * for {@link AbstractCommand}  tab completion.
 */
public class ArgumentBuilder {

    private CompletionArgument base;
    private CompletionArgument last;
    private final CompletionBuilder builder;
    private final ArgumentBuilder back;

    /**
     * Constructor of {@link ArgumentBuilder} asking for the base
     * argument to add the sub arguments and the base {@link CompletionBuilder}.
     *
     * @param base    Base argument.
     * @param builder Base completion builder.
     */
    public ArgumentBuilder(CompletionArgument base, CompletionBuilder builder) {
        this.base = base;
        this.builder = builder;
        this.back = null;
    }

    /**
     * Constructor of {@link ArgumentBuilder} asking for the base
     * argument to add the sub arguments, the {@link ArgumentBuilder} to go back on
     * it and the base {@link CompletionBuilder}.
     *
     * @param base    Base argument.
     * @param builder Base completion builder.
     * @param back    Previous {@link ArgumentBuilder} to navigate.
     */
    public ArgumentBuilder(CompletionArgument base, CompletionBuilder builder, ArgumentBuilder back) {
        this.base = base;
        this.builder = builder;
        this.back = back;
    }

    /**
     * Add a sub argument the the current root argument.
     *
     * @param arg        Name of the argument.
     * @param permission Permission to access the argument.
     * @return Current builder.
     */
    public ArgumentBuilder addSubArgument(String arg, String permission) {
        CompletionArgument argument = new CompletionArgument(arg, permission, new ArrayList<>());
        last = argument;
        base.addArgument(argument);
        return this;
    }

    /**
     * Add a sub argument the the current root argument.
     *
     * @param arg Name of the argument.
     * @return Current builder.
     */
    public ArgumentBuilder addSubArgument(String arg) {
        return addSubArgument(arg, null);
    }

    /**
     * Add a {@link CompletionPreset} or a list of arguments as sub arguments of the root argument.
     *
     * @param preset     List of sub arguments.
     * @param permission Permission to access the arguments.
     * @param then       The rest completion available for the given preset.
     * @return Current builder.
     */
    public ArgumentBuilder addPreset(List<String> preset, String permission, Consumer<ArgumentBuilder> then) {
        for (String s : preset) {
            if (then == null) base.addArgument(new CompletionArgument(s, permission, new ArrayList<>()));
            else {
                ArgumentBuilder b = addSubArgument(s).select();
                then.accept(b);
            }
        }
        return this;
    }

    /**
     * Add a {@link CompletionPreset} or a list of arguments as sub arguments of the root argument.
     *
     * @param preset List of sub arguments.
     * @return Current builder.
     */
    public ArgumentBuilder addPreset(List<String> preset) {
        return addPreset(preset, null, null);
    }

    /**
     * Add a {@link CompletionPreset} or a list of arguments as sub arguments of the root argument.
     *
     * @param preset List of sub arguments.
     * @param then   The rest completion available for the given preset.
     * @return Current builder.
     */
    public ArgumentBuilder addPreset(List<String> preset, Consumer<ArgumentBuilder> then) {
        return addPreset(preset, null, then);
    }

    /**
     * Add a {@link CompletionPreset} or a list of arguments as sub arguments of the root argument.
     *
     * @param preset     List of sub arguments.
     * @param permission Permission to access the arguments.
     * @return Current builder.
     */
    public ArgumentBuilder addPreset(List<String> preset, String permission) {
        return addPreset(preset, permission, null);
    }

    /**
     * Select the last added sub argument to add sub arguments to him.
     * {@link #back} method can be used to go back to the current builder.
     *
     * @return new {@link ArgumentBuilder} for the last added sub argument.
     */
    public ArgumentBuilder select() {
        if (last == null)
            throw new EmptyArgumentListException("Cannot select last added sub argument with empty sub argument list.");
        return new ArgumentBuilder(last, builder, this);
    }

    /**
     * Go back to the previous argument builder.
     *
     * @return Previous argument builder.
     */
    public ArgumentBuilder back() {
        if (back == null) throw new UnreachableBuilderException("Cannot go back to previous builder on lowest level.");
        return back;
    }

    /**
     * Go back to the base {@link CompletionBuilder}.
     *
     * @return Base completion builder.
     */
    public CompletionBuilder builder() {
        return builder;
    }

}
