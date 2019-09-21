package me.xtrm.battlehax.mixin.impl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventRender2D;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {
	
	@Inject(method = "renderHotbar", at = @At("RETURN"))
	public void renderHotbar(ScaledResolution sr, float partialTicks, CallbackInfo ci) {
		BattleHax.getInstance().getMasterManager().getEventManager().callEvent(new EventRender2D(partialTicks));
	}

}
