package org.artoolkit.ar.base.rendering;

import android.opengl.GLES10;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 11/15/16.
 */

public class Arrow {

    private FloatBuffer mVertexBuffer;
    private FloatBuffer	mColorBuffer;
    private ByteBuffer mIndexBuffer;

    public Arrow() {
        this(1.0f);
    }

    public Arrow(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    public Arrow(float size, float x, float y, float z) {
        setArrays(size, x, y, z);
    }

    private void setArrays(float size, float x, float y, float z) {

        float hs = size;
        float hhs = size / 2.0f;
        float shs = hs * 0.87f;
        float vertices[] = {
                x, y+hs, z,  //0
                x-shs, y-hhs, z,  // 1
                x, y, z+0.1f, // 2
                x+shs, y-hhs, z, // 3
        };

        float c = 1.0f;
//		float colors[] = {
//			0, 0, 0, c, // 0 black
//			c, 0, 0, c, // 1 red
//			c, c, 0, c, // 2 yellow
//			0, c, 0, c, // 3 green
//			0, 0, c, c, // 4 blue
//			c, 0, c, c, // 5 magenta
//			c, c, c, c, // 6 white
//			0, c, c, c, // 7 cyan
//		};
        float colors[] = {
                c, c, c, c, // 0 white
                0, 0, 0, c, // 1 black
                0, 0, 0, c, // 2 black
                0, 0, 0, c, // 3 black
        };

        byte indices[] = {
                0, 1, 2,   0, 2, 3,
                1, 0, 2,   1, 2, 0,
                2, 0, 1,   2, 0, 3,
                3, 0, 2,   3, 2, 0,
        };

        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        mIndexBuffer = RenderUtils.buildByteBuffer(indices);

    }

    public void draw(GL10 unused) {
        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);

        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

        GLES10.glDrawElements(GLES10.GL_TRIANGLES, 24, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);

        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }
}
