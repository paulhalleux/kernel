package be.kauzas.kernel.commands.completion;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompletionPreset {

    public static final List<String> PLAYERS = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    public static final List<String> ENTITY = Arrays.stream(EntityType.values()).map(Enum::name).collect(Collectors.toList());
    public static final List<String> SPAWNABLE_ENTITY = Arrays.stream(EntityType.values()).filter(EntityType::isSpawnable).map(Enum::name).collect(Collectors.toList());
    public static final List<String> ALIVE_ENTITY = Arrays.stream(EntityType.values()).filter(EntityType::isAlive).map(Enum::name).collect(Collectors.toList());

}
