package at.yedel.dreamersdeluxe;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
import at.yedel.dreamersdeluxe.features.*;
import at.yedel.dreamersdeluxe.hud.BedwarsXPHud;
import at.yedel.dreamersdeluxe.hud.MagicMilkTimeHud;
import at.yedel.dreamersdeluxe.utils.ServerLocation;import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*? if forge {*//*
import at.yedel.dreamersdeluxe.utils.update.UpdateManager;
import at.yedel.dreamersdeluxe.utils.update.UpdateManager.FeedbackMethod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;

*//*?}*/
/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
/*?}*/
/*? if v0 {*//*
import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
*//*?} else {*/
import org.polyfrost.oneconfig.api.commands.v1.CommandManager;
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.polyfrost.oneconfig.api.hud.v1.HudManager;
/*?}*/


// Mod...
/*? if forge {*//*
@Mod(
	modid = DreamersDeluxeConstants.MOD_ID,
	name = DreamersDeluxeConstants.MOD_NAME,
	version = DreamersDeluxeConstants.MOD_VERSION,
	clientSideOnly = true
)
*//*?}*/
public class DreamersDeluxe /*? if fabric {*/ implements ClientModInitializer /*?}*/ {
	public static final Logger LOGGER = LogManager.getLogger("DreamersDeluxe");

	private static DreamersDeluxe INSTANCE;

	public static DreamersDeluxe getInstance() {
		return INSTANCE;
	}

	public DreamersDeluxe() {
		INSTANCE = this;
	}

	private void initialize() {
		// Loads class. preload() exists for this but what ev
		DreamersConfig.getInstance();
		ServerLocation.getInstance();
		/*? if v0 {*//*
		registerEventListeners(
			this,
			ChatFilters.getInstance(),
			DreamersConfig.getInstance().magicMilkTimeHud
		);
		CommandManager.INSTANCE.registerCommand(DreamersDeluxeCommand.getInstance());
		*//*?} else {*/
		
		EventManager.INSTANCE.register(ChatFilters.getInstance());
		HudManager.register(new BedwarsXPHud(), new MagicMilkTimeHud());
		CommandManager.register(DreamersDeluxeCommand.getInstance());
		/*?}*/
	}

	/*? if forge {*//*
	private final UpdateManager updateManager = new UpdateManager(
		"DreamersDeluxe", DreamersDeluxeConstants.MOD_VERSION, "dreamersdeluxe", "Yedelo/DreamersDeluxe", DreamersDeluxeConstants.LOGO
	);

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		initialize();
	}

	@Mod.EventHandler
	public void checkForUpdates(FMLLoadCompleteEvent event) {
		if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().automaticallyCheckForUpdates) {
			updateManager.checkForUpdates(DreamersConfig.getInstance().getUpdateSource(), FeedbackMethod.NOTIFICATIONS);
		}
	}

	private void registerEventListeners(Object... eventListeners) {
		for (Object eventListener: eventListeners) {
			MinecraftForge.EVENT_BUS.register(eventListener);
			EventManager.INSTANCE.register(eventListener);
		}
	}

	public UpdateManager getUpdateManager() {
		return updateManager;
	}
	*//*?} else {*/
	public void onInitializeClient() {
		initialize();
	}
	/*?}*/
}


