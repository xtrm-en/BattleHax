package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;

public class NoFall extends Module {

	public NoFall() {
		super("NoFall", Category.Player);
	}
	
	@EventTarget
	public void onMotion(EventMotion e) {
		if(e.getType() == EventType.PRE && mc.player.fallDistance >= 2.75F)
			e.setOnGround(true);
	}

}
