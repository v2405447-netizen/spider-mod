package com.spidermod.component;

import net.fabricmc.fabric.api.component.v1.ComponentV1;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class SpiderComponent {
	public static final ComponentV1<SpiderComponent> SPIDER_COMPONENT = ComponentV1.create(new Identifier("spidermod", "spider"), SpiderComponent.class);

	private boolean isSpider = true;
	private int wallClimbCooldown = 0;

	public boolean isSpider() {
		return isSpider;
	}

	public void setSpider(boolean spider) {
		isSpider = spider;
	}

	public int getWallClimbCooldown() {
		return wallClimbCooldown;
	}

	public void setWallClimbCooldown(int cooldown) {
		wallClimbCooldown = cooldown;
	}

	public void fromTag(NbtCompound tag) {
		isSpider = tag.getBoolean("isSpider");
	}

	public void toTag(NbtCompound tag) {
		tag.putBoolean("isSpider", isSpider);
	}
}
