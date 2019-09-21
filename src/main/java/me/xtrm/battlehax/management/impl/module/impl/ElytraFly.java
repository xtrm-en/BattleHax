package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class ElytraFly extends Module {

	public ElytraFly() {
		super("ElytraFly", Category.Movement);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		ItemStack itemstack = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (itemstack != null && itemstack.getItem() == Items.ELYTRA) {
        	if(mc.player.isElytraFlying() && mc.player.rotationPitch < -10) {
        		Vec3d vec3d = mc.player.getLookVec();
                mc.player.motionX += vec3d.x * 0.1D + (vec3d.x * 1.5D - mc.player.motionX) * 0.5D;
                mc.player.motionY += vec3d.y * 0.1D + (vec3d.y * 1.5D - mc.player.motionY) * 0.5D;
                mc.player.motionZ += vec3d.z * 0.1D + (vec3d.z * 1.5D - mc.player.motionZ) * 0.5D;
        	}
        }
	}

}
