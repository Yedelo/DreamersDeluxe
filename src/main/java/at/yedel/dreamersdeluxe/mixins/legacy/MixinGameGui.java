//? if legacy {
/*package at.yedel.dreamersdeluxe.mixins.legacy;



import at.yedel.dreamersdeluxe.config.DreamersConfig;
import at.yedel.dreamersdeluxe.utils.ServerLocation;
import net.minecraft.client.gui.GameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;



@Mixin(GameGui.class)
public abstract class MixinGameGui {
    @Unique
    private static String dreamersdeluxe$getVerityText(String text) {
        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().replaceTeamNamesWithVerityVariants && ServerLocation.getInstance().isInBedwars()) {
            return DreamersConfig.getInstance().getVerityTextAsString(text);
        }
        return text;
    }

    @ModifyArg(method = "renderScoreboardObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;getWidth(Ljava/lang/String;)I", ordinal = 1))
    private String dreamersdeluxe$replaceTeamNamesWithVerityVariants$maxStringWidth(String text) {
        return dreamersdeluxe$getVerityText(text);
    }

    @ModifyArg(method = "renderScoreboardObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;draw(Ljava/lang/String;III)I", ordinal = 0), index = 0)
    private String dreamersdeluxe$replaceTeamNamesWithVerityVariants$stringForRendering(String text) {
        return dreamersdeluxe$getVerityText(text);
    }
}
*///?}