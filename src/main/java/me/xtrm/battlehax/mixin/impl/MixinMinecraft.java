package me.xtrm.battlehax.mixin.impl;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.xtrm.battlehax.BattleHax;
import me.xtrm.battlehax.management.impl.event.impl.EventKeyboard;
import me.xtrm.battlehax.management.impl.event.impl.EventOpenGui;
import me.xtrm.battlehax.management.impl.event.impl.EventTick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Project: LiquidBase
 * -----------------------------------------------------------
 * Copyright © 2017 | CCBlueX | All rights reserved.
 */

/**
 * Mixin used to change the {@link Minecraft} class
 * Used to instanciate {@link BattleHax} in the Minecraft Constructor,
 */
@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public class MixinMinecraft {

    @Shadow public GuiScreen currentScreen;
    @Shadow private SoundHandler mcSoundHandler;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initMinecraft(CallbackInfo callbackInfo) {
        new BattleHax();
    }

    @Inject(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    private void startClient(CallbackInfo callbackInfo) {
        BattleHax.getInstance().startClient();
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void runTickKeyboard(CallbackInfo callbackInfo) {
        if(Keyboard.getEventKeyState() && currentScreen == null)
            BattleHax.getInstance().getMasterManager().getEventManager().callEvent(new EventKeyboard(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey()));
    }
    
    @Inject(method = "runTick", at = @At("HEAD"))
    private void runTick(CallbackInfo info) {
    	BattleHax.getInstance().getMasterManager().getEventManager().callEvent(new EventTick());
    }
    
    @Overwrite
    public void displayGuiScreen(@Nullable GuiScreen guiScreenIn) {
        if (guiScreenIn == null && Minecraft.getMinecraft().world == null) { guiScreenIn = new GuiMainMenu(); } else if (guiScreenIn == null && Minecraft.getMinecraft().player.getHealth() <= 0.0F) { guiScreenIn = new GuiGameOver((ITextComponent)null); }

        GuiScreen old = this.currentScreen; net.minecraftforge.client.event.GuiOpenEvent event = new net.minecraftforge.client.event.GuiOpenEvent(guiScreenIn); if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return; guiScreenIn = event.getGui();
        
        //TODO: Client
        EventOpenGui gui = new EventOpenGui(guiScreenIn);
        BattleHax.getInstance().getMasterManager().getEventManager().callEvent(gui);
        guiScreenIn = gui.getGui();
        
        if (old != null && guiScreenIn != old) { old.onGuiClosed(); }

        if (guiScreenIn instanceof GuiMainMenu || guiScreenIn instanceof GuiMultiplayer) { Minecraft.getMinecraft().gameSettings.showDebugInfo = false; Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(true); } this.currentScreen = guiScreenIn;

        if (guiScreenIn != null) { Minecraft.getMinecraft().setIngameNotInFocus(); KeyBinding.unPressAllKeys(); while (Mouse.next()) { ; } while (Keyboard.next()) { ; } ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft()); int i = scaledresolution.getScaledWidth(); int j = scaledresolution.getScaledHeight(); guiScreenIn.setWorldAndResolution(Minecraft.getMinecraft(), i, j); Minecraft.getMinecraft().skipRenderWorld = false; } else { mcSoundHandler.resumeSounds(); Minecraft.getMinecraft().setIngameFocus(); }
    }
}
