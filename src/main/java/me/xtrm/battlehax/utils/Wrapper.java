package me.xtrm.battlehax.utils;

import java.awt.Font;
import java.io.InputStream;

import me.xtrm.battlehax.utils.fontRenderer.GlyphPage;
import me.xtrm.battlehax.utils.fontRenderer.GlyphPageFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * Minecraft Wrapper
 * @author xTrM_
 */
public class Wrapper {
	
	public static final Minecraft mc = Minecraft.getMinecraft();
	public static final FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	
	public static final GlyphPageFontRenderer font, fontBig, fontSmol, fontBigBig;
	
	static {
		char[] chars = new char[256];
		for(int i = 0; i < chars.length; i++) {
			chars[i] = (char)i;
		}
		
		GlyphPage page1 = new GlyphPage(getFont("Comfortaa", Font.PLAIN, 21), true, true);
		page1.generateGlyphPage(chars);
		page1.setupTexture();
		fontSmol = new GlyphPageFontRenderer(page1, page1, page1, page1);
		
		GlyphPage page2 = new GlyphPage(getFont("Comfortaa", Font.PLAIN, 42), true, true);
		page2.generateGlyphPage(chars);
		page2.setupTexture();
		fontBig = new GlyphPageFontRenderer(page2, page2, page2, page2);
		
		GlyphPage page3 = new GlyphPage(getFont("Comfortaa", Font.PLAIN, 24), true, true);
		page3.generateGlyphPage(chars);
		page3.setupTexture();
		font = new GlyphPageFontRenderer(page3, page3, page3, page3);
		
		GlyphPage page4 = new GlyphPage(getFont("Comfortaa", Font.PLAIN, 100), true, true);
		page4.generateGlyphPage(chars);
		page4.setupTexture();
		fontBigBig = new GlyphPageFontRenderer(page4, page4, page4, page4);
	}

	private static Font getFont(String name, int type, int size) {
        try {
            InputStream is = Wrapper.class.getResourceAsStream("/" + name.toLowerCase() + ".ttf");
            Font font = Font.createFont(0, is);
            return font.deriveFont(type, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Font(name, type, size);
        }
    }
}
