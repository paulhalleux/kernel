package be.kauzas.kernel.items;

/**
 * Enum for the common
 * item rarity.
 */
public enum ItemRarity {

    COMMON("§f§lCOMMUN"),
    UNCOMMON("7§lPEU COMMUN"),
    RARE("§e§lRARE"),
    EPIC("§b§lEPIQUE"),
    LEGENDARY("§6§lLEGENDAIRE"),
    MYTHIC("§d§lMYTHIQUE"),
    UNOBTAINABLE("§4§lINOBTENABLE");

    private final String tag;

    /**
     * Constructor of {@link ItemRarity}.
     */
    ItemRarity(String tag) {
        this.tag = tag;
    }

    /**
     * Get the tag of the rarity.
     *
     * @return Tag of the rarity.
     */
    public String getTag() {
        return tag;
    }

}
