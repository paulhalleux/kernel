package be.kauzas.kernel.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface Handler extends CommandExecutor {

    boolean handle(AbstractCommand abstractCommand, CommandSender sender, String[] args);

}
