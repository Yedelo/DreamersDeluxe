/*? if fabric {*/
package at.yedel.dreamersdeluxe.mixins.modern;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(EquipmentLayerRenderer.class)
public class EquipmentLayerRendererMixin {
    @ModifyExpressionValue(
        method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/DyedItemColor;getOrDefault(Lnet/minecraft/world/item/ItemStack;I)I")
    )
    private int dreamersdeluxe$customArmorColors(int original) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().customArmorColors && ServerLocation.getInstance().isInBedwars()) {
            return DreamersConfig.getInstance().getCustomArmorColor(original);
        }
        return original;
    }
}
/*?}*/