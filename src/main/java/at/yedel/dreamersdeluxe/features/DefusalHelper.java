package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.mixins.AbstractContainerScreenAccessor;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;



public class DefusalHelper {
    private static final DefusalHelper INSTANCE = new DefusalHelper();

    public static DefusalHelper getInstance() {
        return INSTANCE;
    }

    private DefusalHelper() {}

    private static final int RED = -631202;

//    public void renderRedstoneHighlights(GuiContainer container, Slot slot) {
//        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().defusalHelper) {
//            ItemStack stack = slot.getStack();
//            if (stack == null) {
//                return;
//            }
//            if (stack.getItem() == Items.redstone) {
//                if (container instanceof GuiChest) {
//                    if (Objects.equals(((AccessorGuiChest) container).getLowerChestInventory().getName(), "§cC4 (Click §4§lREDSTONE§c)")) {
//                        highlightItem(slot, RED);
//                    }
//                }
//            }
//        }
//    }

    /**
     * Taken from Skyblocker under the GNU LGPL v3
     * <a href="https://github.com/SkyblockerMod/Skyblocker/blob/b23069c5a832deaaa0fca506aed234703f53334a/src/main/java/de/hysky/skyblocker/utils/container/ContainerSolverManager.java#L155">de.hysky.skyblocker.utils.container.ContainerSolverManager</a>
     */
    public static void extractRedstoneHighlights(GuiGraphicsExtractor context, AbstractContainerScreen<ChestMenu> handledScreen, List<Slot> slots) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper) {
            context.pose().pushMatrix();
            context.pose().translate(((AbstractContainerScreenAccessor) handledScreen).getX(), ((AbstractContainerScreenAccessor) handledScreen).getY());
            for (Slot slot: slots) {
                ItemStack stack = slot.getItem();
                if (stack == null) return;
                if (stack.getItem() != Items.REDSTONE) return;
                // if (!Objects.equals(((AccessorGuiChest) container).getLowerChestInventory().getName(), "§cC4 (Click §4§lREDSTONE§c)")) return;
                context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, RED);
            }
            context.pose().popMatrix();
        }
    }
}
