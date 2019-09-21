package me.xtrm.battlehax.management.impl.event.impl;

import me.ihaq.eventmanager.Event;
import net.minecraft.client.gui.GuiScreen;

public class EventOpenGui extends Event {
	
	private GuiScreen gui;
	
	public EventOpenGui(GuiScreen gui) {
		this.gui = gui;
	}	
	
	public GuiScreen getGui() {
		return gui;
	}
	
	public void setGui(GuiScreen gui) {
		this.gui = gui;
	}

}
