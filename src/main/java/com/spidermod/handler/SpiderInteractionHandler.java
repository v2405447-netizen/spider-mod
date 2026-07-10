package com.spidermod.handler;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpiderEntity;
import net.minecraft.entity.CaveSpiderEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.spidermod.entity.BabySpiderEntity;

public class SpiderInteractionHandler {

	public static ActionResult onAttackEntity(PlayerEntity player, World world, Hand hand, LivingEntity target, net.minecraft.util.hit.EntityHitResult hitResult) {
		if (SpiderPlayerUtil.isSpiderPlayer(player)) {
			if (target instanceof LivingEntity) {
				target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0));
			}
		}
		return ActionResult.PASS;
	}

	public static ActionResult onUseItem(PlayerEntity player, World world, Hand hand) {
		return ActionResult.PASS;
	}

	public static ActionResult onUseBlock(PlayerEntity player, World world, Hand hand, net.minecraft.util.hit.BlockHitResult hitResult) {
		if (!world.isClient && SpiderPlayerUtil.isSpiderPlayer(player)) {
			if (player.isSneaking()) {
				BlockPos blockPos = hitResult.getBlockPos();
				if (world.getBlockState(blockPos).getBlock() instanceof SpiderEntity || 
					world.getBlockState(blockPos).getBlock() instanceof CaveSpiderEntity) {
					spawnBabySpider(world, blockPos, (LivingEntity) world.getBlockState(blockPos).getBlock());
				}
			}
		}
		return ActionResult.PASS;
	}

	private static void spawnBabySpider(World world, BlockPos pos, LivingEntity parent) {
		// Будет реализовано в полной версии
	}
}
