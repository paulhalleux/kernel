package be.kauzas.kernel.commands.completion;

import org.bukkit.command.TabCompleter;

/**
 * Interface for tab completable {@link be.kauzas.kernel.commands.AbstractCommand}
 * that provides the {@link TabCompleter}.
 */
public interface Completable {

    TabCompleter getTabCompleter();

}
