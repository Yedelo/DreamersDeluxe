package at.yedel.dreamersdeluxe.features;




import org.polyfrost.oneconfig.api.hud.v1.TextHud;



//@TODO hide this when not in bedwars or time remaining is below 0 seconds
//@TODO color does not work properly
public class MagicMilkTimeHud extends TextHud {
    public MagicMilkTimeHud() {
        super("magic_milk_time_hud", "Magic Milk Time HUD", Category.getINFO(), "Magic Milk:", "");
    }

    private long milkDrinkTime;

    public void handleMilk() {
        if (ServerLocation.getInstance().isInBedwars()) {
            milkDrinkTime = System.nanoTime();
        }
    }

    @Override
    public String getText() {
        if (!isReal()) return "§b25§as";
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

    //    @Override
    //    public boolean getHidden() {
    //        return super.getHidden() || !ServerLocation.getInstance().isInBedwars() || magicMilkTime <= -1;
    //    }
}
