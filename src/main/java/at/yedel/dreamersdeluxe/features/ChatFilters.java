package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.libs.universal.wrappers.message.UTextComponent;
import com.google.common.collect.ImmutableList;

import java.util.Objects;
import java.util.regex.Pattern;



public class ChatFilters {
    private static final ChatFilters INSTANCE = new ChatFilters();

    public static ChatFilters getInstance() {
        return INSTANCE;
    }

    private ChatFilters() {}

    private static final Pattern TOKEN_MESSAGE_PATTERN = Pattern.compile("\\+\\d+ tokens! \\(.*\\)");
    private static final Pattern BEDWARS_XP_MESSAGE_PATTERN = Pattern.compile("\\+\\d+ Bed Wars XP \\(.*\\)");
    private static final Pattern PUNCH_DEPOSIT_MESSAGE_PATTERN = Pattern.compile("Deposited x\\d+ (.*) into (Ender|Team) Chest! \\(\\d+ Total\\)");
    private static final Pattern SLUMBER_TICKET_MESSAGE_PATTERN = Pattern.compile("\\+\\d+ Slumber Tickets \\(.*\\)");
    private static final ImmutableList<String> COMFY_PILLOW_MESSAGES = ImmutableList.<String>builder()
        .add("You are now carrying x1 Comfy Pillows, bring it back to your shop keeper!")
        .add("You cannot return items to another team's Shopkeeper!")
        .add("You cannot carry any more Comfy Pillows!")
        .add("You died while carrying x1 Comfy Pillows!")
        .build();

    @Subscribe
    public void modifyBedwarsChat(ChatReceiveEvent event) {
        if (DreamersConfig.getInstance().enabled && ServerLocation.getInstance().isInBedwars()) {
            String message = UTextComponent.Companion.stripFormatting(event.message.getUnformattedText());

            if (DreamersConfig.getInstance().hideTokenMessages && Objects.equals(message, "Tokens just earned DOUBLED as a Guild Level Reward!")) {
                event.isCancelled = true;
            }
            if (TOKEN_MESSAGE_PATTERN.matcher(message).find()) {
                if (DreamersConfig.getInstance().hideTokenMessages) {
                    event.isCancelled = true;
                }
                else if (DreamersConfig.getInstance().lightGreenTokenMessages) {
                    event.message = new UTextComponent(event.message.getFormattedText().replace("§2", "§a"));
                }
            }

            hideOnPattern(event, message, DreamersConfig.getInstance().hideBedwarsXPMessages, BEDWARS_XP_MESSAGE_PATTERN);
            hideOnPattern(event, message, DreamersConfig.getInstance().hidePunchDepositMessages, PUNCH_DEPOSIT_MESSAGE_PATTERN);
            hideOnPattern(event, message, DreamersConfig.getInstance().hideSlumberTicketMessages, SLUMBER_TICKET_MESSAGE_PATTERN);

            if (message.startsWith("You purchased")) {
                if (DreamersConfig.getInstance().hideItemPurchaseMessages) {
                    event.isCancelled = true;
                }
                else if (DreamersConfig.getInstance().hideSilverCoinCount && message.contains("(+1 Silver Coin [")) {
                    event.message = new UTextComponent(message.substring(0, message.indexOf(" (+1 Silver Coin [")));
                }
            }

            if (DreamersConfig.getInstance().hideComfyPillowMessages && COMFY_PILLOW_MESSAGES.contains(message)) {
                event.isCancelled = true;
            }

            if (DreamersConfig.getInstance().hideDreamerSoulFragmentMessages && message.equals("+1 Dreamer's Soul Fragment!")) {
                event.isCancelled = true;
            }
        }
    }

    private void hideOnPattern(ChatReceiveEvent event, String message, boolean configOption, Pattern pattern) {
        if (configOption && pattern.matcher(message).find()) {
            event.isCancelled = true;
        }
    }
}
