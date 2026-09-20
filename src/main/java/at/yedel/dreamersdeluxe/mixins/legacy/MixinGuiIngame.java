//? if legacy {
//package at.yedel.dreamersdeluxe.mixins.legacy;
//
//
//
//import at.yedel.dreamersdeluxe.config.DreamersConfig;
//import at.yedel.dreamersdeluxe.utils.ServerLocation;
//import net.minecraft.client.gui.GuiIngame;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.ModifyArg;
//
//
//
//@Mixin(GuiIngame.class)
//public abstract class MixinGuiIngame {
//    @Unique
//    private static String dreamersdeluxe$getVerityText(String text) {
//        if (DreamersConfig.getInstance().enabled && DreamersConfig.getInstance().replaceTeamNamesWithVerityVariants && ServerLocation.getInstance().isInBedwars()) {
//            return DreamersConfig.getInstance().getVerityText(text);
//        }
//        return text;
//    }
//
//    @ModifyArg(method = "renderScoreboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;getStringWidth(Ljava/lang/String;)I", ordinal = 1))
//    private String dreamersdeluxe$replaceTeamNamesWithVerityVariants$maxStringWidth(String text) {
//        return dreamersdeluxe$getVerityText(text);
//    }
//
//    @ModifyArg(method = "renderScoreboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I", ordinal = 0), index = 0)
//    private String dreamersdeluxe$replaceTeamNamesWithVerityVariants$stringForRendering(String text) {
//        return dreamersdeluxe$getVerityText(text);
//    }
//}
//?}