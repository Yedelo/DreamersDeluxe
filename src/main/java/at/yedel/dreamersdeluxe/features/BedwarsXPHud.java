package at.yedel.dreamersdeluxe.features;




import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import org.polyfrost.oneconfig.api.event.v1.events.PacketEvent;
import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe;
import org.polyfrost.oneconfig.api.hud.v1.TextHud;



public class BedwarsXPHud extends TextHud {
    private static final BedwarsXPHud INSTANCE = new BedwarsXPHud();

    public static BedwarsXPHud getInstance() {
        return INSTANCE;
    }

    private BedwarsXPHud() {
        super("bedwars_xp_hud", "Bedwars XP Hud", Category.getINFO(), "XP:", "");
    }

    private boolean hasExperience;
    private int bedwarsXP;

    @Subscribe
    public void setBedwarsExperience(PacketEvent.Receive event) {
        if (event.getPacket() instanceof ClientboundSetExperiencePacket packet) {
            float experience = packet.getExperienceProgress();
            hasExperience = experience > 0;
            bedwarsXP = (int) (experience * 5000);
        }
    }

    @Override
    protected String getText() {
        if (!isReal()) {
            return "§b3,550§7/§a5,000";
        }
        else {
            return "§b" + commafy(bedwarsXP) + "§7/§a5,000";
        }
    }

    private String commafy(int number) {
        return String.format("%,d", number);
    }

    @Override
    public boolean getHidden() {
        return super.getHidden() || !ServerLocation.getInstance().isInBedwars() || !hasExperience;
    }
}
