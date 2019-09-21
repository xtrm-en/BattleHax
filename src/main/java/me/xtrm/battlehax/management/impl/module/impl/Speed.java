package me.xtrm.battlehax.management.impl.module.impl;

import org.lwjgl.input.Keyboard;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.management.impl.setting.Setting;
import me.xtrm.battlehax.utils.MovementUtils;

public class Speed extends Module {

	public Speed() {
		super("Speed", Keyboard.KEY_M, Category.Movement);
		addSetting(new Setting("Motion", this, 1D, 0D, 5D, false));
		addSetting(new Setting("BunnyHop", this, false));
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		MovementUtils.setSpeed(0);
		if(MovementUtils.isMoving()) 
			MovementUtils.setSpeed(getSetting("Motion").doubl());
		
		if(getSetting("BunnyHop").bool())
			if(MovementUtils.isMoving())
				if(mc.player.onGround)
					mc.player.jump();
	}

}
