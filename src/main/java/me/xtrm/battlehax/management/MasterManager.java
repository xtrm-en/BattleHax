package me.xtrm.battlehax.management;

import me.ihaq.eventmanager.EventManager;
import me.xtrm.battlehax.management.impl.gui.GuiManager;
import me.xtrm.battlehax.management.impl.module.ModuleManager;
import me.xtrm.battlehax.management.impl.setting.SettingManager;

/**
 * Master of all managers,
 * called on start & shutdown of BattleHax
 * 
 * (mainly a useless class but I liek organized things)
 * 
 * @author xTrM_
 */
public class MasterManager {

	private SettingManager settingManager;
	private ModuleManager moduleManager;
	private EventManager eventManager;
	private GuiManager guiManager;
	
	public MasterManager() {
		eventManager = new EventManager();
		settingManager = new SettingManager();
		moduleManager = new ModuleManager();
		guiManager = new GuiManager();
	}
	
	public void onClientStart() {
		moduleManager.init();
		guiManager.init();
	}
	
	public void onClientStop() {}
	
	public EventManager getEventManager() {
		return eventManager;
	}
	
	public ModuleManager getModuleManager() {
		return moduleManager;
	}
	
	public SettingManager getSettingManager() {
		return settingManager;
	}
	
	public GuiManager getGuiManager() {
		return guiManager;
	}
	
}