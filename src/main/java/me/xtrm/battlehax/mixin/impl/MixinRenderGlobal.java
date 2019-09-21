package me.xtrm.battlehax.mixin.impl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.module.impl.ShaderESP;
import me.xtrm.battlehax.utils.Wrapper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

	@Overwrite
	private boolean isOutlineActive(Entity entityIn, Entity viewer, ICamera camera) {		
		boolean flag = viewer instanceof EntityLivingBase && ((EntityLivingBase)viewer).isPlayerSleeping();

        if (entityIn == viewer && Wrapper.mc.gameSettings.thirdPersonView == 0 && !flag)
        {
        	return false;
        }
        else if (entityIn.isGlowing())
        {
            return true;
        }
        else if (Wrapper.mc.player.isSpectator() && Wrapper.mc.gameSettings.keyBindSpectatorOutlines.isKeyDown() && entityIn instanceof EntityPlayer)
        {
            return entityIn.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(entityIn.getEntityBoundingBox()) || entityIn.isRidingOrBeingRiddenBy(Wrapper.mc.player);
        }
        else
        {
        	if(BattleHax.getInstance() == null) return false;
            return BattleHax.getInstance().getMasterManager().getModuleManager().getModule(ShaderESP.class).getState();
        }
	}
	
}
