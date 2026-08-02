package at.yedel.dreamersdeluxe.mixins;


import at.yedel.dreamersdeluxe.features.DefusalHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer {
	@Inject(method = "drawSlot", at = @At("HEAD"))
	public void dreamersdeluxe$renderRedstoneHighlights(Slot slotIn, CallbackInfo ci) {
        DefusalHelper.getInstance().renderRedstoneHighlights((GuiContainer) (Object) this, slotIn);
	}
}
