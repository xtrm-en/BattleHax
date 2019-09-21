package me.xtrm.battlehax.mixin.impl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.ihaq.eventmanager.data.EventType;
import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 * Code: 
 * https://github.com/superblaubeere27/ClientBase/blob/master/src/main/java/net/superblaubeere27/clientbase/injection/mixins/MixinEntityPlayerSP.java
 */
@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends MixinEntity {

	private double cacheX, cacheY, cacheZ;
	private float cachePitch, cacheYaw;
	private boolean cacheGround;
	
	private boolean shouldUpdate;

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    private void onUpdateWalkingPlayerPre(CallbackInfo ci) {
    	cacheX = posX;
    	cacheY = posY;
    	cacheZ = posZ;

    	cacheYaw = rotationYaw;
    	cachePitch = rotationPitch;
    	
    	cacheGround = onGround;

    	BattleHax.getInstance().getMasterManager().getEventManager().callEvent(new EventUpdate());
    	
    	EventMotion event;
        BattleHax.getInstance().getMasterManager().getEventManager().callEvent(event = new EventMotion(EventType.PRE, posX, posY, posZ, rotationYaw, rotationPitch, onGround));

        shouldUpdate = event.shouldPostUpdate();
        
        posX = event.getX();
        posY = event.getY();
        posZ = event.getZ();
        rotationYaw = event.getYaw();
        rotationPitch = event.getPitch();
        onGround = event.isOnGround();
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    private void onUpdateWalkingPlayerPost(CallbackInfo ci) {
    	if(!shouldUpdate)
    		return;
    	
        posX = cacheX;
        posY = cacheY;
        posZ = cacheZ;
        rotationYaw = cacheYaw;
        rotationPitch = cachePitch;
        onGround = cacheGround;

        EventMotion e = new EventMotion(EventType.POST, posX, posY, posZ, rotationYaw, rotationPitch, onGround);
        BattleHax.getInstance().getMasterManager().getEventManager().callEvent(e);
    }
	
}
