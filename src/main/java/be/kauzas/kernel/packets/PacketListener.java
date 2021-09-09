package be.kauzas.kernel.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Interface for packets listeners.
 */
public interface PacketListener {

    /**
     * Method that will listen for the given
     * packet type.
     *
     * @param plugin Main plugin class.
     * @param packet Received {@link PacketEvent}.
     */
    void listen(JavaPlugin plugin, PacketEvent packet);

    /**
     * Get the type of the packet to listen to.
     *
     * @return Type of the packet.
     */
    PacketType getType();

}
