package at.yedel.dreamersdeluxe.config;



import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Checkbox;
import org.polyfrost.oneconfig.api.config.v1.annotations.Color;
import org.polyfrost.oneconfig.api.config.v1.annotations.DependsOn;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;



public class DreamersConfig extends Config {
    private static final DreamersConfig INSTANCE = new DreamersConfig();

    public static DreamersConfig getInstance() {
        return INSTANCE;
    }

    private DreamersConfig() {
        super("dreamersdeluxe", "/assets/dreamersdeluxe/dreamersdeluxe.png", "DreamersDeluxe", Category.HYPIXEL);
    }

    @Switch(
        title = "Enabled",
        description = "Global toggle for the mod."
    )
    public boolean enabled = true;

    @Switch(
        title = "Debug Location Flag",
        description = "Enable this flag to skip the BedWars server location check, always enabling every feature outside of the game.",
        subcategory = "Debug"
    )
    public boolean debugLocationFlag = true;
    
    @Switch(
        title = "BedWars Defusal Helper",
        description = "Highlights redstone for the BedWars defusal challenge.",
        category = "Challenges",
        subcategory = "Gameplay"
    )
    public boolean bedwarsDefusalHelper = true;

    @DependsOn("bedwarsDefusalHelper")
    @Color(
        title = "Defusal Helper Color",
        description = "The color of defusal helper redstone highlights.",
        category = "Challenges",
        subcategory = "Gameplay"
    )
    public int defusalHelperColor = -631202;
    
    @Checkbox(
        title = "Light Green Token Messages",
        description = "Make token messages light green instead of green (only in bedwars) to make them appear different from emerald messages.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean lightGreenTokenMessages = false;

    @Checkbox(
        title = "Hide Token Messages",
        description = "Hide token messages completely.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hideTokenMessages = false;

    @Checkbox(
        title = "Hide Bedwars XP Messages",
        description = "Hide bedwars xp messages in-game from things like kills and resources.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hideBedwarsXPMessages = false;

    @Checkbox(
        title = "Hide Item Purchase Messages",
        description = "Hide messages from purchasing items at the Item Shop.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hideItemPurchaseMessages = false;

    @Checkbox(
        title = "Hide Punch Deposit Messages",
        description = "Hide messages from depositing items into chests.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hidePunchDepositMessages = false;
    
    @Checkbox(
        title = "Hide Slumber Ticket Messages",
        description = "Hide slumber ticket messages in-game from things like kills and wins.",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideSlumberTicketMessages = false;

    @Checkbox(
        title = "Hide Silver Coin Count",
        description = "Hide the silver coin count from item purchase messages.",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideSilverCoinCount = false;

    @Checkbox(
        title = "Hide Comfy Pillow Messages",
        description = "Hides the following messages:" +
            "\n\"You are now carrying x1 Comfy Pillows, bring it back to your shop keeper!\"" +
            "\n\"You cannot return items to another team's Shopkeeper!\"" +
            "\n\"You cannot carry any more Comfy Pillows!\"" +
            "\n\"You died while carrying 1x Comfy Pillows!\"",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideComfyPillowMessages = false;

    @Checkbox(
        title = "Hide Dreamer's Soul Fragment Messages",
        description = "Hide \"+1 Dreamer's Soul Fragment!\" messages.",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideDreamerSoulFragmentMessages = false;
}
