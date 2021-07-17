package be.kauzas.kernel.items;

import be.kauzas.kernel.utils.Builder;
import be.kauzas.kernel.utils.ChatUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static be.kauzas.kernel.utils.ChatUtils.color;

/**
 * {@link Builder} implementation to create {@link ItemStack}.
 */
public class ItemBuilder implements Builder<ItemStack> {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;
    private String rarity;

    /**
     * Constructor of {@link ItemBuilder} asking for the
     * item type.
     *
     * @param material Type of the item.
     */
    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * Constructor of {@link ItemBuilder} asking for the
     * item type and the amount.
     *
     * @param material Type of the item.
     * @param amount   Amount of the item.
     */
    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
        this.itemStack.setAmount(amount);
    }

    /**
     * Constructor of {@link ItemBuilder} asking for the
     * base item to edit.
     *
     * @param itemStack {@link ItemStack} to edit.
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * Define a custom display name for the item.
     *
     * @param name Name of the item.
     * @return Current builder.
     */
    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(color(name));
        return this;
    }

    /**
     * Define a custom lore for the item.
     *
     * @param lore List of lore lines.
     * @return Current builder.
     */
    public ItemBuilder setLore(List<String> lore) {
        List<String> coloredLore = lore.stream().map(ChatUtils::color).collect(Collectors.toList());
        itemMeta.setLore(coloredLore);
        return this;
    }

    /**
     * Define a custom lore for the item.
     *
     * @param lore Array of lore lines.
     * @return Current builder.
     */
    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * Define the type of the item.
     *
     * @param material Type of the item.
     * @return Current builder.
     */
    public ItemBuilder setType(Material material) {
        itemStack.setType(material);
        return this;
    }

    /**
     * Define amount of items.
     *
     * @param amount Amount of items.
     * @return Current builder.
     */
    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Add item flags the the item.
     *
     * @param itemFlags List of flags.
     * @return Current builder.
     */
    public ItemBuilder addFlags(List<ItemFlag> itemFlags) {
        return addFlags(itemFlags.toArray(ItemFlag[]::new));
    }

    /**
     * Add item flags the the item.
     *
     * @param itemFlags Array of flags.
     * @return Current builder.
     */
    public ItemBuilder addFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Add an enchantment to the item.
     *
     * @param enchantment {@link Enchantment} to add.
     * @param level       Level of the enchantment.
     * @return Current builder.
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Define the durability of the item.
     *
     * @return Current builder.
     */
    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    /**
     * Define the item as unbreakable.
     *
     * @return Current builder.
     */
    public ItemBuilder setUnbreakable() {
        itemMeta.setUnbreakable(true);
        return this;
    }

    /**
     * Define the item as glowing.
     *
     * @return Current builder.
     */
    public ItemBuilder setGlowing() {
        addEnchantment(itemStack.getType() != Material.BOW ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 10);
        addFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * Define the owner of the item if skull.
     *
     * @param owner Owner of the skull.
     * @return Current builder.
     */
    public ItemBuilder setSkullOwner(String owner) {
        if ((itemStack.getType() == Material.PLAYER_HEAD)) {
            SkullMeta skullMeta = (SkullMeta) itemMeta;
            skullMeta.setOwner(owner);
            this.itemMeta = skullMeta;
        }
        return this;
    }

    /**
     * Define the texture of the item if skull.
     *
     * @param texture Texture of the skull.
     * @return Current builder.
     */
    public ItemBuilder texture(String texture) {
        Validate.notNull(texture, "The texture is null.");
        if ((itemStack.getType() == Material.PLAYER_HEAD)) {
            SkullMeta headMeta = (SkullMeta) itemMeta;
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);

            profile.getProperties().put("textures", new Property("textures", texture));

            try {
                Field profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);

            } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
                error.printStackTrace();
            }
            itemMeta = headMeta;
        }
        return this;
    }

    /**
     * Define the color of the item if potion.
     *
     * @param color Color of the potion.
     * @return Current builder.
     */
    public ItemBuilder setPotionColor(Color color) {
        if (itemStack.getType() == Material.POTION) {
            PotionMeta potionMeta = (PotionMeta) itemMeta;
            potionMeta.setColor(color);
            itemMeta = potionMeta;
        }
        return this;
    }

    /**
     * Define the color of the item if firework star.
     *
     * @param color Color of the firework star.
     * @return Current builder.
     */
    public ItemBuilder setFireworkStarColor(Color color) {
        if (itemStack.getType() == Material.FIREWORK_STAR) {
            FireworkEffectMeta fireworkMeta = (FireworkEffectMeta) itemMeta;
            FireworkEffect effect = FireworkEffect.builder().withColor(color).build();
            fireworkMeta.setEffect(effect);
            itemMeta = fireworkMeta;
        }
        return this;
    }

    /**
     * Add a custom string tag to the item.
     *
     * @param plugin Main class of the plugin.
     * @param key    Key of the tag.
     * @param value  Value of the tag.
     * @return Current builder.
     */
    public ItemBuilder setString(JavaPlugin plugin, String key, String value) {
        ItemTags tags = new ItemTags(itemMeta, plugin);
        tags.setString(key, value);
        return this;
    }

    /**
     * Add a custom int tag to the item.
     *
     * @param plugin Main class of the plugin.
     * @param key    Key of the tag.
     * @param value  Value of the tag.
     * @return Current builder.
     */
    public ItemBuilder setInt(JavaPlugin plugin, String key, int value) {
        ItemTags tags = new ItemTags(itemMeta, plugin);
        tags.setInt(key, value);
        return this;
    }

    /**
     * Get {@link ItemTags} for an item.
     *
     * @param plugin    Plugin to get the stored data.
     * @param itemStack ItemStack to get the data on.
     * @return ItemTags instance for the given item.
     */
    public static ItemTags getTags(JavaPlugin plugin, ItemStack itemStack) {
        assert itemStack.getItemMeta() != null;
        return new ItemTags(itemStack.getItemMeta(), plugin);
    }

    /**
     * Set item rarity.
     * <p>
     * Will add a lore line at the end of the lore.
     *
     * @param rarity Rarity of the item.
     * @return Current builder.
     * @see ItemRarity
     */
    public ItemBuilder setRarity(ItemRarity rarity) {
        this.rarity = rarity.getTag();
        return this;
    }

    /**
     * Set item rarity.
     * <p>
     * Will add a lore line at the end of the lore.
     *
     * @param rarity Rarity of the item.
     * @return Current builder.
     */
    public ItemBuilder setRarity(String rarity) {
        this.rarity = color(rarity);
        return this;
    }

    /**
     * Build the final {@link ItemStack}.
     *
     * @return Built {@link ItemStack}.
     */
    @Override
    public ItemStack build() {
        if (rarity != null) {
            if (itemMeta.getLore() == null) itemMeta.setLore(new ArrayList<>());
            if (!itemMeta.getLore().isEmpty())
                itemMeta.getLore().add(" ");
            itemMeta.getLore().add(rarity);
        }

        this.itemStack.setItemMeta(this.itemMeta);
        return itemStack;
    }

}
