package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventMotion;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.management.impl.setting.Setting;
import me.xtrm.battlehax.utils.CombatUtils;
import me.xtrm.battlehax.utils.Modes;
import me.xtrm.battlehax.utils.TimeHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class Aimbot extends Module {

	public Aimbot() {
		super("Aimbot/GunAura Test", Category.Combat);
		addSetting(new Setting("AutoShoot", this, false));
		addSetting(new Setting("ShootClick", this, "Left", Modes.build("Left", "Right")));
		addSetting(new Setting("ShootTime", this, 250, 100, 5000, true));
		addSetting(new Setting("Silent", this, true));
	}
	
	private TimeHelper timer = new TimeHelper();
	
	@EventTarget
	public void onMotion(EventMotion e) {
		if(e.getType() != EventType.PRE) return;

		boolean silent = getSetting("Silent").bool();
		boolean shootRight = getSetting("ShootClick").string().equalsIgnoreCase("Right");
		boolean shoot = getSetting("AutoShoot").bool();
		long shootTime = (long)getSetting("ShootTime").doubl();
		
		EntityPlayer nearest = null;
		for(Object o : mc.world.loadedEntityList) {
			if(o instanceof EntityPlayer) {
				EntityPlayer ep = (EntityPlayer)o;
				if(CombatUtils.isValid(ep) && CombatUtils.rayTrace(ep)) {
					if(nearest == null) {
						nearest = ep;
					}else {
						if(mc.player.getDistance(ep) < mc.player.getDistance(nearest))
							nearest = ep;
					}
				}
			}
		}
		if(nearest == null) return;
		
		float yaw = MathHelper.wrapDegrees(CombatUtils.getYaw(nearest));
		float pitch = Math.min(90, Math.max(-90, CombatUtils.getPitch(nearest) + 2));
		
		if(!silent) {
			mc.player.rotationYaw = yaw;
			mc.player.rotationPitch = pitch;
			e.noPostUpdate();
		}
		
		e.setYaw(yaw);
		e.setPitch(pitch);

		if(timer.hasReached(shootTime)) {
			timer.reset();
			
			if(shoot) {
				if(shootRight)
					mc.playerController.processRightClick(mc.player, mc.player.world, EnumHand.MAIN_HAND);
				else
					mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
			}
		}
	}

}
