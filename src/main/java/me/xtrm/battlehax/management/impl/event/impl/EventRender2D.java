package me.xtrm.battlehax.management.impl.event.impl;

import me.ihaq.eventmanager.Event;

public class EventRender2D extends Event {
	
	private float partialTicks;
	
	public EventRender2D(float partialTicks) {
		this.partialTicks = partialTicks;
	}
	
	public float getPartialTicks() {
		return partialTicks;
	}

}
