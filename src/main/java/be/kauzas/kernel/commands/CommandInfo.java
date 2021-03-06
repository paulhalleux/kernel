package be.kauzas.kernel.commands;

import be.kauzas.kernel.service.ReflectionService;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define who can execute a command.
 * Note that if a {@link Player} can execute a command, {@link ConsoleCommandSender} can
 * execute it too.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {

    public enum Sender {PLAYER, CONSOLE, BOTH}

    /**
     * Get who can execute the command.
     *
     * @return CommandSender who can execute the command.
     * @see Sender
     */
    Sender sender() default Sender.BOTH;

    /**
     * Define if the command has to be registered
     * automatically by a {@link ReflectionService}.
     *
     * @return true if the command has to be registered.
     */
    boolean register() default true;

}
