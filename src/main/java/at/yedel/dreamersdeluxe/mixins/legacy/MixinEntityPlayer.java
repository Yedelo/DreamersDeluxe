//? if legacy {
/*package at.yedel.dreamersdeluxe.mixins.legacy;



import at.yedel.dreamersdeluxe.hud.MagicMilkTimeHud;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {
    @Shadow private ItemStack itemInUse;

    @Inject(method = "onItemUseFinish", at = @At("HEAD"))
    private void dreamersdeluxe$handleMilk(CallbackInfo ci) {
        if (itemInUse.getItem() == Items.milk_bucket) {
            MagicMilkTimeHud.handleMilk();
        }
    }
}
*///?}
