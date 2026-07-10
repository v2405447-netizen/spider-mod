package com.spidermod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import com.spidermod.handler.SpiderPlayerUtil;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "updateVelocity", at = @At("HEAD"))
	private void onUpdateVelocity(CallbackInfo ci) {
		Entity entity = (Entity) (Object) this;
		
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (SpiderPlayerUtil.isSpiderPlayer(player)) {
				// Хождение по воде как по земле
				if (player.isWet()) {
					BlockPos blockPos = player.getBlockPos();
					if (player.world.getBlockState(blockPos.up()).getBlock() == Blocks.WATER) {
						player.setVelocity(player.getVelocity().x, 0, player.getVelocity().z);
					}
				}
			}
		}
	}
}
