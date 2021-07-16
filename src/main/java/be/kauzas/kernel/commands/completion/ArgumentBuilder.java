package be.kauzas.kernel.commands.completion;

import be.kauzas.kernel.exceptions.EmptyArgumentListException;
import be.kauzas.kernel.exceptions.UnreachableBuilderException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArgumentBuilder {

    private CompletionArgument base;
    private CompletionArgument last;
    private final CompletionBuilder builder;
    private final ArgumentBuilder back;

    public ArgumentBuilder(CompletionArgument base, CompletionBuilder builder) {
        this.base = base;
        this.builder = builder;
        this.back = null;
    }

    public ArgumentBuilder(CompletionArgument base, CompletionBuilder builder, ArgumentBuilder back) {
        this.base = base;
        this.builder = builder;
        this.back = back;
    }

    public ArgumentBuilder addSubArgument(String arg, String permission) {
        CompletionArgument argument = new CompletionArgument(arg, permission, new ArrayList<>());
        last = argument;
        base.addArgument(argument);
        return this;
    }

    public ArgumentBuilder addSubArgument(String arg) {
        return addSubArgument(arg, null);
    }

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

    public ArgumentBuilder addPreset(List<String> preset) {
        return addPreset(preset, null, null);
    }

    public ArgumentBuilder addPreset(List<String> preset, Consumer<ArgumentBuilder> then) {
        return addPreset(preset, null, then);
    }

    public ArgumentBuilder addPreset(List<String> preset, String permission) {
        return addPreset(preset, permission, null);
    }

    public ArgumentBuilder select() {
        if (last == null)
            throw new EmptyArgumentListException("Cannot select last added sub argument with empty sub argument list.");
        return new ArgumentBuilder(last, builder, this);
    }

    public ArgumentBuilder back() {
        if (back == null) throw new UnreachableBuilderException("Cannot go back to previous builder on lowest level.");
        return back;
    }

    public CompletionBuilder builder() {
        return builder;
    }

}
