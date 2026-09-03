package at.yedel.dreamersdeluxe.features;





import at.yedel.dreamersdeluxe.DreamersDeluxe;
import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
/*? if forge {*//*
import at.yedel.dreamersdeluxe.utils.update.UpdateManager;
import at.yedel.dreamersdeluxe.utils.update.UpdateSource;
*//*?}*/
/*? if v0 {*//*
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommandGroup;
*//*?} else {*/
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Command;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler;
import org.polyfrost.oneconfig.api.platform.v1.Platform;
import org.polyfrost.oneconfig.utils.v1.dsl.ScreensKt;
/*?}*/

import java.lang.reflect.Field;



@Command(
    value = "dreamersdeluxe",
    description = "The main command of DreamersDeluxe"
)
//~ command_bridge
public class DreamersDeluxeCommand {
    private static final DreamersDeluxeCommand INSTANCE = new DreamersDeluxeCommand();

    public static DreamersDeluxeCommand getInstance() {
        return INSTANCE;
    }

    private DreamersDeluxeCommand() {}

    //~ if v1 '@Main' -> '@org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler'
    @org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler(
        description = "The main command, hosting all subcommands. When used with no arguments, opens the config screen."
    )
    public void main() {
        /*? if v0 {*//*
        DreamersConfig.getInstance().openGui();
        *//*?} else {*/
        ScreensKt.openUI(DreamersConfig.getInstance());
        /*?}*/
    }

    //~ if v1 '@SubCommand' -> '@Handler' {
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
    //~}

    /*? if forge {*//*
    @SubCommandGroup("update")
    public static class Update {
        @SubCommand
        public void main() {
            DreamersDeluxe.getInstance().getUpdateManager().checkForUpdates(DreamersConfig.getInstance().getUpdateSource(), UpdateManager.FeedbackMethod.CHAT);
        }

        @SubCommand
        public void modrinth() {
            DreamersDeluxe.getInstance().getUpdateManager().checkForUpdates(UpdateSource.MODRINTH, UpdateManager.FeedbackMethod.CHAT);
        }

        @SubCommand
        public void github() {
            DreamersDeluxe.getInstance().getUpdateManager().checkForUpdates(UpdateSource.GITHUB, UpdateManager.FeedbackMethod.CHAT);
        }
    }
    *//*?}*/
}
