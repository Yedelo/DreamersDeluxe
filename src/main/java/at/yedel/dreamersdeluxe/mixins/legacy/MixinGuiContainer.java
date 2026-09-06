/*? if legacy {*//*
package at.yedel.dreamersdeluxe.mixins.legacy;


import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer {
	@Inject(method = "drawSlot", at = @At("HEAD"))
	public void dreamersdeluxe$renderRedstoneHighlights(Slot slot, CallbackInfo ci) {
		if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper && ServerLocation.getInstance().isInBedwars()) {
			ItemStack stack = slot.getStack();
			if (stack == null) return;
			if (stack.getItem() != Items.redstone) return;
			GlStateManager.translate(0, 0, 1);
			Gui.drawRect(
				slot.xDisplayPosition,
				slot.yDisplayPosition,
				slot.xDisplayPosition + 16,
				slot.yDisplayPosition + 16,
				//~ if v1 'getRGB()' -> 'getArgb()'
				DreamersConfig.getInstance().defusalHelperColor.getArgb()
			);
		}
	}
}
*//*?}*/