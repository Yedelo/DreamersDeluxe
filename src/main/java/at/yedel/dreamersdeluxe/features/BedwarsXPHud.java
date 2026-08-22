package at.yedel.dreamersdeluxe.features;



    /*? if v0 {*//*
import at.yedel.dreamersdeluxe.utils.Constants;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
    *//*?} else {*/
import org.polyfrost.oneconfig.api.hud.v1.HudManager;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;
/*?}*/
    /*? if forge {*//*
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
    *//*?}*/
    /*? if legacy {*//*
import net.minecraft.client.entity.EntityPlayerSP;
    *//*?} else {*/
import net.minecraft.client.player.LocalPlayer;
/*?}*/
import net.minecraft.client.Minecraft;




//~ texthud_bridge
public class BedwarsXPHud extends TextHud {
    public BedwarsXPHud() {
        /*? if v0 {*//*
        super(
            "XP", // title is actually useful now
            true, // enabled obviously
            5, // x
            15, // y
            1, // normal size
            false, // no background it's ugly
            false, // no rounded corners it's also ugly
            0, // NO rounded corners
            0, // no x padding why would i want it
            0, // no y padding for the same reason
            Constants.EMPTY_COLOR, // no background color
            false, // no border
            0, // NO border
            Constants.EMPTY_COLOR // no border color
        );
        textType = 1;
        *//*?} else {*/
        super("bedwars_xp_hud", "Bedwars XP Hud", Category.getINFO(), "XP:", "");
        /*?}*/
    }

    @Override
    protected String getText() {
        if (!isReal() || HudManager.INSTANCE.isEditing()) {
            return "§b3,550§7/§a5,000";
        }
        else {
            /*? if legacy {*//*
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            *//*?} else {*/
            LocalPlayer player = Minecraft.getInstance().player;
            /*?}*/
            if (player == null) return "";
            //~ if modern 'experience' -> 'experienceProgress'
            float progress = player.experienceProgress;
            int xp = (int) (progress * 5000);
            return "§b" + commafy(xp) + "§7/§a5,000";
        }
    }

    private String commafy(int number) {
        return String.format("%,d", number);
    }

    /*? if v0 {*//*
    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && ServerLocation.getInstance().isInBedwars() && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.experience > 0;
    }
    *//*?}*/
}
