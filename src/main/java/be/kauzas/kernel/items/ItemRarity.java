package be.kauzas.kernel.items;

public enum ItemRarity {

    COMMON("§f§lCOMMUN"),
    UNCOMMON("7§lPEU COMMUN"),
    RARE("§e§lRARE"),
    EPIC("§b§lEPIQUE"),
    LEGENDARY("§6§lLEGENDAIRE"),
    MYTHIC("§d§lMYTHIQUE"),
    UNOBTAINABLE("§4§lINOBTENABLE");

    private String tag;

    ItemRarity(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
