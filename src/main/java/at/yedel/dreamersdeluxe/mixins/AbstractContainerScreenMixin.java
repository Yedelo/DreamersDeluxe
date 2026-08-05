package at.yedel.dreamersdeluxe.mixins;



import at.yedel.dreamersdeluxe.features.DefusalHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> {
    @Inject(method = "extractSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;item(Lnet/minecraft/world/item/ItemStack;III)V"))
    private void dreamersdeluxe$handleSlotRendering(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY, CallbackInfo ci) {
        DefusalHelper.getInstance().extractRedstoneHighlight(graphics, (AbstractContainerScreen<ChestMenu>) (Object) this, slot);
    }
}
