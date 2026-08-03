package at.yedel.dreamersdeluxe.mixins;



import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;



/**
 * Taken from Skyblocker under the GNU LGPL v3
 * <a href="https://github.com/SkyblockerMod/Skyblocker/blob/main/src/main/java/de/hysky/skyblocker/mixins/accessors/AbstractContainerScreenAccessor.java">de.hysky.skyblocker.mixins.accessors.AbstractContainerScreenAccessor</a>
 */
@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {
    @Accessor("leftPos")
    int getX();

    @Accessor("topPos")
    int getY();
}
