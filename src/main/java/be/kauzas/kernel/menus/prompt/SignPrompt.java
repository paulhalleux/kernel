package be.kauzas.kernel.menus.prompt;

import be.kauzas.kernel.packets.prompt.SignPromptPacketManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Implementation of {@link Prompt} for signs.
 */
public abstract class SignPrompt extends Prompt<String> {

    private final String title;
    private final SignPromptPacketManager packetManager;

    private BlockPosition position;

    /**
     * Constructor of {@link SignPrompt} asking for the title.
     *
     * @param title Title of the prompt.
     */
    public SignPrompt(String title) {
        this.title = title;
        this.packetManager = SignPromptPacketManager.getInstance();
    }

    @Override
    public void display(Player player) {
        Location location = player.getLocation();
        this.position = new BlockPosition(location.getBlockX(), location.getBlockY() + (255 - location.getBlockY()), location.getBlockZ());
        packetManager.openPrompt(player, this);
    }

    /**
     * Get the title of the prompt.
     *
     * @return Title of the prompt.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the position of the sign.
     *
     * @return Position of the sign.
     */
    public BlockPosition getPosition() {
        return position;
    }

    /**
     * Abstract method executed when the data has been
     * entered by the player.
     *
     * @param player Player that entered the data.
     * @param value  Entered data.
     */
    public abstract void onPromptEntered(Player player, String value);

    /**
     * Validate if the data entered by the player is correct.
     *
     * @param player Player that entered the data.
     * @param value  Entered data.
     * @return true if the data is correct, otherwise false.
     */
    public abstract boolean validate(Player player, String value);

}
