package at.yedel.dreamersdeluxe.config;



import at.yedel.dreamersdeluxe.DreamersDeluxe;
import at.yedel.dreamersdeluxe.features.BedwarsXPHud;
import at.yedel.dreamersdeluxe.features.MagicMilkTimeHud;
import at.yedel.dreamersdeluxe.utils.update.UpdateManager;
import at.yedel.dreamersdeluxe.utils.update.UpdateSource;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.libs.universal.UDesktop;
import cc.polyfrost.oneconfig.utils.Notifications;



public class DreamersConfig extends Config {
    private static final DreamersConfig INSTANCE = new DreamersConfig();

    public static DreamersConfig getInstance() {
        return INSTANCE;
    }

    private static transient final int RED_ARMOR_COLOR = 0xFF0000;
    private static transient final int BLUE_ARMOR_COLOR = 0x0000FF;
    private static transient final int GREEN_ARMOR_COLOR = 0x48CC18;
    private static transient final int YELLOW_ARMOR_COLOR = 0xFFFF00;
    private static transient final int AQUA_ARMOR_COLOR = 0x00FFFF;
    private static transient final int WHITE_ARMOR_COLOR = 0xFFFFFF;
    private static transient final int PINK_ARMOR_COLOR = 0xEF83A4;
    private static transient final int GRAY_ARMOR_COLOR = 0x808080;

    private DreamersConfig() {
        super(
            new Mod(
                "DreamersDeluxe",
                ModType.HYPIXEL,
                "/assets/dreamersdeluxe/dreamersdeluxe.png"
            ),
            "dreamersdeluxe.json",
            true,
            true
        );
        initialize();
        addDependency("defusalHelperColor", "bedwarsDefusalHelper");
        for (String color: new String[] { "red", "blue", "green", "yellow", "aqua", "white", "pink", "gray" }) {
            addDependency(color + "Color", "customArmorColors");
        }
    }
    
    @Dropdown(
        name = "Update Source",
        description = "Where to get updates from. Use GitHub for earlier releases and Modrinth for more stable releases.",
        category = "General",
        subcategory = "Updates",
        options = {"Modrinth", "GitHub"}
    )
    public int updateSource = 0;

    public UpdateSource getUpdateSource() {
        if (updateSource == 0) {
            return UpdateSource.MODRINTH;
        }
        else {
            return UpdateSource.GITHUB;
        }
    }
    
    @Switch(
        name = "Automatically Check for Updates",
        description = "Checks for updates on game load",
        category = "General",
        subcategory = "Updates"
    )
    public boolean automaticallyCheckForUpdates = true;

    @Button(
        name = "Modrinth Link",
        description = "Click to open the Modrinth site",
        category = "General",
        subcategory = "Updates",
        text = "Open"
    )
    public void openModrinthLink() {
        if (!UDesktop.browse(DreamersDeluxe.getInstance().getUpdateManager().getModrinthLink())) {
            Notifications.INSTANCE.send("DreamersDeluxe", "Couldn't open modrinth link!");
        }
    }

    @Button(
        name = "GitHub Link",
        description = "Click to open the GitHub repository",
        category = "General",
        subcategory = "Updates",
        text = "Open"
    )
    public void openGitHubRepository() {
        if (!UDesktop.browse(DreamersDeluxe.getInstance().getUpdateManager().getGithubLink())) {
            Notifications.INSTANCE.send("DreamersDeluxe", "Couldn't open github link!");
        }
    }

    @Button(
        name = "Check for Updates",
        description = "Check for updates with the selected source",
        category = "General",
        subcategory = "Updates",
        text = "Check",
        size = 2
    )
    public void checkForUpdates() {
        DreamersDeluxe.getInstance().getUpdateManager().checkForUpdates(getUpdateSource(), UpdateManager.FeedbackMethod.NOTIFICATIONS);
    }

    @Switch(
        name = "Debug Location Flag",
        description = "Enable this flag to skip the BedWars server location check, always enabling every feature outside of the game.",
        subcategory = "Debug"
    )
    public boolean debugLocationFlag = false;

    @Switch(
        name = "Custom Armor Colors",
        description = "Changes the color of leather armor for different teams.",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public boolean customArmorColors = false;

    @Button(
        name = "Reset Colors",
        text = "Reset",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public Runnable resetColors = () -> {
        redColor = new OneColor(RED_ARMOR_COLOR);
        blueColor = new OneColor(BLUE_ARMOR_COLOR);
        greenColor = new OneColor(GREEN_ARMOR_COLOR);
        yellowColor = new OneColor(YELLOW_ARMOR_COLOR);
        aquaColor = new OneColor(AQUA_ARMOR_COLOR);
        whiteColor = new OneColor(WHITE_ARMOR_COLOR);
        pinkColor = new OneColor(PINK_ARMOR_COLOR);
        grayColor = new OneColor(GRAY_ARMOR_COLOR);
        save();
    };

    @Color(
        name = "Red Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor redColor = new OneColor(RED_ARMOR_COLOR);

    @Color(
        name = "Blue Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor blueColor = new OneColor(BLUE_ARMOR_COLOR);

    @Color(
        name = "Green Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor greenColor = new OneColor(GREEN_ARMOR_COLOR);

    @Color(
        name = "Yellow Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor yellowColor = new OneColor(YELLOW_ARMOR_COLOR);

    @Color(
        name = "Aqua Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor aquaColor = new OneColor(AQUA_ARMOR_COLOR);

    @Color(
        name = "White Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor whiteColor = new OneColor(WHITE_ARMOR_COLOR);

    @Color(
        name = "Pink Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor pinkColor = new OneColor(PINK_ARMOR_COLOR);

    @Color(
        name = "Gray Color",
        category = "Gameplay",
        subcategory = "Visual",
        allowAlpha = false
    )
    public OneColor grayColor = new OneColor(GRAY_ARMOR_COLOR);

    public int getCustomArmorColor(int original) {
        switch (original) {
            case RED_ARMOR_COLOR: return redColor.getRGB();
            case BLUE_ARMOR_COLOR: return blueColor.getRGB();
            case GREEN_ARMOR_COLOR: return greenColor.getRGB();
            case YELLOW_ARMOR_COLOR: return yellowColor.getRGB();
            case AQUA_ARMOR_COLOR: return aquaColor.getRGB();
            case WHITE_ARMOR_COLOR: return whiteColor.getRGB();
            case PINK_ARMOR_COLOR: return pinkColor.getRGB();
            case GRAY_ARMOR_COLOR: return grayColor.getRGB();
        }
        return original;
    }

    @Switch(
        name = "BedWars Defusal Helper",
        description = "Highlights redstone for the BedWars defusal challenge.",
        category = "Challenges",
        subcategory = "Gameplay"
    )
    public boolean bedwarsDefusalHelper = true;

    @Color(
        name = "Defusal Helper Color",
        description = "The color of defusal helper redstone highlights.",
        category = "Challenges",
        subcategory = "Gameplay"
    )
    public OneColor defusalHelperColor = new OneColor(246, 94, 94, 255);

    @HUD(
        name = "Bedwars XP Display HUD",
        category = "Display"
    )
    public BedwarsXPHud bedwarsXPHud = new BedwarsXPHud();

    @HUD(
        name = "Magic Milk Time HUD",
        category = "Display"
    )
    public MagicMilkTimeHud magicMilkTimeHud = new MagicMilkTimeHud();
    
    @Checkbox(
        name = "Light Green Token Messages",
        description = "Make token messages light green instead of green (only in bedwars) to make them appear different from emerald messages.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean lightGreenTokenMessages = false;

    @Checkbox(
        name = "Hide Token Messages",
        description = "Hide token messages completely.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hideTokenMessages = false;

    @Checkbox(
        name = "Hide Bedwars XP Messages",
        description = "Hide bedwars xp messages in-game from things like kills and resources.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hideBedwarsXPMessages = false;

    @Checkbox(
        name = "Hide Item Purchase Messages",
        description = "Hide messages from purchasing items at the Item Shop.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hideItemPurchaseMessages = false;

    @Checkbox(
        name = "Hide Punch Deposit Messages",
        description = "Hide messages from depositing items into chests.",
        category = "Chat",
        subcategory = "General"
    )
    public boolean hidePunchDepositMessages = false;

    
    @Checkbox(
        name = "Hide Slumber Ticket Messages",
        description = "Hide slumber ticket messages in-game from things like kills and wins.",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideSlumberTicketMessages = false;

    
    @Checkbox(
        name = "Hide Silver Coin Count",
        description = "Hide the silver coin count from item purchase messages.",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideSilverCoinCount = false;

    
    @Checkbox(
        name = "Hide Comfy Pillow Messages",
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
        name = "Hide Dreamer's Soul Fragment Messages",
        description = "Hide \"+1 Dreamer's Soul Fragment!\" messages.",
        category = "Chat",
        subcategory = "Slumber Hotel"
    )
    public boolean hideDreamerSoulFragmentMessages = false;
}
