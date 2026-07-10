package com.spidermod.handler;

import net.minecraft.entity.player.PlayerEntity;
import com.spidermod.component.SpiderComponent;

public class SpiderPlayerUtil {

	public static boolean isSpiderPlayer(PlayerEntity player) {
		SpiderComponent component = player.getComponent(SpiderComponent.SPIDER_COMPONENT);
		return component != null && component.isSpider();
	}

	public static void setSpiderPlayer(PlayerEntity player, boolean isSpider) {
		SpiderComponent component = player.getComponent(SpiderComponent.SPIDER_COMPONENT);
		if (component != null) {
			component.setSpider(isSpider);
		}
	}
}
