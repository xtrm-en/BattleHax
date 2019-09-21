package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.TimeHelper;
import net.minecraft.inventory.ClickType;

public class Stealer extends Module {

	public Stealer() {
		super("Stealer", Category.World);
	}

	private TimeHelper timer = new TimeHelper();

	@EventTarget
	public void onUpdate(EventMotion e) {
		if(e.getType() == EventType.POST) return;
		
		if(this.mc.player.openContainer != null && mc.player.openContainer.getClass().getName().toLowerCase().contains("chest")){
			
			boolean vide = true;
		    int iii = 0;
		    for (int slotAmount = mc.player.openContainer.inventorySlots.size() == 90 ? 54 : 27; iii < slotAmount; iii++) 
		      	if (mc.player.openContainer.getSlot(iii).getHasStack()) 
		      		vide = false;
		    
		    if(!vide) {
		    	for(int i = 0; i < mc.player.openContainer.getInventory().size(); i++){
					if(mc.player.openContainer.getInventory().get(i) != null && timer.hasReached(200)){
						this.mc.playerController.windowClick(mc.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, this.mc.player); 
						this.timer.reset();
					}
				}
		    }else {
		    	mc.player.closeScreen();
	    		mc.displayGuiScreen(null);
		    }
		}
	}
}