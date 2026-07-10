package com.spidermod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import com.spidermod.handler.SpiderPlayerUtil;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	@Inject(method = "tick", at = @At("HEAD"))
	private void onTick(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		
		if (SpiderPlayerUtil.isSpiderPlayer(player)) {
			updateSpiderMovement(player);
			updateWaterWalking(player);
		}
	}

	private void updateSpiderMovement(PlayerEntity player) {
		// Карабканье по стенам
		if (!player.isOnGround() && !player.isSwimming()) {
			BlockPos blockPos = player.getBlockPos();
			
			// Проверка стен вокруг игрока
			if (isWallNear(player)) {
				player.setVelocity(player.getVelocity().x, 0.2, player.getVelocity().z);
			}
		}
	}

	private void updateWaterWalking(PlayerEntity player) {
		// Хождение по воде
		if (player.isWet() && !player.isOnGround()) {
			BlockPos blockPos = player.getBlockPos();
			if (player.world.getBlockState(blockPos).getBlock() == Blocks.WATER || 
				player.world.getBlockState(blockPos.up()).getBlock() == Blocks.WATER) {
				player.setOnGround(true);
			}
		}
	}

	private boolean isWallNear(PlayerEntity player) {
		BlockPos pos = player.getBlockPos();
		return !player.world.getBlockState(pos.north()).getMaterial().isReplaceable() ||
				!player.world.getBlockState(pos.south()).getMaterial().isReplaceable() ||
				!player.world.getBlockState(pos.east()).getMaterial().isReplaceable() ||
				!player.world.getBlockState(pos.west()).getMaterial().isReplaceable();
	}

	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	private void onDamage(DamageSource source, float amount, CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		
		if (SpiderPlayerUtil.isSpiderPlayer(player)) {
			// Без урона от падения
			if (source == DamageSource.FALL) {
				ci.cancel();
			}
		}
	}
}
