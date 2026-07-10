package com.spidermod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.CobwebBlock;
import com.spidermod.handler.SpiderPlayerUtil;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(method = "getMovementSpeed", at = @At("RETURN"), cancellable = true)
	private void onGetMovementSpeed(CallbackInfoReturnable<Float> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (SpiderPlayerUtil.isSpiderPlayer(player)) {
				// Без замедления в паутине
				if (entity.world.getBlockState(entity.getBlockPos()).getBlock() == Blocks.COBWEB) {
					cir.setReturnValue(cir.getReturnValue() * 5.0F);
				}
			}
		}
	}
}
