/*? if forge {*//*
package at.yedel.dreamersdeluxe.mixins.legacy;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.features.ServerLocation;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(LayerArmorBase.class)
public abstract class MixinLayerArmorBase {
    @Redirect(method = "renderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemArmor;getColor(Lnet/minecraft/item/ItemStack;)I"))
    private int dreamersdeluxe$customArmorColors(ItemArmor instance, ItemStack stack) {
        int original = instance.getColor(stack);
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().customArmorColors && ServerLocation.getInstance().isInBedwars()) {
            return DreamersConfig.getInstance().getCustomArmorColor(original);
        }
        return original;
    }
}
*//*?}*/