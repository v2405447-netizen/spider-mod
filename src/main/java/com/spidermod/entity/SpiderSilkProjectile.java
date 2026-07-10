package com.spidermod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Blocks;

public class SpiderSilkProjectile extends ProjectileEntity {

	public SpiderSilkProjectile(World world, LivingEntity owner) {
		super(EntityType.SNOW_BALL, world);
		this.setOwner(owner);
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		if (!this.world.isClient) {
			BlockPos pos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
			if (this.world.getBlockState(pos).getMaterial().isReplaceable()) {
				this.world.setBlockState(pos, Blocks.COBWEB.getDefaultState());
			}
			this.remove();
		}
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		if (!this.world.isClient) {
			if (entityHitResult.getEntity() instanceof LivingEntity) {
				LivingEntity entity = (LivingEntity) entityHitResult.getEntity();
				entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 2));
			}
			this.remove();
		}
	}
}
