package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventBlockReach;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.MovementUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class ClickTP extends Module {

	public ClickTP() {
		super("Teleport", Category.Player);
	}
	
	@EventTarget
	public void onUpdate(EventMotion e) {
		if(e.getType() != EventType.PRE) {
			return;
		}
		
		
		if(mc.gameSettings.keyBindUseItem.isKeyDown()) {
			BlockPos pos = getBlinkBlock().getBlockPos().add(0,1,0);
			
			MovementUtils.teleport(new double[] {mc.player.posX, mc.player.posY, mc.player.posZ}, pos);
			
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
		}
	}
	
	public RayTraceResult getBlinkBlock() {
        Vec3d var4 = mc.player.getPositionEyes(1.0f);
        Vec3d var5 = mc.player.getLook(1.0f);
        Vec3d var6 = var4.addVector(var5.x * 70, var5.y * 70, var5.z * 70);
        return mc.player.world.rayTraceBlocks(var4, var6, false, false, true);
    }
	
	@EventTarget
	public void onReach(EventBlockReach e) {
		e.setReach(250F);
	}

}
