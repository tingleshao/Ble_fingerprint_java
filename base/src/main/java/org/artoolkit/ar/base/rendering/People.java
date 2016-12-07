package org.artoolkit.ar.base.rendering;

import android.opengl.GLES10;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 12/5/16.
 */

public class People {

    private FloatBuffer mVertexBuffer;
    private FloatBuffer	mColorBuffer;
    private ByteBuffer mIndexBuffer;

    private Rect body;
    private Cube head;
    private Cube lhand;
    private Cube rhand;
    float lxs_clap_3_x[];
    float lxs_clap_3_y[];
    float rxs_clap_3_x[];
    float rxs_clap_3_y[];
    float lxs_clap_6_x[];
    float lxs_clap_6_y[];
    float rxs_clap_6_x[];
    float rxs_clap_6_y[];
    float lxs_clap_9_x[];
    float lxs_clap_9_y[];
    float rxs_clap_9_x[];
    float rxs_clap_9_y[];
    float lxs_clap_12_x[];
    float lxs_clap_12_y[];
    float rxs_clap_12_x[];
    float rxs_clap_12_y[];
    float lxs_clap_15_x[];
    float lxs_clap_15_y[];
    float rxs_clap_15_x[];
    float rxs_clap_15_y[];

//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];
//    float lxs_clap_3_y[];

    int count = 0;
    int id = 0;
    public People() {
        this(1.0f);
    }

    public People(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    public People(float size, float x, float y, float z) {
        setArrays(size, x, y, z);
    }

    private void setArrays(float size, float x, float y, float z) {

        float headx = 0f;
        float heady = 0f;
        float headz = 0f;

        float lhandx = 0f;
        float lhandy = 0f;
        float lhandz = 0f;
        float rhandx = 0f;
        float rhandy = 0f;
        float rhandz = 0f;

        float bodyx = headx;
        float bodyy = heady;
        float bodyz = headz - 25f;

        body = new Rect(60.0f, 0.0f, -30.0f, -10.0f);
        lhand = new Cube(20.0f, -30.0f, -0.0f, 20.0f);
        rhand = new Cube(20.0f, 30.0f, -0.0f, 20.0f);
        head = new Cube(40.0f, 0.0f, 60.0f, -10.0f);

        float c = 1.0f;

        float headcolors[] = {
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
                c, 0, 0, c, // 1 red
        };
        head.setColors(headcolors);

        float lhandcolors[] = {
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
                0, c, 0, c, // 3 green
        };
        lhand.setColors(lhandcolors);

        float rhandcolors[] = {
                0, 0, c, c, // 4 blue
                0, 0, c, c, // 4 blue
                0, 0, c, c, // 4 blue
                0, 0, c, c,  // 4 blue
                0, 0, c, c, // 4 blue
                0, 0, c, c,  // 4 blue
                0, 0, c, c,  // 4 blue
                0, 0, c, c,  // 4 blue
        };
        rhand.setColors(rhandcolors);

        float bodycolors[] = {
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
                0, 0, 0, c, // 0 black
        };
        body.setColors(bodycolors);

        float colors[] = {
                0, 0, 0, c, // 0 black
                c, 0, 0, c, // 1 red
                c, c, 0, c, // 2 yellow
                0, c, 0, c, // 3 green
                0, 0, c, c, // 4 blue
                c, 0, c, c, // 5 magenta
                c, c, c, c, // 6 white
                0, c, c, c, // 7 cyan
        };
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

   //     mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
    //    mColorBuffer = RenderUtils.buildFloatBuffer(colors);
    //    mIndexBuffer = RenderUtils.buildByteBuffer(indices);
        lxs_clap_3_x = new float[] {-36.f, -22.5f, -25.3f};
        lxs_clap_3_y = new float[] {0.33f, -0f, -4.67f};
        rxs_clap_3_x = new float[] {37.f, 22.7f, 11.8f};
        rxs_clap_3_y = new float[] {-2.2f, 1.3f, 11.9f};

        lxs_clap_6_x = new float[] {-36.7f, -38.4f, -27.2f, -38.2f, -19.2f, -58.3f};
        lxs_clap_6_y = new float[] {0.3333f, 1.3f, -5.3333f,  0f, 1.2f, -3.9f};
        rxs_clap_6_x = new float[] {37.1f, 48.6f, 26.1f, 51.0f, 21.0f, 47.5f};
        rxs_clap_6_y = new float[] {-2.2f, 1.2f, 0.2f, -3.4f, 12.5f, 11.1f};

        lxs_clap_9_x = new float[] {  -36.6667f,  -48.0000f,  -21.5000f,  -34.3333f,  -39.0000f,  -16.8333f,  -40.0000f,  -43.7218f, -18.6667f};
        lxs_clap_9_y = new float[] {0.3f,-63.7f,-5.3f, 0.3f, 1.7f, 4.2f,1.8f,-0.9f, 6.8f};
        rxs_clap_9_x = new float[] {37.1f,   55.2f,   7.5f, 31.2f,   55.1f,  10.7f,  33.3f,  52.1f,    11.8f};
        rxs_clap_9_y = new float[] {-2.2f,    2.3f,    3.7f,   -1.3f,    0.5f,   15.2f,  12.0f,   10.7f,    21.0f};

        rxs_clap_12_x = new float[] {37.0855f,55.0867f,51.5428f,7.5000f,16.3333f,42.2908f,55.0658f,34.5000f,11.0000f,33.3333f,54.2093f,41.5588f};
        rxs_clap_12_y = new float[] {-2.1513f,-0.2680f,1.4149f,3.6667f,2.3333f,-1.7692f,0.5352f,-0.8333f,13.3333f,12.0000f,11.1445f,12.9510f};
        lxs_clap_12_x = new float[] {-36.6667f,-51.5000f,-47.8333f,-21.5000f,-24.6667f,-37.6667f,-39.0000f,-35.7809f,-18.3333f,-40.0000f,-50.8333f,-57.7962f};
        lxs_clap_12_y = new float[] {0.3333f,3.5000f,-63.8333f,-5.3333f,-6.1667f,1.8333f,1.6667f,0.0990f,3.0000f,1.8333f,10.2261f,-3.1640f};

        lxs_clap_15_x = new float[] {-36.6667f,-49.5000f,-47.1667f,-36.5000f,-13.3333f,-22.5000f,-37.6667f,-40.4430f,-35.5758f,-16.8333f,-25.3333f,-42.6667f,-50.8333f,-58.3406f,-18.8333f};
        lxs_clap_15_y = new float[] {0.3333f,2.5000f,-62.5000f,0.6667f,1.6667f,0f,1.8333f,1.5543f,-0.0801f,4.1667f,-4.6667f,8.6667f,10.2261f,-3.9275f,8.0000f};
        rxs_clap_15_x = new float[] {37.0855f,54.4006f,55.4478f,36.6667f,9.8333f,20.6667f,42.2908f,54.9693f,46.4032f,10.6667f,11.8050f,37.9379f,54.2093f,47.5224f,11.1667f};
        rxs_clap_15_y = new float[] {-2.1513f,-1.5400f,3.9159f,-1.1667f,4.5000f,1.3333f,-1.7692f,2.1800f,-4.1016f,15.1667f,11.8972f,11.8616f,11.1445f,11.1493f,23.5000f};





    }

    public void draw(GL10 unused) {
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
        count += 1;
        int currlen = lxs_clap_15_x.length;
        float[] currhands = lxs_clap_12_x;
        int maxcount = 45/currlen;
        if (count == maxcount) {
            id += 1;
            count = 0;
            if (currlen == 3) {
                lhand.setArrays(20.0f, lxs_clap_3_x[id], lxs_clap_3_y[id], 15.0f);
                rhand.setArrays(20.0f, rxs_clap_3_x[id], rxs_clap_3_y[id], 15.0f);
            }
            else if (currlen == 6) {
                lhand.setArrays(20.0f, lxs_clap_6_x[id], lxs_clap_6_y[id], 15.0f);
                rhand.setArrays(20.0f, rxs_clap_6_x[id], rxs_clap_6_y[id], 15.0f);
            }
            else if (currlen == 9) {
                lhand.setArrays(20.0f, lxs_clap_9_x[id], lxs_clap_9_y[id], 15.0f);
                rhand.setArrays(20.0f, rxs_clap_9_x[id], rxs_clap_9_y[id], 15.0f);
            }
            else if (currlen == 12) {
                lhand.setArrays(20.0f, lxs_clap_12_x[id], lxs_clap_12_y[id], 15.0f);
                rhand.setArrays(20.0f, rxs_clap_12_x[id], rxs_clap_12_y[id], 15.0f);
            }
            else if (currlen == 15) {
                lhand.setArrays(20.0f, lxs_clap_15_x[id], lxs_clap_15_y[id], 15.0f);
                rhand.setArrays(20.0f, rxs_clap_15_x[id], rxs_clap_15_y[id], 15.0f);
            }
        }
        if (id == currlen-1) {
            id = 0;
        }
        body.draw(unused);
        lhand.draw(unused);
        rhand.draw(unused);
        head.draw(unused);
    }
}