/*
 *  Cube.java
 *  ARToolKit5
 *
 *  This file is part of ARToolKit.
 *
 *  ARToolKit is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ARToolKit is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with ARToolKit.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  As a special exception, the copyright holders of this library give you
 *  permission to link this library with independent modules to produce an
 *  executable, regardless of the license terms of these independent modules, and to
 *  copy and distribute the resulting executable under terms of your choice,
 *  provided that you also meet, for each linked independent module, the terms and
 *  conditions of the license of that module. An independent module is a module
 *  which is neither derived from nor based on this library. If you modify this
 *  library, you may extend this exception to your version of the library, but you
 *  are not obligated to do so. If you do not wish to do so, delete this exception
 *  statement from your version.
 *
 *  Copyright 2015 Daqri, LLC.
 *  Copyright 2011-2015 ARToolworks, Inc.
 *
 *  Author(s): Julian Looser, Philip Lamb
 *
 */

package org.artoolkit.ar.base.rendering;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;

/**
 * Simple class to render a coloured cube.
 */
public class Sphere {

//    private FloatBuffer	mVertexBuffer;
//    private FloatBuffer	mColorBuffer;
//    private ByteBuffer	mIndexBuffer;
//    float colors[];
//
    private float z;
    public Sphere(float z) {
        this.z = z;
    }
//
//    public Sphere(float size) {
//        this(size, 0.0f, 0.0f, 0.0f);
//    }
//
//    public Sphere(float size, float x, float y, float z) {
//        setArrays(size, x, y, z);
//    }
//
//    public void setColors(float[] colors) {
//        this.colors = colors;
//        mColorBuffer = RenderUtils.buildFloatBuffer(colors);
//
//    }
//
// XXX
//    public void setArrays(float size, float x, float y, float z) {
//
//        float hs = size / 2.0f;
//
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

//        if (colors == null) {
//            float c = 1.0f;
//            colors = new float[]{
//                    0, 0, 0, c, // 0 black
//                    c, 0, 0, c, // 1 red
//                    c, c, 0, c, // 2 yellow
//                    0, c, 0, c, // 3 green
//                    0, 0, c, c, // 4 blue
//                    c, 0, c, c, // 5 magenta
//                    c, c, c, c, // 6 white
//                    0, c, c, c, // 7 cyan
//            };
//        }


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

        // XXX
//        byte indices[] = {
//                0, 4, 5, 	0, 5, 1,
//                1, 5, 6, 	1, 6, 2,
//                2, 6, 7, 	2, 7, 3,
//                3, 7, 4, 	3, 4, 0,
//                4, 7, 6, 	4, 6, 5,
//                3, 0, 1, 	3, 1, 2
//        };
//
//        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
//        mColorBuffer = RenderUtils.buildFloatBuffer(colors);
//        mIndexBuffer = RenderUtils.buildByteBuffer(indices);
//    }

    public void draw(GL10 gl) {
//
//
//        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
//        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);
//
//        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
//        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);
//
//        GLES10.glDrawElements(GLES10.GL_TRIANGLES, 36, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);
//
//        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
//        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

        float angleA, angleB;
        float cos, sin;
        float r1, r2;
        float h1, h2;
        float step = 30.0f;
        float[][] v = new float[32][3];
        ByteBuffer vbb;
        FloatBuffer vBuf;

        vbb = ByteBuffer.allocateDirect(v.length * v[0].length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vBuf = vbb.asFloatBuffer();

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        for (angleA = -90.0f; angleA < 90.0f; angleA+=step) {
            int n = 0;
            r1 = (float)Math.cos(angleA * Math.PI / 180.0);
            r2 = (float)Math.cos((angleA + step) * Math.PI / 180.0);
            h1 = (float)Math.sin(angleA * Math.PI / 180.0);
            h2 = (float)Math.sin((angleA + step) * Math.PI / 180.0);

            // fixed latitude, 360 degrtess rotation to traverse a weft
            for (angleB = 0.0f; angleB <= 360.0f; angleB += step) {
                cos = (float)Math.cos(angleB * Math.PI / 180.0);
                sin = -(float)Math.sin(angleB * Math.PI / 180.0);

                v[n][0] = 35*(r2 * cos);
                v[n][1] = 35*(h2);
                v[n][2] = 35*(z+r2 * sin);
                v[n+1][0] = 35*(r1 * cos);
                v[n+1][1] = 35*(h1);
                v[n+1][2] = 35*(z+r1 * sin);

                vBuf.put(v[n]);
                vBuf.put(v[n+1]);
                n += 2;

                if (n > 31) {
                    vBuf.position(0);
                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
                    gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
                    n = 0;
                    angleB -= step;
                }
            }
            vBuf.position(0);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
            gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }

}