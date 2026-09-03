/*? if fabric {*/
package at.yedel.dreamersdeluxe.mixins.modern;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> {
    @Inject(method = "extractSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;item(Lnet/minecraft/world/item/ItemStack;III)V"))
    private void dreamersdeluxe$handleSlotRendering(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY, CallbackInfo ci) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper && ServerLocation.getInstance().isInBedwars()) {
            ItemStack stack = slot.getItem();
            if (stack == null) return;
            if (stack.getItem() != Items.REDSTONE) return;
            graphics.pose().pushMatrix();
            graphics.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, DreamersConfig.getInstance().defusalHelperColor.getArgb());
            graphics.pose().popMatrix();
        }
    }
}
/*?}*/