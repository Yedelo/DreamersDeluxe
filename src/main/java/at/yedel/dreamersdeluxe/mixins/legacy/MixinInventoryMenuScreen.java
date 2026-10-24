/*? if legacy {*//*
package at.yedel.dreamersdeluxe.mixins.legacy;


import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;

import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.gui.screen.game.inventory.InventoryMenuScreen;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.inventory.slot.InventorySlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(InventoryMenuScreen.class)
public abstract class MixinInventoryMenuScreen {
	@Inject(method = "renderSlot", at = @At("HEAD"))
	public void dreamersdeluxe$renderRedstoneHighlights(InventorySlot slot, CallbackInfo ci) {
		if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper && ServerLocation.getInstance().isInBedwars()) {
			ItemStack stack = slot.getItem();
			if (stack == null) return;
			if (stack.getItem() != Items.REDSTONE) return;
			GlStateManager.translatef(0, 0, 1);
			// this name is ass
			GuiElement.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, DreamersConfig.getInstance().defusalHelperColor.getArgb());
		}
	}
}
*//*?}*/