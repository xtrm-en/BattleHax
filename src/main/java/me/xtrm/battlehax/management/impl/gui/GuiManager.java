package me.xtrm.battlehax.management.impl.gui;

import me.ihaq.eventmanager.listener.EventListener;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventOpenGui;
import me.xtrm.battlehax.management.impl.gui.override.CMainMenu;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiManager implements EventListener {
	
	public GuiManager() {}
	
	public void init() {
		BattleHax.getInstance().getMasterManager().getEventManager().register(this);
	}
	
	@EventTarget
	public void onGui(EventOpenGui e) {
		if(e.getGui() == null) return;
		if(!e.getGui().getClass().getName().toLowerCase().contains("guicubgmainmenu") && !(e.getGui() instanceof GuiMainMenu)) return;
		if(e.getGui() instanceof CMainMenu) return;
//		if(!Keyboard.isKeyDown(Keyboard.KEY_M)) return;
		e.setGui(new CMainMenu());
	}

}
