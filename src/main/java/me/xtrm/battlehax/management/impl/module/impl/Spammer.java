package me.xtrm.battlehax.management.impl.module.impl;

import java.util.Random;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventUpdate;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.management.impl.setting.Setting;
import me.xtrm.battlehax.utils.TimeHelper;

public class Spammer extends Module {

	public Spammer() {
		super("Spammer", Category.Player);
		addSetting(new Setting("Delay", this, 2, 0.1D, 10, false));
		addSetting(new Setting("AntiSpam", this, true));
	}
	
	private TimeHelper timer = new TimeHelper();
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(timer.hasReached((long)(getSetting("Delay").doubl() * 1000))) {
			timer.reset();
			
			String msg = "BattleHax by xTrM_ & FurYzen - Best Battlefields client - Made in 1 day lmao";
			
			if(getSetting("AntiSpam").bool())
				msg = msg + " " + Math.min(999999, (new Random()).nextInt(1000000) + 10000);
			
			mc.player.sendChatMessage(msg);
		}
	}

}
