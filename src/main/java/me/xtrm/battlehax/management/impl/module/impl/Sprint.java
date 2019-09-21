package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.MovementUtils;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", Category.Movement);
	}

	@EventTarget
	public void onUpdate(EventUpdate e) {
		mc.player.setSprinting(MovementUtils.isMoving() && !mc.player.collidedHorizontally);
	}
}
