package me.xtrm.battlehax.management.impl.module.impl;

import me.ihaq.eventmanager.listener.EventTarget;
import me.xtrm.battlehax.management.impl.event.impl.EventRender3D;
import me.xtrm.battlehax.management.impl.module.Module;
import me.xtrm.battlehax.utils.RenderUtils;
import net.minecraft.tileentity.TileEntity;

public class StorageESP extends Module {

	public StorageESP() {
		super("StorageESP", Category.Render);
	}
	
	@EventTarget
	public void onRender3D(EventRender3D e) {
		RenderUtils.init3D();
		for(Object o : mc.world.loadedTileEntityList) {
			if(!(o instanceof TileEntity)) continue;
			if(o.getClass().getName().toLowerCase().contains("chest")) 
				RenderUtils.drawOutlinedBox(((TileEntity)o).getRenderBoundingBox());
		}
		RenderUtils.reset3D();
	}

}
