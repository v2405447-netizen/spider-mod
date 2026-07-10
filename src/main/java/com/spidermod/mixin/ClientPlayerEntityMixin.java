package com.spidermod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.network.ClientPlayerEntity;
import com.spidermod.handler.SpiderPlayerUtil;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

	@Inject(method = "updateMovement", at = @At("HEAD"))
	private void onUpdateMovement(CallbackInfo ci) {
		ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
		
		if (SpiderPlayerUtil.isSpiderPlayer(player)) {
			// Дополнительная логика для клиента если нужна
		}
	}
}
