package at.yedel.dreamersdeluxe.config;






/*? if forge {*//*
import at.yedel.dreamersdeluxe.DreamersDeluxe;
import at.yedel.dreamersdeluxe.hud.BedwarsXPHud;
import at.yedel.dreamersdeluxe.hud.MagicMilkTimeHud;
import at.yedel.dreamersdeluxe.utils.update.UpdateManager;
import at.yedel.dreamersdeluxe.utils.update.UpdateSource;
*//*?}*/
/*? if v0 {*//*

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.libs.universal.UDesktop;
import cc.polyfrost.oneconfig.utils.Notifications;
 
*//*?} else {*/
import org.polyfrost.compose.render.PolyColor;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.*;
import org.polyfrost.oneconfig.api.notifications.v1.Notifications;
/*?}*/


//~ config_bridge
public class DreamersConfig extends Config {
    private static final DreamersConfig INSTANCE = new DreamersConfig();

    public static DreamersConfig getInstance() {
        return INSTANCE;
    }


    // OR's the number with 0xFF0000
    // initial idea was to replace 0x with 0xFF, but this does not work properly because some colors start with FF
    //~ if modern '= 0x' -> '= -16777216 | 0x' {
    private static transient final int RED_ARMOR_COLOR = -16777216 | 0xFF0000;
    private static transient final int BLUE_ARMOR_COLOR = -16777216 | 0x0000FF;
    private static transient final int GREEN_ARMOR_COLOR = -16777216 | 0x48CC18;
    private static transient final int YELLOW_ARMOR_COLOR = -16777216 | 0xFFFF00;
    private static transient final int AQUA_ARMOR_COLOR = -16777216 | 0x00FFFF;
    private static transient final int WHITE_ARMOR_COLOR = -16777216 | 0xFFFFFF;
    private static transient final int PINK_ARMOR_COLOR = -16777216 | 0xEF83A4;
    private static transient final int GRAY_ARMOR_COLOR = -16777216 | 0x808080;
    //~}

    private DreamersConfig() {
        /*? if v0 {*//*
        
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
        
        *//*?} else {*/
        super("dreamersdeluxe", "/assets/dreamersdeluxe/dreamersdeluxe.png", "DreamersDeluxe", Category.HYPIXEL);
        /*?}*/
        addDependency("defusalHelperColor", "bedwarsDefusalHelper");
        for (String color: new String[] { "red", "blue", "green", "yellow", "aqua", "white", "pink", "gray" }) {
            addDependency(color + "Color", "customArmorColors");
        }
    }

    /*? if forge {*//*
    
    @Dropdown(
        title = "Update Source",
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
        title = "Automatically Check for Updates",
        description = "Checks for updates on game load",
        category = "General",
        subcategory = "Updates"
    )
    public boolean automaticallyCheckForUpdates = true;

    @Button(
        title = "Modrinth Link",
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
        title = "GitHub Link",
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
        title = "Check for Updates",
        description = "Check for updates with the selected source",
        category = "General",
        subcategory = "Updates",
        text = "Check",
        size = 2
    )
    public void checkForUpdates() {
        DreamersDeluxe.getInstance().getUpdateManager().checkForUpdates(getUpdateSource(), UpdateManager.FeedbackMethod.NOTIFICATIONS);
    }
    
    *//*?}*/

    /*? if v1 {*/
    @Switch(
        title = "Enabled",
        description = "Global toggle for the mod."
    )
    public boolean enabled = true;
    /*?}*/

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

    /*? if v0 {*//*
    
    @Button(
        title = "Reset Colors",
        text = "Reset",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public Runnable resetColors = () -> {
        redColor = new PolyColor(RED_ARMOR_COLOR);
        blueColor = new PolyColor(BLUE_ARMOR_COLOR);
        greenColor = new PolyColor(GREEN_ARMOR_COLOR);
        yellowColor = new PolyColor(YELLOW_ARMOR_COLOR);
        aquaColor = new PolyColor(AQUA_ARMOR_COLOR);
        whiteColor = new PolyColor(WHITE_ARMOR_COLOR);
        pinkColor = new PolyColor(PINK_ARMOR_COLOR);
        grayColor = new PolyColor(GRAY_ARMOR_COLOR);
        save();
    };
    
    *//*?}*/

    @Color(
        title = "Red Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor redColor = new PolyColor(RED_ARMOR_COLOR);

    @Color(
        title = "Blue Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor blueColor = new PolyColor(BLUE_ARMOR_COLOR);

    @Color(
        title = "Green Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor greenColor = new PolyColor(GREEN_ARMOR_COLOR);

    @Color(
        title = "Yellow Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor yellowColor = new PolyColor(YELLOW_ARMOR_COLOR);

    @Color(
        title = "Aqua Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor aquaColor = new PolyColor(AQUA_ARMOR_COLOR);

    @Color(
        title = "White Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor whiteColor = new PolyColor(WHITE_ARMOR_COLOR);

    @Color(
        title = "Pink Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
    )
    public PolyColor pinkColor = new PolyColor(PINK_ARMOR_COLOR);

    @Color(
        title = "Gray Color",
        category = "Gameplay",
        subcategory = "Visual",
        alpha = false
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

    @Color(
        title = "Defusal Helper Color",
        description = "The color of defusal helper redstone highlights.",
        category = "Challenges",
        subcategory = "Gameplay"
    )
    public PolyColor defusalHelperColor = new PolyColor(0xF65E5E);

    /*? if v0 {*//*
    
    @HUD(
        title = "Bedwars XP Display HUD",
        category = "Display"
    )
    public BedwarsXPHud bedwarsXPHud = new BedwarsXPHud();

    @HUD(
        title = "Magic Milk Time HUD",
        category = "Display"
    )
    public MagicMilkTimeHud magicMilkTimeHud = new MagicMilkTimeHud();
    
    *//*?}*/
    
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
