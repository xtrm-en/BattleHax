package me.xtrm.battlehax;

import org.lwjgl.opengl.Display;

import me.xtrm.battlehax.management.MasterManager;
import me.xtrm.battlehax.utils.Consts;
import net.minecraft.client.Minecraft;

/**
 * BattleHax is made by xTrM_ and FurYzen_
 * Some code (mainly mixin-related) comes from the {@link https://git.liquidbounce.net/CCBlueX/LiquidBase LiquidBase} repository
 * 
 * Project started: 21/07/2019 @ 5:21 (UTC+1)
 */

/**
 * Main class of BattleHax.
 * The instance is saved in {@link #INSTANCE}
 * 
 * (this was made while listening to undertale & touhou remixes)
 * 
 * @author xTrM_
 */
public class BattleHax {
	
	/**
	 * Current BattleHax instance
	 */
	private static BattleHax INSTANCE;
	
	/**
	 * MasterManager instance
	 */
	private MasterManager masterManager;
	
	public BattleHax() {
		INSTANCE = this;
	}
	
	/**
	 * Called when starting the client
	 */
	public void startClient() {
		Minecraft.getMinecraft().gameSettings.guiScale = 2;
		
		Display.setTitle(Consts.NAME + " " + Consts.VER_STR);
		
		masterManager = new MasterManager();
		masterManager.onClientStart();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> { onClientStop(); }));
	}
	
	/**
	 * Called on runtime shutdown
	 */
	private void onClientStop() {
		masterManager.onClientStop();
	}
	
	/** Getters */
	
	public static BattleHax getInstance() { return INSTANCE; }
	public MasterManager getMasterManager() { return masterManager; }

}
