package me.xtrm.battlehax.management.impl.module;

import me.ihaq.eventmanager.listener.EventListener;
import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * Every module's superclass
 * @author xTrM_
 */
public class Module implements EventListener {
	
	protected Minecraft mc = Minecraft.getMinecraft();
	protected FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	
	private String name, displayName;
	private int key, anim;
	private Category category;
	private boolean enabled, visible;
	
	public Module(String name, String displayName, int key, Category category, boolean enabled, boolean visible) {
		this.name = name;
		this.displayName = displayName;
		this.key = key;
		this.category = category;
		this.enabled = enabled;
		this.visible = visible;
		this.anim = 0;
		
		if(enabled)
			iEnable();
	}
	
	public Module(String name, String displayName, int key, Category category, boolean enabled) { this(name, displayName, key, category, enabled, true); }
	public Module(String name, String displayName, int key, Category category) { this(name, displayName, key, category, false, true); }
	public Module(String name, int key, Category category, boolean enabled, boolean visible) { this(name, name, key, category, enabled, visible); }
	public Module(String name, int key, Category category, boolean enabled) { this(name, name, key, category, enabled, true); }
	public Module(String name, int key, Category category) { this(name, name, key, category, false, true); }
	public Module(String name, Category category, boolean enabled) { this(name, name, 0, category, enabled, true); }
	public Module(String name, Category category) { this(name, name, 0, category, false, true); }

	public enum Category { 
		Combat, Movement, Player, Render, World, Gui; 
		public boolean isVisible() { return this != Gui; }
	}
	
	public String getDisplayName() { return displayName; }
	public void setDisplayName(String displayName) { this.displayName = displayName; }	
	public int getKey() { return key; }
	public void setKey(int key) { this.key = key; }
	public int getAnim() { return anim; }
	public void setAnim(int anim) { this.anim = anim; }
	public boolean isVisible() { return visible; }
	public void setVisible(boolean visible) { this.visible = visible; }
	public String getName() { return name; }
	public Category getCategory() { return category; }
	public boolean isEnabled() { return enabled; }
	public boolean isToggled() { return enabled; }
	public boolean getState() { return enabled; }
	
	public void toggle() {
		this.enabled = !enabled;
		if(enabled)
			iEnable();
		else
			iDisable();
		iToggle();
	}
	
	public void setState(boolean ostate) {
		boolean state = ostate;
		this.enabled = state;
		if(state != enabled)
			iToggle();	
		if(state)
			iEnable();
		else
			iDisable();
	}
	
//	Settings API
	public void addSetting(Setting set) { BattleHax.getInstance().getMasterManager().getSettingManager().rSetting(set); }
	public Setting getSetting(String set) { return BattleHax.getInstance().getMasterManager().getSettingManager().getSettingByUnlocalizedName(name + " " + set); }
	public String getMode() { return BattleHax.getInstance().getMasterManager().getSettingManager().getSettingByUnlocalizedName(name + " Mode").string(); }

	private void iEnable() { if(anim == -1) anim = 0; BattleHax.getInstance().getMasterManager().getEventManager().register(this); onEnable(); }
	private void iDisable() { BattleHax.getInstance().getMasterManager().getEventManager().unregister(this); onDisable(); }
	private void iToggle() { onToggle(); }
	
	protected void onEnable() {}
	protected void onDisable() {}
	protected void onToggle() {}
	
}