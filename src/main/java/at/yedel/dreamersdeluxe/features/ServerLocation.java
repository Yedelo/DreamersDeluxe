package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import net.hypixel.data.type.GameType;
import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.packet.impl.clientbound.event.ClientboundLocationPacket;



public class ServerLocation {
    private static final ServerLocation INSTANCE = new ServerLocation();

    public static ServerLocation getInstance() {
        return INSTANCE;
    }

    private boolean inBedwars;

    private ServerLocation() {
        HypixelModAPI.getInstance().registerHandler(ClientboundLocationPacket.class, this::handleLocationPacket);
    }

    private void handleLocationPacket(ClientboundLocationPacket packet) {
        inBedwars = packet.getServerType().isPresent() && packet.getServerType().get() == GameType.BEDWARS && !packet.getLobbyName().isPresent();
    }

    public boolean isInBedwars() {
        return DreamersConfig.getInstance().debugLocationFlag || inBedwars;
    }
}
