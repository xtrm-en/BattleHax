package me.xtrm.battlehax.management.impl.module.impl;

import java.awt.Color;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventRender2D;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.Consts;
import me.xtrm.battlehax.utils.Wrapper;
import net.minecraft.client.gui.Gui;

public class HUD extends Module {

	public HUD() {
		super("HUD", Category.Render);
		setState(true);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		drawWatermark();
		drawArraylist();
	}
	
	public void drawWatermark() {
		Gui.drawRect(0, 0, (int) (Wrapper.fontBig.getStringWidth(Consts.NAME) + 8), Wrapper.fontBig.getFontHeight() + 8, 0xBB000000);
		Wrapper.fontBig.drawStringWithShadow(Consts.NAME, 2, 2, -1);
		Wrapper.fontSmol.drawStringWithShadow(Consts.VER_STR + " by xTrM_", (Wrapper.fontBig.getStringWidth(Consts.NAME) + 8) / 2 - Wrapper.fontSmol.getStringWidth(Consts.VER_STR + " by xTrM_") / 2 - 2, 20, -1);
	}
	
	private float hue = 0f;
	public void drawArraylist() {		
		float hue1 = this.hue;
		this.hue += 0.01F;
		if(this.hue >= 255.0F) {
			this.hue = 0.0F;
		}
		
		int yPos = Wrapper.font.getFontHeight() * 2 + 10;
		int increment = Wrapper.font.getFontHeight() - 1;
		for(Module m : BattleHax.getInstance().getMasterManager().getModuleManager().getModules()) {
			if(m.getAnim() != -1) {
				Wrapper.font.drawStringWithShadow(m.getDisplayName(), -Wrapper.font.getStringWidth(m.getDisplayName()) + 2 + m.getAnim(), yPos, Color.HSBtoRGB(hue1, 0.5F, 1F));
				
				hue1 += 0.075f;
	      		if(hue1 >= 255.0F) {
	      			hue1 = 0.0F;
	      		}
				
	      		for(int i = 0; i < 3; i++) { 
	  				if(m.getAnim() < (int)(Wrapper.font.getStringWidth(m.getDisplayName())) && m.isEnabled()){ 
	  					m.setAnim(m.getAnim() + 1); 
	  				} 
	  				if(m.getAnim() > (int)(Wrapper.font.getStringWidth(m.getDisplayName())) && m.isEnabled()){
	  					m.setAnim((int)(Wrapper.font.getStringWidth(m.getDisplayName()))); 
	  				} 
	  				if(m.getAnim() != -1 && !m.isEnabled()){
	  					m.setAnim(m.getAnim() - 1);
	  				}
	  			}
				
				yPos += Math.max(0, Math.min(m.getAnim(), increment));
			}
		}
	}

}
