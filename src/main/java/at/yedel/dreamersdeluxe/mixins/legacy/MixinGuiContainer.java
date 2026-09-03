/*? if legacy {*//*
package at.yedel.dreamersdeluxe.mixins.legacy;


import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import cc.polyfrost.oneconfig.libs.universal.UGraphics;
import cc.polyfrost.oneconfig.platform.Platform;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;



@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer {
	@Inject(method = "drawSlot", at = @At("HEAD"))
	public void dreamersdeluxe$renderRedstoneHighlights(Slot slot, CallbackInfo ci) {
		if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().bedwarsDefusalHelper && ServerLocation.getInstance().isInBedwars()) {
			ItemStack stack = slot.getStack();
			if (stack == null) return;
			if (stack.getItem() != Items.redstone) return;
			UGraphics.GL.translate(0, 0, 1);
			Platform.getGLPlatform().drawRect(
				slot.xDisplayPosition,
				slot.yDisplayPosition,
				slot.xDisplayPosition + 16,
				slot.yDisplayPosition + 16,
				DreamersConfig.getInstance().defusalHelperColor.getRGB()
			);
		}
	}
}
*//*?}*/