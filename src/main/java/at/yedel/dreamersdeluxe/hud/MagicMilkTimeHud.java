package at.yedel.dreamersdeluxe.hud;




/*? if v0 {*//*
import at.yedel.dreamersdeluxe.utils.Constants;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
*//*?} else {*/
import org.polyfrost.oneconfig.api.hud.v1.HudManager;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;
/*?}*/
/*? if forge {*//*
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
*//*?}*/
/*? if legacy {*//*
import net.minecraft.init.Items;
*//*?}*/
import at.yedel.dreamersdeluxe.utils.ServerLocation;



//~ texthud_bridge
public class MagicMilkTimeHud extends TextHud {
    public MagicMilkTimeHud() {
        /*? if v0 {*//*
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
        *//*?} else {*/
        super("magic_milk_time_hud", "Magic Milk Time HUD", Category.getINFO(), "Magic Milk:", "");
        /*?}*/
    }

    private transient long milkDrinkTime;

    public void handleMilk() {
        if (ServerLocation.getInstance().isInBedwars()) {
            milkDrinkTime = System.nanoTime();
        }
    }

    /*? if forge {*//*
    @SubscribeEvent
    public void onItemUse(PlayerUseItemEvent.Finish event) {
        if (event.item.getItem() == Items.milk_bucket) {
            handleMilk();
        }
    }
    *//*?}*/

    @Override
    protected String getText() {
        if (!isReal() || HudManager.INSTANCE.isEditing()) return "§b25§as";
        else {
            return "§b" + getTimeRemaining() + "§as";
        }
    }

    private double getTimeRemaining() {
        return round(30 - (System.nanoTime() - milkDrinkTime) / 1_000_000_000D, 2);
    }

    // https://stackoverflow.com/a/22186845
    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /*? if v0 {*//*
    @Override
    public boolean shouldShow() {
        return super.shouldShow() && ServerLocation.getInstance().isInBedwars() && getTimeRemaining() > 0;
    }
    *//*?}*/
}
