package com.spidermod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import com.spidermod.entity.SpiderSilkProjectile;

public class SpiderSilkShooterItem extends Item {

	public SpiderSilkShooterItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient) {
			SpiderSilkProjectile projectile = new SpiderSilkProjectile(world, user);
			projectile.setVelocity(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(projectile);
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
