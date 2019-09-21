package me.xtrm.battlehax.management.impl.module.impl;

import org.lwjgl.input.Keyboard;

import me.xtrm.battlehax.management.impl.gui.click.ClickGUI;
import me.xtrm.battlehax.management.impl.module.Module;

public class ClickGui extends Module {

	public ClickGui() {
		super("ClickGUI", Keyboard.KEY_RSHIFT, Category.Gui);
	}
	
	private ClickGUI gui;
	
	@Override
	protected void onEnable() {
		mc.displayGuiScreen(gui == null ? (gui = new ClickGUI()) : gui);
		
		toggle();
		super.onEnable();
	}

}
