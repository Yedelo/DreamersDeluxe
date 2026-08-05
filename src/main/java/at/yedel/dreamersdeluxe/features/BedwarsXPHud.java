package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.utils.Constants;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;



public class BedwarsXPHud extends SingleTextHud {
    public BedwarsXPHud() {
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
    }

    @Override
    protected String getText(boolean example) {
        if (example) {
            return "§b3,550§7/§a5,000";
        }
        else {
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            if (player == null) return "";
            float progress = player.experience;
            int xp = (int) (progress * 5000);
            return "§b" + commafy(xp) + "§7/§a5,000";
        }
    }

    private String commafy(int number) {
        return String.format("%,d", number);
    }

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && ServerLocation.getInstance().isInBedwars() && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.experience > 0;
    }
}
