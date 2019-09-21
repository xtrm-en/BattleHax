package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventBlockReach;
import me.xtrm.battlehax.management.impl.event.impl.EventKeyboard;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.event.impl.EventRender2D;
import me.xtrm.battlehax.management.impl.event.impl.EventRender3D;
import me.xtrm.battlehax.management.impl.event.impl.EventTick;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;

public class Test extends Module {
	
	public Test() {
		super("Test", Category.Player);
	}
	
	@EventTarget
	public void onMotion(EventMotion e) {
		if(e.getType() != EventType.PRE) return;
		e.setOnGround(false);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		
	}
	
	@EventTarget
	public void onTick(EventTick e) {
		
	}
	
	@EventTarget
	public void onRender2D(EventRender2D e) {
		
	}
	
	@EventTarget
	public void onRender3D(EventRender3D e) {
		
	}
	
	@EventTarget
	public void onKeyboard(EventKeyboard e) {
		
	}
	
	@EventTarget
	public void onBlockReach(EventBlockReach e) {
		
	}
	
	@Override
	protected void onEnable() {
		super.onEnable();
	}
	
	@Override
	protected void onDisable() {
		super.onDisable();
	}
	
	@Override
	protected void onToggle() {
		super.onToggle();
	}

}
