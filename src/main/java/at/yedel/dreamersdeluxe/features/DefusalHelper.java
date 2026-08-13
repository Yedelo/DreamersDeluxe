package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.polyfrost.oneconfig.api.platform.v1.Platform;

import java.util.Objects;



public class DefusalHelper {
    private static final DefusalHelper INSTANCE = new DefusalHelper();

    public static DefusalHelper getInstance() {
        return INSTANCE;
    }

    private DefusalHelper() {}

    private final String DEFUSAL_WINDOW_TITLE = "C4 (Click REDSTONE)";

    public void extractRedstoneHighlight(GuiGraphicsExtractor context, AbstractContainerScreen<ChestMenu> handledScreen, Slot slot) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper && ServerLocation.getInstance().isInBedwars()) {
            ItemStack stack = slot.getItem();
            if (stack == null) return;
            if (stack.getItem() != Items.REDSTONE) return;
            if (!Objects.equals(ChatFormatting.stripFormatting(handledScreen.getTitle().getString()), DEFUSAL_WINDOW_TITLE)) return;
            context.pose().pushMatrix();
            context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, DreamersConfig.getInstance().defusalHelperColor);
            context.pose().popMatrix();
        }
    }
}
