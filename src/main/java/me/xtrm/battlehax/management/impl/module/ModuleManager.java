package me.xtrm.battlehax.management.impl.module;

import java.util.ArrayList;
import java.util.List;

import me.ihaq.eventmanager.listener.EventListener;
import me.ihaq.eventmanager.listener.EventTarget;

import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventKeyboard;
import me.xtrm.battlehax.management.impl.module.Module.Category;
import me.xtrm.battlehax.management.impl.module.impl.*;

/**
 * Manager in charge of registering modules
 * @author xTrM_
 */
public class ModuleManager implements EventListener {

	public List<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
	}
	
	public void init() {
		// Register
		BattleHax.getInstance().getMasterManager().getEventManager().register(this);
		
		// Add modules
		modules.add(new Aimbot());
		modules.add(new AntiAim());
		modules.add(new ClickGui());
		modules.add(new ClickTP());
		modules.add(new ElytraFly());
		modules.add(new Fly());
		modules.add(new Fullbright());
		modules.add(new HUD());
		modules.add(new InfiniteAura());
		modules.add(new KillAura());
		modules.add(new NoFall());
		modules.add(new ShaderESP());
		modules.add(new Spammer());
		modules.add(new Speed());
		modules.add(new Sprint());
		modules.add(new Stealer());
		modules.add(new StorageESP());
		
		modules.add(new Test());
	}
	
	public List<Module> getModules(){
		return modules;
	}
	
	public Module getModule(Class<? extends Module> clazz) {
		for(Module m : modules) 
			if(m.getClass() == clazz) 
				return m;
		return null;
	}
	
	public Module getModule(String name) {
		for(Module m : modules) 
			if(m.getName() == name) 
				return m;
		return null;
	}
	
	public List<Module> getModulesInCategory(Category cat) {
		List<Module> modz = new ArrayList<Module>();
		for(Module m : modules) 
			if(m.getCategory() == cat) 
				modz.add(m);
		return modz;
	}
	
	@EventTarget
	public void onKeyboard(EventKeyboard e) {
		if(e.getKey() != -1 && e.getKey() != 0) 
			for(Module m : modules) 
				if(e.getKey() == m.getKey()) 
					m.toggle();
	}
	
}
