//? if modern {
package at.yedel.dreamersdeluxe.mixins.modern;


import at.yedel.dreamersdeluxe.config.DreamersConfig;
 import at.yedel.dreamersdeluxe.utils.ServerLocation;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
    //~ if < 26.2 'Hud' -> 'Gui'
import net.minecraft.client.gui.Hud;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



//~ if < 26.2 'Hud' -> 'Gui'
@Mixin(Hud.class)
public abstract class ScoreboardRenderingMixin {
    //~ if < 26.2 'Hud' -> 'Gui'
    @ModifyExpressionValue(method = "displayScoreboardSidebar", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Hud$1DisplayEntry;name:Lnet/minecraft/network/chat/Component;"))
    private Component dreamersdeluxe$replaceTeamNamesWithVerityVariants(Component original) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().replaceTeamNamesWithVerityVariants && ServerLocation.getInstance().isInBedwars()) {
            return Component.literal(DreamersConfig.getInstance().getVerityText(original.getString()));
        }
        return original;
    }
}
//?}
