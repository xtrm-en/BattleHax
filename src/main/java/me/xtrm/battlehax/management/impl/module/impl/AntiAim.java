package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;
import net.minecraft.util.math.MathHelper;

public class AntiAim extends Module {

	public AntiAim() {
		super("AntiAim", Category.Player);
	}
	
	private int spin;

	@EventTarget
	public void onMotion(EventMotion e) {
		if(e.getType() != EventType.PRE) return;
		
		spin += 35;
		spin = MathHelper.wrapDegrees(spin);
		
		mc.player.rotationYawHead = mc.player.renderYawOffset = spin;
		e.setYaw(spin);
		e.setPitch(-90);
	}
}
