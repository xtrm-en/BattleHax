package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", Category.Render);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		mc.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 8400, 10)); 
	}
	
	@Override
	protected void onDisable() {
		mc.player.removePotionEffect(Potion.getPotionById(16));
		super.onDisable();
	}

}
