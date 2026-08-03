package at.yedel.dreamersdeluxe.features;





import at.yedel.dreamersdeluxe.DreamersDeluxe;
import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Command;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler;
import org.polyfrost.oneconfig.api.platform.v1.Platform;
import org.polyfrost.oneconfig.utils.v1.dsl.ScreensKt;

import java.lang.reflect.Field;


@Command(
    value = "dreamersdeluxe",
    description = "The main command of DreamersDeluxe"
)
public class DreamersDeluxeCommand {
    private static final DreamersDeluxeCommand INSTANCE = new DreamersDeluxeCommand();

    public static DreamersDeluxeCommand getInstance() {
        return INSTANCE;
    }

    private DreamersDeluxeCommand() {}

    @Handler(
        description = "The main command, hosting all subcommands. When used with no arguments, opens the config screen."
    )
    public void main() {
        ScreensKt.openUI(DreamersConfig.getInstance());
    }

    @Handler(description = "Shows mod constants and build information such as the project version.")
    public void constants() {
        try {
            Platform.compatibility().displayChatMessage(DreamersDeluxeConstants.LOGO + " §eConstants:");
            for (Field field : DreamersDeluxeConstants.class.getDeclaredFields()) {
                Platform.compatibility().displayChatMessage(DreamersDeluxeConstants.LOGO + " " + field.getName() + ": §r" + field.get(null));
            }
        }
        catch (IllegalAccessException e) {
            Platform.compatibility().displayChatMessage(DreamersDeluxeConstants.LOGO + " §cCouldn't get mod constants!");
            DreamersDeluxe.LOGGER.error("Couldn't get mod constants!", e);
        }
    }
}
