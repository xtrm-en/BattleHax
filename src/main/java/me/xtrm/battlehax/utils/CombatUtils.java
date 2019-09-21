package me.xtrm.battlehax.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

public class CombatUtils {
	
	public static Minecraft mc = Minecraft.getMinecraft();
    
	public static EntityLivingBase findEntity() {
        double Dist = Double.MAX_VALUE;
        EntityLivingBase entity = null;
        for (Object object : mc.world.loadedEntityList) {
            if ((object instanceof EntityLivingBase)) {
                EntityLivingBase e = (EntityLivingBase) object;
                if (CombatUtils.isValid(e) && (mc.player.getDistance(e) < Dist)) {
                    entity = e;
                    Dist = mc.player.getDistance(entity);
                }
            }
        }
        return entity;
    }
	
	public static EntityPlayer findPlayer() {
        double Dist = Double.MAX_VALUE;
        EntityPlayer entity = null;
        for (Object object : mc.world.loadedEntityList) {
            if ((object instanceof EntityPlayer)) {
            	EntityPlayer e = (EntityPlayer) object;
                if (CombatUtils.isValid(e) && (mc.player.getDistance(e) < Dist)) {
                    entity = e;
                    Dist = mc.player.getDistance(entity);
                }
            }
        }
        return entity;
    }

    public static float getPitch(EntityLivingBase ent) {
        double y = (ent.posY + ent.getEyeHeight()) - (mc.player.posY + mc.player.getEyeHeight());
        y /= mc.player.getDistance(ent);
        double pitch = Math.asin(y) * 57;
        pitch = -pitch;
        return (float)pitch;
    }
 
    public static float getYaw(EntityLivingBase ent) {
        double x = ent.posX - mc.player.posX;
        double z = ent.posZ - mc.player.posZ;
        double yaw = Math.atan2(x, z) * 57;
        yaw = -yaw;
        return (float)yaw;
    }
    
    public static boolean rayTrace(EntityLivingBase e) {
    	EntitySnowball eb = new EntitySnowball(mc.player.world, e.posX, e.posY + (double)e.getEyeHeight(), e.posZ);
    	return mc.player.canEntityBeSeen(eb);
	}
    
    public static boolean isValid(EntityLivingBase ent) {
    	if(ent == null)
    		return false;
    	
    	if(ent == mc.player)
    		return false;
    	
    	if(!ent.isEntityAlive())
    		return false;
    	
    	if(ent instanceof EntityArmorStand)
    		return false;
    	
    	if(ent.isInvisible())
    		return false;
    	
    	return true;
    }
    
    public static void attack(EntityLivingBase entity, boolean noSwing, int crackSize) {	
//		mc.player.connection.sendPacket(new CPacketUseEntity(entity));
		mc.playerController.attackEntity(mc.player, entity);
		mc.player.setSprinting(true);
        
		if(noSwing)
			mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
		else
			mc.player.swingArm(EnumHand.MAIN_HAND);
		
        final float sharpLevel = EnchantmentHelper.getModifierForCreature(mc.player.getHeldItem(EnumHand.MAIN_HAND), entity.getCreatureAttribute());
        if (sharpLevel > 0.0f && crackSize == 0) 
            mc.player.onEnchantmentCritical(entity);
        
        for(int i = 0; i < crackSize; i++) {
    		mc.player.onCriticalHit(entity);
    		mc.player.onEnchantmentCritical(entity);
    	}
    }
    
    public static boolean isInReach(EntityLivingBase entity, double reach) {
    	return mc.player.getDistance(entity) <= reach;
    }
}
