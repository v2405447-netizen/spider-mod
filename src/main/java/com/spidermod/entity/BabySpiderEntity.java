package com.spidermod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpiderEntity;
import net.minecraft.world.World;

public class BabySpiderEntity extends SpiderEntity {

	public BabySpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
		super(entityType, world);
		this.setBaby(true);
	}
}
