package be.kauzas.kernel.packets.prompt;

import be.kauzas.kernel.menus.prompt.SignPrompt;
import be.kauzas.kernel.packets.PacketListener;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Listener for the prompt packets.
 */
public class PromptPacketListener implements PacketListener {

    @Override
    public void listen(JavaPlugin plugin, PacketEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

        SignPrompt signPrompt = SignPromptPacketManager.getInstance().removeInput(player);
        if (signPrompt == null) return;

        String[] lines = event.getPacket().getStringArrays().read(0);
        String prompt = lines[0] + " " + lines[1];
        boolean success = signPrompt.validate(player, prompt);
        if (success) {
            signPrompt.setValue(prompt);
            signPrompt.onPromptEntered(player, signPrompt.getValue());
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (player.isOnline()) {
                BlockPosition position = signPrompt.getPosition();
                Location finalLocation = new Location(player.getWorld(), position.getX(), position.getY(), position.getZ());
                player.sendBlockChange(finalLocation, finalLocation.getBlock().getBlockData());
            }
        }, 2L);
    }

    @Override
    public PacketType getType() {
        return PacketType.Play.Client.UPDATE_SIGN;
    }

}
