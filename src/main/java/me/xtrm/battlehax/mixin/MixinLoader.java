package me.xtrm.battlehax.mixin;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

/**
 * Project: LiquidBase
 * -----------------------------------------------------------
 * Copyright © 2017 | CCBlueX | All rights reserved.
 */

/**
 * Class loaded by Mixin,
 * Will setup the environment for BattleHax {@link me.xtrm.battlehax.mixin.impl Mixins}.
 */
public class MixinLoader implements IFMLLoadingPlugin {

    public MixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.battlehax.json");
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    /**
     * The strength of this injection client is that
     * it doesn't rely on any Forge {@link ModContainer}
     */
    @Override public String getModContainerClass() { return null; }
    
    @Override public String[] getASMTransformerClass() { return new String[0]; }
    @Override public String getSetupClass() { return null; }
    @Override public void injectData(Map<String, Object> data) { }
    @Override public String getAccessTransformerClass() { return null; }
}
