package org.artoolkit.ar.base.rendering;

import android.content.Context;
import android.graphics.Bitmap;

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
    ShortBuffer mIndices;
    Model model;

    public int count;
    public int id;

    public int currlen;
    public int currGes;
    public int currspeed;

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
    }

    public void setBitmap(Bitmap bitmap) {
        return;
    }


    public void draw(GL10 gl) {

        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);

        gl.glEnable(GL10.GL_TEXTURE_2D); // workaround bug 3623
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
        gl.glNormalPointer(1, GL10.GL_FIXED, mNormalBuffer);
        // gl.glColor4f(1, 1, 1, 1);
        // gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, mVertexBuffer.limit());
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

}

