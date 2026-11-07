package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import net.kyori.adventure.text.TextReplacementConfig;
import org.polyfrost.oneconfig.api.event.v1.events.ChatEvent;
import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe;



public class VerityVariants {
    private static final VerityVariants INSTANCE = new VerityVariants();

    public static VerityVariants getInstance() {
        return INSTANCE;
    }

    private VerityVariants() {}

    private final TextReplacementConfig REPLACEMENT = TextReplacementConfig.builder()
        .match("^(Red|Blue|Green|Yellow|Aqua|White|Pink|Gray) ")
        .replacement((result, builder) -> builder.content(DreamersConfig.getInstance().getVerityTextAsString(result.group()))).build();

    @Subscribe
    public void replaceChatNamesWithVerityVariants(ChatEvent.Receive event) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().replaceTeamNamesWithVerityVariants && ServerLocation.getInstance().isInBedwars()) {
            event.setMessage(event.getMessage().replaceText(REPLACEMENT));
        }
    }
}
