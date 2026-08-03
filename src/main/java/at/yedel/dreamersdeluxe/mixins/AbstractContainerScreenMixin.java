package at.yedel.dreamersdeluxe.mixins;



import at.yedel.dreamersdeluxe.features.DefusalHelper;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> {

    @Shadow @Final protected T menu;

    /**
     * Taken from Skyblocker under the GNU LGPL v3
     * <a href="https://github.com/SkyblockerMod/Skyblocker/blob/b23069c5a832deaaa0fca506aed234703f53334a/src/main/java/de/hysky/skyblocker/mixins/AbstractContainerScreenMixin.java#L164-L167">de.hysky.skyblocker.mixins.AbstractContainerScreenMixin</a>
     */
    @Inject(method = "extractTooltip", at = @At("HEAD"))
    private void skyblocker$beforeTooltipExtracted(CallbackInfo ci, @Local(name = "graphics") GuiGraphicsExtractor graphics) {
        DefusalHelper.extractRedstoneHighlights(graphics, (AbstractContainerScreen<ChestMenu>) (Object) this, menu.slots);
    }
}
