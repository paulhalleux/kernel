package be.kauzas.kernel.packets.prompt;

import be.kauzas.kernel.menus.prompt.SignPrompt;
import be.kauzas.kernel.utils.ChatUtils;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manager for sign prompt packets.
 */
public class SignPromptPacketManager {

    private static final int ACTION_INDEX = 9;
    private static final String NBT_FORMAT = "{\"text\":\"%s\"}";
    private static final String NBT_BLOCK_ID = "minecraft:sign";

    private final Map<Player, SignPrompt> inputs;
    private static SignPromptPacketManager instance;

    /**
     * Constructor of {@link SignPromptPacketManager}.
     */
    public SignPromptPacketManager() {
        inputs = new ConcurrentHashMap<>();
    }

    /**
     * Get the instance of the manager.
     *
     * @return Manager instance.
     */
    public static SignPromptPacketManager getInstance() {
        if (instance == null) instance = new SignPromptPacketManager();
        return instance;
    }

    /**
     * Add an input for a player using a prompt.
     *
     * @param player Player that is using a prompt.
     * @param prompt Prompt that player is using.
     * @return Prompt.
     */
    public SignPrompt addInput(Player player, SignPrompt prompt) {
        return inputs.put(player, prompt);
    }

    /**
     * Remove an input for a player using a prompt.
     *
     * @param player Player that is using a prompt.
     * @return Prompt used.
     */
    public SignPrompt removeInput(Player player) {
        return inputs.remove(player);
    }

    /**
     * Open a prompt for a player.
     *
     * @param player Player to open the prompt.
     * @param prompt Prompt to open.
     */
    public void openPrompt(Player player, SignPrompt prompt) {
        BlockPosition position = prompt.getPosition();
        player.sendBlockChange(position.toLocation(player.getWorld()), Material.OAK_SIGN.createBlockData());

        PacketContainer openSign = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
        PacketContainer signData = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.TILE_ENTITY_DATA);

        openSign.getBlockPositionModifier().write(0, position);

        NbtCompound signNBT = (NbtCompound) signData.getNbtModifier().read(0);

        signNBT.put("Text" + (3), String.format(NBT_FORMAT, ChatUtils.color("&m-----------")));
        signNBT.put("Text" + (4), String.format(NBT_FORMAT, ChatUtils.color(prompt.getTitle())));

        signNBT.put("x", position.getX());
        signNBT.put("y", position.getY());
        signNBT.put("z", position.getZ());
        signNBT.put("id", NBT_BLOCK_ID);

        signData.getBlockPositionModifier().write(0, position);
        signData.getIntegers().write(0, ACTION_INDEX);
        signData.getNbtModifier().write(0, signNBT);

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, signData);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, openSign);
            addInput(player, prompt);
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

}
