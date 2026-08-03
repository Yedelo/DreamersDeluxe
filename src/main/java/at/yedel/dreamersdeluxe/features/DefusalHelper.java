package at.yedel.dreamersdeluxe.features;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;



public class DefusalHelper {
    private static final DefusalHelper INSTANCE = new DefusalHelper();

    public static DefusalHelper getInstance() {
        return INSTANCE;
    }

    private DefusalHelper() {}

//    private static final int RED = new OneColor(246, 94, 94, 255).getRGB();
//
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
//
//    private void highlightItem(Slot slot, int color) {
//        UGraphics.GL.translate(0, 0, 1);
//        Platform.getGLPlatform().drawRect(slot.xDisplayPosition, slot.yDisplayPosition, slot.xDisplayPosition + 16, slot.yDisplayPosition + 16, color);
//    }
}
