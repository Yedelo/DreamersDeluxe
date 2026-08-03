package at.yedel.dreamersdeluxe.features;



import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.polyfrost.oneconfig.api.event.v1.events.TickEvent;
import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;



public class MagicMilkTimeHud extends TextHud {
    private static final MagicMilkTimeHud INSTANCE = new MagicMilkTimeHud();

    public static MagicMilkTimeHud getInstance() {
        return INSTANCE;
    }

    public MagicMilkTimeHud() {
        super("magic_milk_time_hud", "Magic Milk Time HUD", Category.getINFO(), "Magic Milk:", "");
    }

    private int magicMilkTime;
    private int ticks;

    public void handleMilk(Item item) {
        if (ServerLocation.getInstance().isInBedwars() && item == Items.MILK_BUCKET) {
            magicMilkTime = 30;
        }
    }

    // @TODO this goes too fast
    @Subscribe
    public void decrementMagicMilkTime(TickEvent.Start event) {
        if (ticks % 20 == 0) {
            magicMilkTime--;
        }
        ticks ++;
    }

    @Override
    public String getText() {
        if (!isReal()) return "§b25§as";
        else {
            return "§b" + magicMilkTime + "§as";
        }
    }

    //    @Override
    //    public boolean getHidden() {
    //        return super.getHidden() || !ServerLocation.getInstance().isInBedwars() || magicMilkTime <= -1;
    //    }
}
