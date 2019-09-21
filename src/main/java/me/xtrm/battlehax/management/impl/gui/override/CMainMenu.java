package me.xtrm.battlehax.management.impl.gui.override;

import java.awt.Color;
import java.io.IOException;

import me.xtrm.battlehax.management.impl.gui.ui.UIButton;
import me.xtrm.battlehax.utils.Consts;
import me.xtrm.battlehax.utils.TimeHelper;
import me.xtrm.battlehax.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.client.FMLClientHandler;

public class CMainMenu extends GuiScreen {
	
	private int anim, anim2;
	private int incrementValue;
	
	private TimeHelper doTheThing = new TimeHelper();
	
	@Override
	public void initGui() {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		anim = 0;
		
		int widthPart = sr.getScaledWidth() / 6;
		int ix = 0;
		int alphaOffset = 120;
		this.buttonList.add(new UIButton(ix, widthPart * ix++, sr.getScaledHeight() / 2 + (15 + Wrapper.fontBigBig.getFontHeight() / 2), widthPart, 20, "Solo", alphaOffset + (ix * 20)));
		this.buttonList.add(new UIButton(ix, widthPart * ix++, sr.getScaledHeight() / 2 + (15 + Wrapper.fontBigBig.getFontHeight() / 2), widthPart, 20, "Multiplayer", alphaOffset + (ix * 20)));
		this.buttonList.add(new UIButton(ix, widthPart * ix++, sr.getScaledHeight() / 2 + (15 + Wrapper.fontBigBig.getFontHeight() / 2), widthPart, 20, "Join BFs", alphaOffset + (ix * 20)));
		
		UIButton button;
		button = new UIButton(ix, widthPart * ix++, sr.getScaledHeight() / 2 + (15 + Wrapper.fontBigBig.getFontHeight() / 2), widthPart, 20, "Alt Login", alphaOffset + (ix * 20));
		button.enabled = false;
		this.buttonList.add(button);
		
		this.buttonList.add(new UIButton(ix, widthPart * ix++, sr.getScaledHeight() / 2 + (15 + Wrapper.fontBigBig.getFontHeight() / 2), widthPart, 20, "Options", alphaOffset + (ix * 20)));
		this.buttonList.add(new UIButton(ix, widthPart * ix++, sr.getScaledHeight() / 2 + (15 + Wrapper.fontBigBig.getFontHeight() / 2), widthPart, 20, "Quit", alphaOffset + (ix * 20)));
		
		incrementValue = (int) (sr.getScaledWidth() / 15.2);
		switch(mc.gameSettings.guiScale) {
			case 1:
				incrementValue = (int) (63 * 1.75);	
				break;
			case 3:
				incrementValue = (int) (63 / 1.5);
				break;
			case 4:
				incrementValue = (int) (63 / 1.825);
				break;
		}
		
		doTheThing.reset();
		
		super.initGui();
	}
	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xFF101010);
		
		Gui.drawRect(0, sr.getScaledHeight() / 2 - Math.min(anim2, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), sr.getScaledWidth(), sr.getScaledHeight() / 2 + Math.min(anim2, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), 0xCC000000);
		
		int i = 0;
		Wrapper.font.drawStringWithShadow("anim: " + anim, 2, 2 + (10 * i++), -1);
		Wrapper.font.drawStringWithShadow("scaleFactor: " + sr.getScaleFactor(), 2, 2 + (10 * i++), -1);
		Wrapper.font.drawStringWithShadow("width: " + sr.getScaledWidth(), 2, 2 + (10 * i++), -1);
		Wrapper.font.drawStringWithShadow("width/incrementValue: " + (sr.getScaledWidth() / 63), 2, 2 + (10 * i++), -1);
		
		Wrapper.fontBigBig.drawStringWithShadow(Consts.NAME, (anim - Wrapper.fontBigBig.getStringWidth(Consts.NAME) / 2), sr.getScaledHeight() / 2 - Wrapper.fontBigBig.getFontHeight() / 2, new Color(255, 255, 255, (int)Math.min(255, Math.max(20, Math.round(Math.max(0, doTheThing.getLast()) / 5)))).getRGB());
		Wrapper.font.drawStringWithShadow("by xTrM_", sr.getScaledWidth() / 2 - Wrapper.font.getStringWidth("by xTrM_") / 2, sr.getScaledHeight() / 2 + 20, new Color(255, 255, 255, (int)Math.min(255, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - 1000) / 5)))).getRGB());
		
		if(mc.gameSettings.guiScale != 2) {
			Wrapper.font.drawStringWithShadow("Hey! BattleHax GUIs are best rendered with Gui Scale set to Normal", 2, 2, new Color(255, 255, 255, (int)Math.min(255, (doTheThing.getLast() / 5))).getRGB());
		}
		
//		if(doTheThing.hasReached(1200)) {
//			
//			
////			Singleplayer, Multiplayer, Play, BattleHax, Options, Quit
//			
//			int widthPart = sr.getScaledWidth() / 6;
//			int ix = 0;
//			Gui.drawRect(widthPart * ix, sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), widthPart * (ix + 1), sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)) + 20, new Color(5, 5, 5, (int)Math.min(210, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - (1200 + 100*ix++)) / 5)))).getRGB());
//			Gui.drawRect(widthPart * ix, sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), widthPart * (ix + 1), sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)) + 20, new Color(5, 5, 5, (int)Math.min(210, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - (1200 + 100*ix++)) / 5)))).getRGB());
//			Gui.drawRect(widthPart * ix, sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), widthPart * (ix + 1), sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)) + 20, new Color(5, 5, 5, (int)Math.min(210, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - (1200 + 100*ix++)) / 5)))).getRGB());
//			Gui.drawRect(widthPart * ix, sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), widthPart * (ix + 1), sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)) + 20, new Color(5, 5, 5, (int)Math.min(210, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - (1200 + 100*ix++)) / 5)))).getRGB());
//			Gui.drawRect(widthPart * ix, sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), widthPart * (ix + 1), sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)) + 20, new Color(5, 5, 5, (int)Math.min(210, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - (1200 + 100*ix++)) / 5)))).getRGB());
//			Gui.drawRect(widthPart * ix, sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)), widthPart * (ix + 1), sr.getScaledHeight() / 2 + Math.min(anim / 10, (15 + Wrapper.fontBigBig.getFontHeight() / 2)) + 20, new Color(5, 5, 5, (int)Math.min(210, Math.max(20, Math.round(Math.max(0, doTheThing.getLast() - (1200 + 100*ix++)) / 5)))).getRGB());
//		}
		
		int targetAnim = (sr.getScaledWidth() / 2);
		int now = anim;
		if(anim < targetAnim)
			anim += Math.max(1, incrementValue /= 1.1);
		
		int diff = anim - now;

		if(anim > targetAnim)
			anim = targetAnim;
		
		if(anim != targetAnim) {
			if(diff < 2) {
				anim2 += 2;
			}else {
				anim2 = anim / 10;
			}
		} else
			anim2 += 2;
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
			case 0:
				mc.displayGuiScreen(new GuiWorldSelection(this));
				break;
			case 1:
				mc.displayGuiScreen(new GuiMultiplayer(this));
				break;
			case 2:
		        FMLClientHandler.instance().setupServerList();
		        FMLClientHandler.instance().connectToServer(this, new ServerData("Server", "server.cubgmc.net", false));
				break;
			case 3:
				break;
			case 4:
				mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
				break;
			case 5:
				mc.shutdown();
				break;
		}
		super.actionPerformed(button);
	}

}
