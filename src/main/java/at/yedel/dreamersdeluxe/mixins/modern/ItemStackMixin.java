/*? if fabric {*/
package at.yedel.dreamersdeluxe.mixins.modern;



import at.yedel.dreamersdeluxe.features.MagicMilkTimeHud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.polyfrost.oneconfig.api.hud.v1.HudManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void dreamersdeluxe$handleMilk(Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        if (getItem() == Items.MILK_BUCKET) {
            HudManager.INSTANCE.getHudsOfType(MagicMilkTimeHud.class).forEach((hud) -> hud.handleMilk());
        }
    }
}
/*?}*/