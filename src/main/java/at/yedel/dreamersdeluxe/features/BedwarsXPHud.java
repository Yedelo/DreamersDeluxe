package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.utils.Constants;
import at.yedel.dreamersdeluxe.utils.TextUtils;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.network.play.server.S1FPacketSetExperience;



public class BedwarsXPHud extends SingleTextHud {
    private boolean hasExperience;
    private int bedwarsXP;

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

    @Subscribe
    public void setBedwarsExperience(ReceivePacketEvent event) {
        if (event.packet instanceof S1FPacketSetExperience) {
            float experience = ((S1FPacketSetExperience) event.packet).func_149397_c();
            hasExperience = experience > 0;
            bedwarsXP = (int) (experience * 5000);
        }
    }

    @Override
    protected String getText(boolean example) {
        if (example) {
            return "§b3,550§7/§a5,000";
        }
        else {
            return "§b" + TextUtils.commafy(bedwarsXP) + "§7/§a5,000";
        }
    }

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && ServerLocation.getInstance().isInBedwars() && hasExperience;
    }
}
