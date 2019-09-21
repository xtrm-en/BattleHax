package me.xtrm.battlehax.management.impl.event.impl;

import me.ihaq.eventmanager.Event;

public class EventKeyboard extends Event {

	private int key;
	
	public EventKeyboard(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
}
