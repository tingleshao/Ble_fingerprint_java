package org.artoolkit.ar.base.rendering;

import android.opengl.GLES10;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 12/5/16.
 */


public class Rect {

    private FloatBuffer mVertexBuffer;
    private FloatBuffer	mColorBuffer;
    private ByteBuffer mIndexBuffer;
    float colors[];

    public Rect() {
        this(1.0f);
    }

    public Rect(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    public Rect(float size, float x, float y, float z) {
        setArrays(size, x, y, z);
    }

    public void setColors(float[] colors) {
        this.colors = colors;
        mColorBuffer = RenderUtils.buildFloatBuffer(colors);

    }

    private void setArrays(float size, float x, float y, float z) {

        float hs = size / 2.0f;

        float vertices[] = {
                x - hs, y - 2*hs, z - hs, // 0
                x + hs, y - 2*hs, z - hs, // 1
                x + hs, y + 2*hs, z - hs, // 2
                x - hs, y + 2*hs, z - hs, // 3
                x - hs, y - 2*hs, z + hs, // 4
                x + hs, y - 2*hs, z + hs, // 5
                x + hs, y + 2*hs, z + hs, // 6
                x - hs, y + 2*hs, z + hs, // 7
        };


        if (colors == null) {
            float c = 1.0f;
            colors = new float[]{
                    0, 0, 0, c, // 0 black
                    c, 0, 0, c, // 1 red
                    c, c, 0, c, // 2 yellow
                    0, c, 0, c, // 3 green
                    0, 0, c, c, // 4 blue
                    c, 0, c, c, // 5 magenta
                    c, c, c, c, // 6 white
                    0, c, c, c, // 7 cyan
            };
        }
//		float colors[] = {
//			0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//		};

        byte indices[] = {
                0, 4, 5, 	0, 5, 1,
                1, 5, 6, 	1, 6, 2,
                2, 6, 7, 	2, 7, 3,
                3, 7, 4, 	3, 4, 0,
                4, 7, 6, 	4, 6, 5,
                3, 0, 1, 	3, 1, 2
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

        GLES10.glDrawElements(GLES10.GL_TRIANGLES, 36, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);
//           for (int i = 0; i < mVertexBuffer.limit(); i++) {
//               Log.d("DDL", "mVertexBuffer:" + String.valueOf(mVertexBuffer.get(i)));
//           }
//        for (int i = 0; i < mIndexBuffer.limit(); i++) {
//            Log.d("DDL", "mIndexBuffer:" + String.valueOf(mIndexBuffer.get(i)));
//        }

        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }

}