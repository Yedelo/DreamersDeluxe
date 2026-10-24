package at.yedel.dreamersdeluxe.hud;



import org.polyfrost.oneconfig.api.hud.v1.HudManager;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;
    /*? if legacy {*//*
import net.minecraft.client.entity.EntityPlayerSP;
    *//*?} else {*/
import net.minecraft.client.player.LocalPlayer;
/*?}*/
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import net.minecraft.client.Minecraft;




//~ texthud_bridge
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
            int xp = (int) (experience() * 5000);
            return "§b" + commafy(xp) + "§7/§a5,000";
        }
    }

    private String commafy(int number) {
        return String.format("%,d", number);
    }

    private float experience() {
        //? if legacy {
//        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        //?} else {
        LocalPlayer player = Minecraft.getInstance().player;
        //?}
        if (player == null) return 0;
        //~ if modern 'experience' -> 'experienceProgress'
        return player.experienceProgress;
    }

    @Override
    public boolean shouldShow() {
        return ServerLocation.getInstance().isInBedwars() && experience() > 0;
    }
}
