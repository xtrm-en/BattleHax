package me.xtrm.battlehax.mixin.impl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class MixinEntity {
    @Shadow public double posX;
    @Shadow public double posY;
    @Shadow public double posZ;

    @Shadow public float rotationYaw;
    @Shadow public float rotationPitch;

    @Shadow public boolean onGround;
}
