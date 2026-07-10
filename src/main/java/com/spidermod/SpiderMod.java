package com.spidermod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.spidermod.item.SpiderSilkShooterItem;
import com.spidermod.item.SpiderWebItem;
import com.spidermod.handler.SpiderInteractionHandler;

public class SpiderMod implements ModInitializer {
	public static final String MOD_ID = "spidermod";

	public static final Item SPIDER_SILK_SHOOTER = new SpiderSilkShooterItem(new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item SPIDER_WEB = new SpiderWebItem(new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "spider_silk_shooter"), SPIDER_SILK_SHOOTER);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "spider_web"), SPIDER_WEB);

		AttackEntityCallback.EVENT.register(SpiderInteractionHandler::onAttackEntity);
		UseItemCallback.EVENT.register(SpiderInteractionHandler::onUseItem);
		UseBlockCallback.EVENT.register(SpiderInteractionHandler::onUseBlock);
	}
}
