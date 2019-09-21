package me.xtrm.battlehax.management.impl.event.impl;

import me.ihaq.eventmanager.Event;
import me.ihaq.eventmanager.data.EventType;
import me.ihaq.eventmanager.type.Typed;

public class EventMotion extends Event implements Typed {
	
    private EventType eventType;
    
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;
    
    private boolean noPostUpdate;

    public EventMotion(EventType eventType, double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.eventType = eventType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override public EventType getType() { return eventType; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() {return y; }
    public void setY(double y) { this.y = y; }
    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }
    public float getYaw() { return yaw; }
    public void setYaw(float yaw) { this.yaw = yaw; }
    public float getPitch() { return pitch; }
    public void setPitch(float pitch) { this.pitch = pitch; }
    public boolean isOnGround() { return onGround; }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
    
    public void noPostUpdate() { noPostUpdate = true; }
    public boolean shouldPostUpdate() { return !noPostUpdate; }
}