package me.xtrm.battlehax.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class MovementUtils {
	
	private static Minecraft mc = Minecraft.getMinecraft();

	public static boolean isMoving() {
		return mc.player.moveForward != 0 || mc.player.moveStrafing != 0;
	}
	
    public static double square(double in) {
        return in * in;
    }
 
    public static double getSpeed() {
        return Math.sqrt(square(mc.player.motionX) + square(mc.player.motionZ));
    }
 
    public static float getDirection() {
        float yaw = mc.player.rotationYaw;
        float forward = mc.player.moveForward;
        float strafe = mc.player.moveStrafing;
        yaw += (forward < 0.0F ? 180 : 0);
        if (strafe < 0.0F) {
            yaw += (forward < 0.0F ? -45 : forward == 0.0F ? 90 : 45);
        }
        if (strafe > 0.0F) {
            yaw -= (forward < 0.0F ? -45 : forward == 0.0F ? 90 : 45);
        }
        return yaw * 0.017453292F;
    }
    
    public static void setSpeed(double speed) {
        mc.player.motionX = (-MathHelper.sin(getDirection()) * speed);
        mc.player.motionZ = (MathHelper.cos(getDirection()) * speed);
    }
    
    public static void setRaycastedPositionOffsetted(double offset) {
    	mc.player.setPosition(mc.player.posX + -MathHelper.sin(getDirection()) * offset, mc.player.posY, mc.player.posZ + MathHelper.cos(getDirection()) * offset);
    	mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX + -MathHelper.sin(getDirection()) * offset, mc.player.posY, mc.player.posZ + MathHelper.cos(getDirection()) * offset, mc.player.onGround));
    }
    
    public static void teleport(double x, double y, double z) {
    	double dist = Math.sqrt(mc.player.getDistanceSq(new BlockPos(x, y, z)));
    	
    	double posX = mc.player.posX;
    	double posY = mc.player.posY;
    	double posZ = mc.player.posZ;
    	
    	if(dist > 5) {
    		for(int i = 0; i < dist / 5; i++) {
        		posX += (x - mc.player.posX) / (dist / 5);
        		
        		posY += (x - mc.player.posY) / (dist / 5);
        		
        		posZ += (x - mc.player.posZ) / (dist / 5);
  
//        		mc.player.setPosition(posZ, posY + 2, posZ);
        		mc.player.connection.sendPacket(new CPacketPlayer.Position(posX, posY + 2, posZ, false));
        	}
        	
        	mc.player.setPosition(x, y + 2, z);	
    	} else {
    		mc.player.setPosition(x, y + 2, z);	
    	}
    }
    
    public static void teleport(final double[] startPos, final BlockPos endPos){
    	double dist = Math.sqrt(mc.player.getDistanceSq(endPos));
    	double distanceEntreLesPackets = 5;
    	double xtp, ytp, ztp = 0;
    	
    	if(dist> distanceEntreLesPackets){
    		double nbPackets = Math.round(dist / distanceEntreLesPackets + 0.49999999999) - 1;
    		xtp = mc.player.posX;
    		ytp = mc.player.posY;
    		ztp = mc.player.posZ;
    		for (int i = 1; i < nbPackets;i++){		
    			double xdi = (endPos.getX() - mc.player.posX)/( nbPackets);	
    			xtp += xdi;
    			 
    			double zdi = (endPos.getZ() - mc.player.posZ)/( nbPackets);	
    			ztp += zdi;
    			 
    			double ydi = (endPos.getY() - mc.player.posY)/( nbPackets);	
    			ytp += ydi;
    			CPacketPlayer.Position Packet= new CPacketPlayer.Position(xtp, ytp, ztp, true);
    			
    			mc.player.connection.sendPacket(Packet);
    		}
    		
    		mc.player.setPosition(endPos.getX() + 0.5, endPos.getY(), endPos.getZ() + 0.5);
    	}else{
    		mc.player.setPosition(endPos.getX(), endPos.getY(), endPos.getZ());
    	}
    }


}
