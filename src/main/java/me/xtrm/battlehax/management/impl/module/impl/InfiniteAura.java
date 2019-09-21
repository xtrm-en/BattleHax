package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.CombatUtils;
import net.minecraft.entity.EntityLivingBase;

public class InfiniteAura extends Module {

	public InfiniteAura() {
		super("InfiniteAura", Category.Combat);
	}
	
	@EventTarget
	public void onUpdate(EventMotion e){
		if(e.getType() != EventType.PRE) return;
		
		EntityLivingBase nearest = CombatUtils.findEntity();
		
		if(!CombatUtils.isValid(nearest))
			return;
		if(!CombatUtils.isInReach(nearest, 100D))
			return; 
		
		if(mc.player.getCooledAttackStrength(0) < 1)
			return;
		
		mc.player.setPosition(nearest.posX , nearest.posY - 1, nearest.posZ);
		
		CombatUtils.attack(nearest, false, 1);
	}

}
