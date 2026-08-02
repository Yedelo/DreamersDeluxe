package at.yedel.dreamersdeluxe;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.features.ChatFilters;
import at.yedel.dreamersdeluxe.features.DefusalHelper;
import at.yedel.dreamersdeluxe.features.DreamersDeluxeCommand;
import at.yedel.dreamersdeluxe.features.ServerLocation;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
import at.yedel.dreamersdeluxe.utils.ClickNotifications;
import at.yedel.dreamersdeluxe.utils.update.UpdateManager;
import at.yedel.dreamersdeluxe.utils.update.UpdateManager.FeedbackMethod;
import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.packet.impl.clientbound.event.ClientboundLocationPacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// Mod...
@Mod(
	modid = DreamersDeluxeConstants.MOD_ID,
	name = DreamersDeluxeConstants.MOD_NAME,
	version = DreamersDeluxeConstants.MOD_VERSION,
	clientSideOnly = true
)
public class DreamersDeluxe {
	@Instance
	private static DreamersDeluxe INSTANCE;

	public static DreamersDeluxe getInstance() {
		return INSTANCE;
	}

	public static final Logger LOGGER = LogManager.getLogger("DreamersDeluxe");

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Loads class. preload() exists for this but what ev
		DreamersConfig.getInstance();
		HypixelModAPI.getInstance().subscribeToEventPacket(ClientboundLocationPacket.class);
		CommandManager.INSTANCE.registerCommand(DreamersDeluxeCommand.getInstance());

		ServerLocation.getInstance();
		registerEventListeners(
			this,
			ClickNotifications.getInstance(),

			ChatFilters.getInstance(),
			DefusalHelper.getInstance(),
			DreamersConfig.getInstance().bedwarsXPHud,
			DreamersConfig.getInstance().magicMilkTimeHud
		);
	}

	@EventHandler
	public void checkForUpdates(FMLLoadCompleteEvent event) {
		if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().automaticallyCheckForUpdates) {
			UpdateManager.getInstance().checkForUpdates(DreamersConfig.getInstance().getUpdateSource(), FeedbackMethod.NOTIFICATIONS);
		}
	}

	private void registerEventListeners(Object... eventListeners) {
		for (Object eventListener: eventListeners) {
			MinecraftForge.EVENT_BUS.register(eventListener);
			EventManager.INSTANCE.register(eventListener);
		}
	}
}
