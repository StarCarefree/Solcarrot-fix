package cn.star.solcarrot_fix.mixin;

import com.cazsius.solcarrot.tracking.FoodTracker;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodTracker.class)
public abstract class FoodTrackerMixin {

    @Inject(
            method = "onFoodEaten",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void onFoodEaten(LivingEntityUseItemEvent.Finish event, CallbackInfo ci) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (!player.isAlive() || player.isDeadOrDying()) {
            ci.cancel();
        }
    }

}