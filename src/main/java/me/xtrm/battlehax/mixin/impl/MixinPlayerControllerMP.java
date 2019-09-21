package me.xtrm.battlehax.mixin.impl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventBlockReach;
import me.xtrm.battlehax.utils.Wrapper;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {

	@Overwrite
	public float getBlockReachDistance()
    {
        float attrib = (float) Wrapper.mc.player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
        float reach = Wrapper.mc.playerController.getCurrentGameType().isCreative() ? attrib : attrib - 0.5F; 
        
		EventBlockReach ebr = new EventBlockReach(reach);
		BattleHax.getInstance().getMasterManager().getEventManager().callEvent(ebr);
        return ebr.getReach();
    }
	
}
