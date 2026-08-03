package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.utils.Constants;
import cc.polyfrost.oneconfig.events.event.Stage;
import cc.polyfrost.oneconfig.events.event.TickEvent;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class MagicMilkTimeHud extends SingleTextHud {
    private transient int magicMilkTime;
    private transient int ticks;

    public MagicMilkTimeHud() {
        super(
            "Magic Milk", // title is actually useful now
            true, // enabled obviously
            5, // x
            25, // y
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

    @SubscribeEvent
    public void resetMagicMilkTime(PlayerUseItemEvent.Finish event) {
        if (event.item.getItem() == Items.milk_bucket && ServerLocation.getInstance().isInBedwars()) {
            magicMilkTime = 30;
        }
    }

    @Subscribe
    public void decrementMagicMilkTime(TickEvent event) {
        if (event.stage == Stage.START) {
            if (ticks % 20 == 0) {
                magicMilkTime --;
            }
            ticks ++;
        }
    }

    @Override
    public boolean shouldShow() {
        return super.shouldShow() && ServerLocation.getInstance().isInBedwars() && magicMilkTime > -1;
    }

    @Override
    protected String getText(boolean example) {
        if (example) return "§b25§as";
        else {
            return "§b" + magicMilkTime + "§as";
        }
    }
}
