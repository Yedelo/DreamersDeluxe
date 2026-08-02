package at.yedel.dreamersdeluxe.features;





import at.yedel.dreamersdeluxe.DreamersDeluxe;
import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
import at.yedel.dreamersdeluxe.utils.update.UpdateManager;
import at.yedel.dreamersdeluxe.utils.update.UpdateSource;
import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommandGroup;

import java.lang.reflect.Field;


@Command(
    value = "dreamersdeluxe",
    description = "The main command of DreamersDeluxe",
    chatColor = ChatColor.BLUE
)
public class DreamersDeluxeCommand {
    private static final DreamersDeluxeCommand INSTANCE = new DreamersDeluxeCommand();

    public static DreamersDeluxeCommand getInstance() {
        return INSTANCE;
    }

    private DreamersDeluxeCommand() {}

    @Main(
        description = "The main command, hosting all subcommands. When used with no arguments, opens the config screen."
    )
    public void main() {
        DreamersConfig.getInstance().openGui();
    }

    @SubCommand(description = "Shows mod constants and build information such as the project version.")
    public void constants() {
        try {
            UChat.chat(DreamersDeluxeConstants.LOGO + " §eConstants:");
            for (Field field : DreamersDeluxeConstants.class.getDeclaredFields()) {
                UChat.chat(DreamersDeluxeConstants.LOGO + " " + field.getName() + ": §r" + field.get(null));
            }
        }
        catch (IllegalAccessException e) {
            UChat.chat( DreamersDeluxeConstants.LOGO + " §cCouldn't get mod constants!");
            DreamersDeluxe.LOGGER.error("Couldn't get mod constants!", e);
        }
    }

    @SubCommandGroup("update")
    public static class Update {
        @Main
        public void main() {
            UpdateManager.getInstance().checkForUpdates(DreamersConfig.getInstance().getUpdateSource(), UpdateManager.FeedbackMethod.CHAT);
        }

        @SubCommand
        public void modrinth() {
            UpdateManager.getInstance().checkForUpdates(UpdateSource.MODRINTH, UpdateManager.FeedbackMethod.CHAT);
        }

        @SubCommand
        public void github() {
            UpdateManager.getInstance().checkForUpdates(UpdateSource.GITHUB, UpdateManager.FeedbackMethod.CHAT);
        }
    }
}
