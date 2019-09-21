package me.xtrm.battlehax.management.impl.event.impl;

import me.ihaq.eventmanager.Event;

public class EventBlockReach extends Event {
	
	private float reach;
	
	public EventBlockReach(float reach) {
		this.reach = reach;
	}
	
	public float getReach() {
		return reach;
	}
	
	public void setReach(float reach) {
		this.reach = reach;
	}
}
