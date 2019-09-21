package me.xtrm.battlehax.management.impl.gui.ui;

import java.awt.Color;

import me.xtrm.battlehax.utils.TimeHelper;
import me.xtrm.battlehax.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class UIButton extends GuiButton {
	
	public UIButton(int buttonId, int x, int y, String buttonText, int alphaOffset) { this(buttonId, x, y, 200, 20, buttonText, alphaOffset); }
	public UIButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int alphaOffset) { super(buttonId, x, y, widthIn, heightIn, buttonText); this.alpha = -alphaOffset; }

	/** Used to regulate updated regardless of frames */
	private TimeHelper timer = new TimeHelper();
	
	private int alpha;
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(this.visible) {
			if(!this.enabled) {
				Gui.drawRect(x, y, x + width, y + height, 0xCC000000);
				Wrapper.font.drawStringWithShadow(this.displayString, x + width / 2 - Wrapper.font.getStringWidth(displayString) / 2, y + height / 2 - Wrapper.font.getFontHeight() / 2, new Color(-1).darker().getRGB());
			}else {
				boolean hovered = mouseX > x && mouseY > y && mouseX < x + width && mouseY < y + height;
				
				if(timer.hasReached(1000 / 60)) { // based on 60 FPS
					timer.reset();
					
					if(hovered) {
						for(int i = 0; i < 5; i++) {
							if(alpha < 230)
								alpha++;
							if(alpha > 230)
								alpha--;
						}
					}else {
						for(int i = 0; i < 5; i++) {
							if(alpha > 190)
								alpha--;
							if(alpha < 190)
								alpha++;
						}
					}
				}
				
				if(alpha < 0)
					return;
				
				Color boxColor = new Color(5, 5, 5, alpha);
				Gui.drawRect(x, y, x + width, y + height, boxColor.getRGB());
				
				if(alpha <= 20)
					return;
				
				Color textColor = new Color(255, 255, 255, alpha > 180 ? 255 : alpha);
				Wrapper.font.drawStringWithShadow(this.displayString, x + width / 2 - Wrapper.font.getStringWidth(displayString) / 2, y + height / 2 - Wrapper.font.getFontHeight() / 2, textColor.getRGB());
			}
		}
	}
}
