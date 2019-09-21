package me.xtrm.battlehax.utils;

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.util.math.AxisAlignedBB;

public class RenderUtils {

	public static void init3D() {
		glDisable( GL_TEXTURE_2D );
		glDisable( GL_DEPTH_TEST );
		glDisable( GL_CULL_FACE );
		glDepthMask(false);
		glEnable( GL_BLEND );
		glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
		glLineWidth( 1f );
	}
	
	public static void reset3D() {
		glDepthMask(true);
		glDisable( GL_BLEND );
		glEnable( GL_TEXTURE_2D );
		glEnable( GL_DEPTH_TEST );
		glEnable( GL_CULL_FACE );
	}
	
	public static void drawOutlinedBox(AxisAlignedBB box){
        if (box == null) 
            return;

        glBegin(3);
        glVertex3d(box.minX, box.minY, box.minZ);
        glVertex3d(box.maxX, box.minY, box.minZ);
        glVertex3d(box.maxX, box.minY, box.maxZ);
        glVertex3d(box.minX, box.minY, box.maxZ);
        glVertex3d(box.minX, box.minY, box.minZ);
        glEnd();
        glBegin(3);
        glVertex3d(box.minX, box.maxY, box.minZ);
        glVertex3d(box.maxX, box.maxY, box.minZ);
        glVertex3d(box.maxX, box.maxY, box.maxZ);
        glVertex3d(box.minX, box.maxY, box.maxZ);
        glVertex3d(box.minX, box.maxY, box.minZ);
        glEnd();
        glBegin(1);
        glVertex3d(box.minX, box.minY, box.minZ);
        glVertex3d(box.minX, box.maxY, box.minZ);
        glVertex3d(box.maxX, box.minY, box.minZ);
        glVertex3d(box.maxX, box.maxY, box.minZ);
        glVertex3d(box.maxX, box.minY, box.maxZ);
        glVertex3d(box.maxX, box.maxY, box.maxZ);
        glVertex3d(box.minX, box.minY, box.maxZ);
        glVertex3d(box.minX, box.maxY, box.maxZ);
        glEnd();
    }
	
}
