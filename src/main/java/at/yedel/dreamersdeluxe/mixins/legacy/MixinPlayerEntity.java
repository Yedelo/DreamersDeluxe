//? if legacy {
/*package at.yedel.dreamersdeluxe.mixins.legacy;



import at.yedel.dreamersdeluxe.hud.MagicMilkTimeHud;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @Shadow private ItemStack itemInUse;

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void dreamersdeluxe$handleMilk(CallbackInfo ci) {
        if (itemInUse.getItem() == Items.MILK_BUCKET) {
            MagicMilkTimeHud.handleMilk();
        }
    }
}
*///?}
