package org.artoolkit.ar.base.rendering;

import android.opengl.GLES10;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 11/13/16.
 */

public class Pyramid {

    private FloatBuffer mVertexBuffer;
    private FloatBuffer	mColorBuffer;
    private ByteBuffer mIndexBuffer;

    public Pyramid() {
        this(1.0f);
    }

    public Pyramid(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    public Pyramid(float size, float x, float y, float z) {
        setArrays(size, x, y, z);
    }

    private void setArrays(float size, float x, float y, float z) {

      //  float hs = size / 2.0f;
        float hs = size;
        float hhs = size / 2.0f;
        float shs = hs * 0.87f;
//        float vertices[] = {
//                x - hs, y - hs, z - hs, // 0
//                x + hs, y - hs, z - hs, // 1
//                x + hs, y + hs, z - hs, // 2
//                x - hs, y + hs, z - hs, // 3
//                x - hs, y - hs, z + hs, // 4
//                x + hs, y - hs, z + hs, // 5
//                x + hs, y + hs, z + hs, // 6
//                x - hs, y + hs, z + hs, // 7
//        };
        float vertices[] = {
                x, y, z+hs,  //0
                x-shs, y, z-hhs,  // 1
                x+shs, y, z-hhs, // 2
                x, y+hs, z, // 3
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
                c, c, c, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
       //         0, 0, 0, c, // 0 black
       //         0, 0, 0, c, // 0 black
       //         0, 0, 0, c, // 0 black
       //        0, 0, 0, c, // 0 black
        };

//
//        byte indices[] = {
//                0, 4, 5, 	0, 5, 1,
//                1, 5, 6, 	1, 6, 2,
//                2, 6, 7, 	2, 7, 3,
//                3, 7, 4, 	3, 4, 0,
//                4, 7, 6, 	4, 6, 5,
//                3, 0, 1, 	3, 1, 2
//        };
        byte indices[] = {
                0, 1, 2,    0, 3, 1,
                0, 3, 2,    1, 3, 2,
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

        GLES10.glDrawElements(GLES10.GL_TRIANGLES, 12, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);

        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }
}
