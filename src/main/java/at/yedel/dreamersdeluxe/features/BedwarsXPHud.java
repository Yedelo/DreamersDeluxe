package at.yedel.dreamersdeluxe.features;




import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.polyfrost.oneconfig.api.hud.v1.HudManager;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;



//@TODO hide this when not in bedwars
//@TODO color does not work properly
public class BedwarsXPHud extends TextHud {
    public BedwarsXPHud() {
        super("bedwars_xp_hud", "Bedwars XP Hud", Category.getINFO(), "XP:", "");
    }

    @Override
    protected String getText() {
        if (!isReal() || HudManager.INSTANCE.isEditing()) {
            return "§b3,550§7/§a5,000";
        }
        else {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return "";
            float progress = player.experienceProgress;
            int xp = (int) (progress * 5000);
            return "§b" + commafy(xp) + "§7/§a5,000";
        }
    }

    private String commafy(int number) {
        return String.format("%,d", number);
    }

//    @Override
//    public boolean getHidden() {
//        return super.getHidden() || !ServerLocation.getInstance().isInBedwars() || !hasExperience;
//    }
}
