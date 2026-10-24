package at.yedel.dreamersdeluxe.config;






//? if modern {
import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;
import net.kyori.adventure.text.TextReplacementConfig;
import net.minecraft.network.chat.Component;
//?}
import org.polyfrost.compose.render.PolyColor;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.*;
import org.polyfrost.oneconfig.api.notifications.v1.Notifications;


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
        super("dreamersdeluxe", "/assets/dreamersdeluxe/dreamersdeluxe.png", "DreamersDeluxe", Category.HYPIXEL);
        addDependency("defusalHelperColor", "bedwarsDefusalHelper");
        for (String color: new String[] { "red", "blue", "green", "yellow", "aqua", "white", "pink", "gray" }) {
            addDependency(color + "Color", "customArmorColors");
        }
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
    public boolean debugLocationFlag = false;

    @Switch(
        title = "Custom Armor Colors",
        description = "Changes the color of leather armor for different teams.",
        category = "Gameplay",
        subcategory = "Visual"
    )
    public boolean customArmorColors = false;

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
    public PolyColor defusalHelperColor = new PolyColor(0xFFF65E5E);
    
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

    @Switch(
        title = "Replace Team Names with Verity Variants",
        category = "Fun",
        subcategory = "Verity"
    )
    public boolean replaceTeamNamesWithVerityVariants = false;

    @Text(
        title = "Red Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String redTeamVariant = "Cruelty";

    @Text(
        title = "Blue Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String blueTeamVariant = "Falsity";

    @Text(
        title = "Green Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String greenTeamVariant = "Curiosity";

    @Text(
        title = "Yellow Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String yellowTeamVariant = "Verity";

    // i made Hydrity
    @Text(
        title = "Aqua Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String aquaTeamVariant = "Hydrity";

    @Text(
        title = "White Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String whiteTeamVariant = "Angelity";

    @Text(
        title = "Pink Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String pinkTeamVariant = "Lovity";

    @Text(
        title = "Gray Team Variant",
        category = "Fun",
        subcategory = "Verity",
        placeholder = "Verity goes here..."
    )
    public String grayTeamVariant = "Obscurity";

    public String getVerityTextAsString(String text) {
        return text
            .replace("Red", redTeamVariant)
            .replace("Blue", blueTeamVariant)
            .replace("Green", greenTeamVariant)
            .replace("Yellow", yellowTeamVariant)
            .replace("Aqua", aquaTeamVariant)
            .replace("White", whiteTeamVariant)
            .replace("Pink", pinkTeamVariant)
            .replace("Gray", grayTeamVariant);
    }

    //? if modern {
    public Component getVerityTextAsComponent(Component component) {
        return MinecraftClientAudiences.of().asNative(
            MinecraftClientAudiences.of().asAdventure(component)
            .replaceText(TextReplacementConfig.builder().matchLiteral("Red").replacement(redTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("Blue").replacement(blueTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("Green").replacement(greenTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("Yellow").replacement(yellowTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("Aqua").replacement(aquaTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("White").replacement(whiteTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("Pink").replacement(pinkTeamVariant).build())
            .replaceText(TextReplacementConfig.builder().matchLiteral("Gray").replacement(grayTeamVariant).build())
        );
    }
    //?}
}
