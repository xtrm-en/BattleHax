package me.xtrm.battlehax.management.impl.module.impl;

import org.lwjgl.input.Keyboard;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.management.impl.setting.Setting;
import me.xtrm.battlehax.utils.MovementUtils;

public class Fly extends Module {

	public Fly() {
		super("Fly", Keyboard.KEY_G, Category.Movement);
		addSetting(new Setting("Motion", this, 1D, 0D, 5D, false));
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		double val = getSetting("Motion").doubl()*0.4;
		mc.player.motionY = mc.gameSettings.keyBindJump.isKeyDown() ? (mc.gameSettings.keyBindSneak.isKeyDown() ? 0 : val) : mc.gameSettings.keyBindSneak.isKeyDown() ? -val : 0;
		
		MovementUtils.setSpeed(0);
		if(MovementUtils.isMoving())
			MovementUtils.setSpeed(getSetting("Motion").doubl());
	}

}
