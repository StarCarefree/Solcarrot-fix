package cn.star.solcarrot_fix.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
            method = "eat",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onEat(net.minecraft.world.level.Level level, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity instanceof Player player) {
            if (!player.isAlive() || player.isDeadOrDying()) {
                cir.setReturnValue(itemStack);
                cir.cancel();
            }
        }
    }

}