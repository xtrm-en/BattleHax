package me.xtrm.battlehax.management.impl.module.impl;

import org.lwjgl.input.Keyboard;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.CombatUtils;
import net.minecraft.entity.EntityLivingBase;

public class KillAura extends Module {

	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.Combat);
	}
	
	@EventTarget
	public void onMotion(EventMotion e) {
		if(e.getType() != EventType.PRE) return;
		
		EntityLivingBase nearest = CombatUtils.findEntity();
		
		if(!CombatUtils.isValid(nearest))
			return;
		if(!CombatUtils.isInReach(nearest, 6D))
			return; 
		
		if(mc.player.getCooledAttackStrength(0) < 1)
			return;
		
		CombatUtils.attack(nearest, false, 1);
	}

}
