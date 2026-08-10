package at.yedel.dreamersdeluxe.config;



import org.polyfrost.compose.render.PolyColor;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.*;



public class DreamersConfig extends Config {
    private static final DreamersConfig INSTANCE = new DreamersConfig();

    public static DreamersConfig getInstance() {
        return INSTANCE;
    }

    private DreamersConfig() {
        super("dreamersdeluxe", "/assets/dreamersdeluxe/dreamersdeluxe.png", "DreamersDeluxe", Category.HYPIXEL);
    }

    private static final int RED_ARMOR_COLOR = 0xFFFF0000;
    private static final int BLUE_ARMOR_COLOR = 0xFF0000FF;
    private static final int GREEN_ARMOR_COLOR = 0xFF48CC18;
    private static final int YELLOW_ARMOR_COLOR = 0xFFFFFF00;
    private static final int AQUA_ARMOR_COLOR = 0xFF00FFFF;
    private static final int WHITE_ARMOR_COLOR = 0xFFFFFFFF;
    private static final int PINK_ARMOR_COLOR = 0xFFEF83A4;
    private static final int GRAY_ARMOR_COLOR = 0xFF808080;

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
    public boolean debugLocationFlag = false;

    @Switch(
        title = "Custom Armor Colors",
        description = "Changes the color of leather armor for different teams.",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public boolean customArmorColors = false;

    @DependsOn("customArmorColors")
    @Color(
        title = "Red Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor redColor = new PolyColor(RED_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "Blue Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor blueColor = new PolyColor(BLUE_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "Green Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor greenColor = new PolyColor(GREEN_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "Yellow Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor yellowColor = new PolyColor(YELLOW_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "Aqua Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor aquaColor = new PolyColor(AQUA_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "White Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor whiteColor = new PolyColor(WHITE_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "Pink Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor pinkColor = new PolyColor(PINK_ARMOR_COLOR);

    @DependsOn("customArmorColors")
    @Color(
        title = "Gray Color",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public PolyColor grayColor = new PolyColor(GRAY_ARMOR_COLOR);

    public int getCustomArmorColor(int original) {
        switch (original) {
            case RED_ARMOR_COLOR: return redColor.getArgb();
            case BLUE_ARMOR_COLOR: return blueColor.getArgb();
            case GREEN_ARMOR_COLOR: return greenColor.getArgb();
            case YELLOW_ARMOR_COLOR: return yellowColor.getArgb();
            case AQUA_ARMOR_COLOR: return aquaColor.getArgb();
            case WHITE_ARMOR_COLOR: return whiteColor.getArgb();
            case PINK_ARMOR_COLOR: return pinkColor.getArgb();
            case GRAY_ARMOR_COLOR: return grayColor.getArgb();
        }
        return original;
    }
    
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
