package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.mixins.AccessorGuiChest;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.libs.universal.UGraphics;
import cc.polyfrost.oneconfig.platform.Platform;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Objects;



public class DefusalHelper {
    private static final DefusalHelper INSTANCE = new DefusalHelper();

    public static DefusalHelper getInstance() {
        return INSTANCE;
    }

    private DefusalHelper() {}

    private static final int RED = new OneColor(246, 94, 94, 255).getRGB();

    public void renderRedstoneHighlights(GuiContainer container, Slot slot) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper) {
            ItemStack stack = slot.getStack();
            if (stack == null) return;
            if (stack.getItem() != Items.redstone) return;
            if (!(container instanceof GuiChest)) return;
            if (!Objects.equals(((AccessorGuiChest) container).getLowerChestInventory().getName(), "§cC4 (Click §4§lREDSTONE§c)")) return;
            UGraphics.GL.translate(0, 0, 1);
            Platform.getGLPlatform().drawRect(slot.xDisplayPosition, slot.yDisplayPosition, slot.xDisplayPosition + 16, slot.yDisplayPosition + 16, RED);
        }
    }
}
