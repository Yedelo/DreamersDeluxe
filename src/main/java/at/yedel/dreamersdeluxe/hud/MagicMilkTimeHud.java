package at.yedel.dreamersdeluxe.hud;




import org.polyfrost.oneconfig.api.hud.v1.HudManager;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;
/*? if legacy {*//*
import net.minecraft.init.Items;
*//*?}*/
import at.yedel.dreamersdeluxe.utils.ServerLocation;



//~ texthud_bridge
public class MagicMilkTimeHud extends TextHud {
    public MagicMilkTimeHud() {
        super("magic_milk_time_hud", "Magic Milk Time HUD", Category.getINFO(), "Magic Milk:", "");
    }

    private transient long milkDrinkTime;

    public static void handleMilk() {
        if (ServerLocation.getInstance().isInBedwars()) {
            HudManager.INSTANCE.getHudsOfType(MagicMilkTimeHud.class).forEach((hud) -> hud.milkDrinkTime = System.nanoTime());
        }
    }

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

    @Override
    public boolean shouldShow() {
        return ServerLocation.getInstance().isInBedwars() && getTimeRemaining() > 0;
    }
}
