package at.yedel.dreamersdeluxe;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.features.*;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;

import net.fabricmc.api.ClientModInitializer;
import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.packet.impl.clientbound.event.ClientboundLocationPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.polyfrost.oneconfig.api.commands.v1.CommandManager;
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.polyfrost.oneconfig.api.hud.v1.HudManager;



public class DreamersDeluxe implements ClientModInitializer {
	private static DreamersDeluxe INSTANCE;

	public static DreamersDeluxe getInstance() {
		return INSTANCE;
	}

	public DreamersDeluxe() {
		INSTANCE = this;
	}

	public static final Logger LOGGER = LogManager.getLogger("DreamersDeluxe");

	public void onInitializeClient() {
		// Loads class. preload() exists for this but what ev
		DreamersConfig.getInstance();
		ServerLocation.getInstance();
		EventManager.INSTANCE.register(ChatFilters.getInstance());
        HudManager.register(new BedwarsXPHud(), new MagicMilkTimeHud());
		CommandManager.register(DreamersDeluxeCommand.getInstance());
	}
}
