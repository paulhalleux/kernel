package be.kauzas.kernel.commands;

import org.bukkit.command.CommandSender;
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

    Class<? extends CommandSender> sender() default Player.class;

    boolean register() default true;

}
