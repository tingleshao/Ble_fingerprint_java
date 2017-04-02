package org.artoolkit.ar.base.rendering;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES10;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao on 4/2/17.
 */

public class People3 {

    // global buffers

    FloatBuffer mVertexBuffer;
    FloatBuffer mTextureBuffer;
    FloatBuffer mNormalBuffer;

    private FloatBuffer	mColorBuffer;
    private ByteBuffer mIndexBuffer;
    ShortBuffer mIndices;
    Model model;

    public int count;
    public int id;

    public int currlen;
    public int currGes;
    public int currspeed;
    float colors[];

    public People3() {

//penguin_obj is .obj file which is exported from blender

        //     Model model = new Model(R.raw.penguin_obj,
        //             activity);

        this.model = new Model();
    }

    public void setModel(int modelID, Context activity) {
        this.model.setModel(modelID, activity);

        mVertexBuffer = model.getVertices();
        mTextureBuffer = model.getTexCoords();
        mNormalBuffer = model.getNormals();
        mIndices = model.getIndices();



        float size = 40.0f;
        float hs = size / 2.0f;
        float x = 0.0f;
        float y = 60.0f;
        float z = -10.0f;
        float vertices[] = {
                x - hs, y - hs, z - hs, // 0
                x + hs, y - hs, z - hs, // 1
                x + hs, y + hs, z - hs, // 2
                x - hs, y + hs, z - hs, // 3
                x - hs, y - hs, z + hs, // 4
                x + hs, y - hs, z + hs, // 5
                x + hs, y + hs, z + hs, // 6
                x - hs, y + hs, z + hs, // 7
        };
        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);


        byte indices[] = {
                0, 4, 5, 	0, 5, 1,
                1, 5, 6, 	1, 6, 2,
                2, 6, 7, 	2, 7, 3,
                3, 7, 4, 	3, 4, 0,
                4, 7, 6, 	4, 6, 5,
                3, 0, 1, 	3, 1, 2
        };

         mIndexBuffer = RenderUtils.buildByteBuffer(indices);
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

        mColorBuffer = RenderUtils.buildFloatBuffer(colors);

    }

    public void setBitmap(Bitmap bitmap) {
        return;
    }


    public void draw(GL10 gl) {

    //    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);

    //    gl.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);

        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
        GLES10.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);

        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

   //     gl.glEnable(GL10.GL_TEXTURE_2D); // workaround bug 3623

  //      gl.glNormalPointer(1, GL10.GL_FIXED, mNormalBuffer);
        // gl.glColor4f(1, 1, 1, 1);
        // gl.glNormal3f(0, 0, 1);
     //   gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, mVertexBuffer.limit());

        GLES10.glDrawElements(GLES10.GL_TRIANGLES, 36, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);
mVertexBuffer = (FloatBuffer)mVertexBuffer.limit(24);
        for (int i = 0; i < mVertexBuffer.limit(); i++) {
            Log.d("DDL", "LIMIT" + String.valueOf(mVertexBuffer.limit()));

            Log.d("DDL", "mVertexBuffer:" + String.valueOf(mVertexBuffer.get(i)));
        }
        for (int i = 0; i < mIndexBuffer.limit(); i++) {
            Log.d("DDL", "LIMIT" + String.valueOf(mIndexBuffer.limit()));

            Log.d("DDL", "mIndexBuffer:" + String.valueOf(mIndexBuffer.get(i)));
        }

        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }

}

