package at.yedel.dreamersdeluxe;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
import at.yedel.dreamersdeluxe.features.*;
import at.yedel.dreamersdeluxe.hud.BedwarsXPHud;
import at.yedel.dreamersdeluxe.hud.MagicMilkTimeHud;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.fabricmc.api.ClientModInitializer;
import org.polyfrost.oneconfig.api.commands.v1.CommandManager;
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.polyfrost.oneconfig.api.hud.v1.HudManager;


// Mod...
public class DreamersDeluxe implements ClientModInitializer{
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
		
		EventManager.INSTANCE.register(ChatFilters.getInstance());
		HudManager.register(new BedwarsXPHud(), new MagicMilkTimeHud());
		CommandManager.register(DreamersDeluxeCommand.getInstance());
	}

	public void onInitializeClient() {
		initialize();
	}
}


