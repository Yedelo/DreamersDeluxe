package at.yedel.dreamersdeluxe.features;



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
        HypixelModAPI.getInstance().createHandler(ClientboundLocationPacket.class, this::handleLocationPacket);
    }

    private void handleLocationPacket(ClientboundLocationPacket packet) {
        inBedwars = packet.getServerType().isPresent() && packet.getServerType().get() == GameType.BEDWARS && !packet.getLobbyName().isPresent();
    }

    public boolean isInBedwars() {
        //@TODO make this return inBedwars when oneconfig fixes hypixel mod api
        return true;
    }
}
