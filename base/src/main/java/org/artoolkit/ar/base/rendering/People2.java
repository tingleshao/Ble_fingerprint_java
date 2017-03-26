package org.artoolkit.ar.base.rendering;


import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 12/5/16.
 */

public class People2 {

    private FloatBuffer mVertexBuffer;
    private FloatBuffer	mColorBuffer;
    private ByteBuffer mIndexBuffer;

    private Rect body;
    private Cube head;
    private Cube lhand;
    private Cube rhand;

    private Sphere ball;


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

    float lxs_roll_3_x[];
    float lxs_roll_3_y[];
    float rxs_roll_3_x[];
    float rxs_roll_3_y[];
    float lxs_roll_6_x[];
    float lxs_roll_6_y[];
    float rxs_roll_6_x[];
    float rxs_roll_6_y[];
    float lxs_roll_9_x[];
    float lxs_roll_9_y[];
    float rxs_roll_9_x[];
    float rxs_roll_9_y[];
    float lxs_roll_12_x[];
    float lxs_roll_12_y[];
    float rxs_roll_12_x[];
    float rxs_roll_12_y[];
    float lxs_roll_15_x[];
    float lxs_roll_15_y[];
    float rxs_roll_15_x[];
    float rxs_roll_15_y[];

    float lxs_wave_3_x[];
    float lxs_wave_3_y[];
    float rxs_wave_3_x[];
    float rxs_wave_3_y[];
    float hxs_wave_3_x[];
    float hxs_wave_3_y[];

    float lxs_wave_6_x[];
    float lxs_wave_6_y[];
    float rxs_wave_6_x[];
    float rxs_wave_6_y[];
    float hxs_wave_6_x[];
    float hxs_wave_6_y[];

    float lxs_wave_9_x[];
    float lxs_wave_9_y[];
    float rxs_wave_9_x[];
    float rxs_wave_9_y[];
    float hxs_wave_9_x[];
    float hxs_wave_9_y[];

    float lxs_wave_12_x[];
    float lxs_wave_12_y[];
    float rxs_wave_12_x[];
    float rxs_wave_12_y[];
    float hxs_wave_12_x[];
    float hxs_wave_12_y[];

    float lxs_wave_15_x[];
    float lxs_wave_15_y[];
    float rxs_wave_15_x[];
    float rxs_wave_15_y[];
    float hxs_wave_15_x[];
    float hxs_wave_15_y[];

    float lxs_lw_3_x[];
    float lxs_lw_3_y[];
    float rxs_lw_3_x[];
    float rxs_lw_3_y[];
    float lxs_lw_6_x[];
    float lxs_lw_6_y[];
    float rxs_lw_6_x[];
    float rxs_lw_6_y[];
    float lxs_lw_9_x[];
    float lxs_lw_9_y[];
    float rxs_lw_9_x[];
    float rxs_lw_9_y[];
    float lxs_lw_12_x[];
    float lxs_lw_12_y[];
    float rxs_lw_12_x[];
    float rxs_lw_12_y[];
    float lxs_lw_15_x[];
    float lxs_lw_15_y[];
    float rxs_lw_15_x[];
    float rxs_lw_15_y[];

    float lxs_rw_3_x[];
    float lxs_rw_3_y[];
    float rxs_rw_3_x[];
    float rxs_rw_3_y[];
    float lxs_rw_6_x[];
    float lxs_rw_6_y[];
    float rxs_rw_6_x[];
    float rxs_rw_6_y[];
    float lxs_rw_9_x[];
    float lxs_rw_9_y[];
    float rxs_rw_9_x[];
    float rxs_rw_9_y[];
    float lxs_rw_12_x[];
    float lxs_rw_12_y[];
    float rxs_rw_12_x[];
    float rxs_rw_12_y[];
    float lxs_rw_15_x[];
    float lxs_rw_15_y[];
    float rxs_rw_15_x[];
    float rxs_rw_15_y[];


    public int count = 0;
    public int id = 0;


    public int currGes;
    public int currlen;
    public int currspeed;
    public People2() {
        this(1.0f);
    }

    public People2(float size) {
        this(size, 0.0f, 0.0f, -1000.0f);
    }

    public People2(float size, float x, float y, float z) {
        setArrays(size, x, y, z);
        currGes = 0;
        currlen = 3;
    }

    private void setArrays(float size, float x, float y, float z) {

        body = new Rect(60.0f, 0.0f, -30.0f, z+(-10.0f));
        lhand = new Cube(20.0f, -30.0f, -0.0f, z+20.0f);
        rhand = new Cube(20.0f, 30.0f, -0.0f, z+20.0f);
        head = new Cube(40.0f, 0.0f, 60.0f, z+(-10.0f));
        ball = new Sphere(z);

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


        rxs_roll_3_x = new float[] {11.6667f,11.6667f,13.0630f};
        rxs_roll_3_y = new float[] {-19.8333f,6.5000f,2.5385f};
        lxs_roll_3_x = new float[] {-6.3333f,-22.5253f,22.5000f};
        lxs_roll_3_y = new float[] {0.6667f,-26.6667f,-13.8333f};


        lxs_roll_6_x = new float[] {                -6.3333f, -5.0000f,-16.8333f, -3.3333f, 21.6667f,-29.6667f
        };
        lxs_roll_6_y = new float[] {        0.6667f,-27.3333f,-28.5000f, -3.8333f,-13.3333f,-65.0000f
        };
        rxs_roll_6_x = new float[] {11.6667f,7.8333f, 11.3333f,7.5000f,5.1667f,7.5000f};
        rxs_roll_6_y = new float[] {                -19.8333f,2.8333f, 10.6667f,-27.1667f,-15.8333f, 32.0606f};

        lxs_roll_9_x = new float[] {                  -6.3333f,-11.8333f,-2.6667f,64.5330f,-2.2494f,-0.8333f,1.1667f,-2.0000f,3.0000f
        };
        lxs_roll_9_y = new float[] {        0.6667f,-25.1667f,-2.6667f,-39.8994f,3.3333f,-23.5000f,-3.3333f,-22.6667f,-0.8333f
        };
        rxs_roll_9_x = new float[] {        11.6667f,9.4986f,5.5000f,10.3884f,7.6667f,8.8333f,3.8333f,3.4647f,3.7813f
        };
        rxs_roll_9_y = new float[] {                -19.8333f,13.7389f,-12.6667f,15.8716f,-24.0000f,22.7895f,-26.6667f,34.5189f,-9.8398f
        };

        lxs_roll_12_x = new float[] {                -6.3333f,-14.6667f,-6.3333f,-2.6667f,72.3333f,-15.0908f,-2.2494f,-18.5000f,3.0000f,1.1667f,-16.8333f,-17.8333f
        };
        lxs_roll_12_y = new float[] {        0.6667f,-11.0000f,-29.1667f,-2.6667f,-42.0582f,-38.7435f,3.3333f,-9.8333f,-28.8333f,-3.3333f,-7.5000f,-34.6667f
        };
        rxs_roll_12_x = new float[] {        11.6667f,18.3092f,5.7269f,5.5000f,12.0000f,8.7342f,7.6667f,8.5000f,6.5408f,3.8333f,6.0000f,6.3425f
        };
        rxs_roll_12_y = new float[] {                -19.8333f,0.5724f,6.0323f,-12.6667f,-2.3333f,11.4244f,-24.0000f,-8.1667f,13.1231f,-26.6667f,-9.0000f,26.1617f
        };

        lxs_roll_15_x = new float[] {                -6.3333f,-15.8333f,-10.3333f,-1.0000f,-5.3333f,-22.5253f,-15.0908f,-2.0000f,-4.3333f,-0.8333f,22.5000f,0.5409f,-16.8333f,-29.6667f,23.0000f
        };
        lxs_roll_15_y = new float[] {        0.6667f,-8.5000f,-28.6667f,-16.8333f,-1.5000f,-26.6667f,-38.7435f,-0.3333f,-7.0000f,-23.5000f,-13.8333f,-2.0000f,-7.5000f,-65.0000f,-10.3333f
        };
        rxs_roll_15_x = new float[] {        11.6667f,17.8137f,10.5000f,-34.1667f,6.8333f,11.6667f,8.7342f,7.0000f,8.1667f,8.8333f,13.0630f,3.6667f,6.0000f,7.5000f,-33.8333f
        };
        rxs_roll_15_y = new float[] {                -19.8333f,-4.1206f,12.6667f,55.1667f,-17.6667f,6.5000f,11.4244f,-18.3333f,-24.6667f,22.7895f,2.5385f,-27.8333f,-9.0000f,32.0606f,56.0000f
        };


        lxs_wave_3_x = new float[] {                -0.1207f, -4.8500f,-11.1667f};
        lxs_wave_3_y = new float[] {                -8.8822f,-7.6217f,-6.8333f};
        rxs_wave_3_x = new float[] {        -51.6667f,-49.8333f,-50.8333f};
        rxs_wave_3_y = new float[] {        73.6667f, 75.8333f, 75.5000f};
        hxs_wave_3_x = new float[] {        -25.0988f,  -26.6974f,  -30.6214f,};
        hxs_wave_3_y = new float[] {        53.2038f,   54.6386f,   54.6841f,};

        lxs_wave_6_x = new float[] {                -0.1207f,-45.0266f, -6.3333f,-58.6667f,-14.8333f, 51.0000f};
        lxs_wave_6_y = new float[] {                -8.8822f, 7.2597f,-7.0000f, -6.3333f,-5.8333f,-3.5000f};
        rxs_wave_6_x = new float[] {        -51.6667f, 13.6667f,-49.8333f, 18.4832f,-51.5000f, 16.8333f};
        rxs_wave_6_y = new float[] {        23.6667f,-16.8333f, 36.0000f,-9.2972f, 36.6667f,-2.3333f};
        hxs_wave_6_x = new float[] {        -24.9547f,  -14.8040f,  -27.5332f,  -19.4693f,  -32.5796f,   34.1244f,};
        hxs_wave_6_y = new float[] {         72.6935f,   55.6841f,   74.7305f,   59.3624f,   45.6114f,    42.3093f,};

        lxs_wave_9_x = new float[] {                 -0.1207f,-36.0942f,-37.9629f,-8.5000f,-40.0000f,-22.3333f,-25.1667f,-52.5000f,-35.8333f};
        lxs_wave_9_y = new float[] {                -8.8822f,40.1113f,16.6283f,-5.0000f,-53.8333f, 6.6667f,-2.5000f,51.6667f, 5.3333f};
        rxs_wave_9_x = new float[] {        -51.6667f,34.6667f,44.9741f,-50.0000f,24.4304f,55.0663f,-49.8333f,18.0000f,55.6597f};
        rxs_wave_9_y = new float[] {        53.6667f,-18.0000f,-30.8333f,56.0000f,-34.5683f,-17.6687f,55.8333f,-20.8333f, 1.1364f};
        hxs_wave_9_x = new float[] {        -25.7230f,   -0.4861f,    3.9413f,  -28.9389f,   -6.8614f,   16.7967f,  -37.3152f,  -16.3451f,   10.8929f};
        hxs_wave_9_y = new float[] {        72.8311f,    46.1668f,    41.5272f,   75.9087f,  7.6059f,    43.0945f,   77.2695f,   66.1279f,   53.1233f
        };

        lxs_wave_12_x = new float[] {-0.1207f,-39.5000f,53.3333f,-37.9629f,-6.0000f,-27.3700f,-40.0000f,-49.1667f,-19.6667f,-25.1667f,-45.8353f,-50.3584f};
        lxs_wave_12_y = new float[] {-28.8822f,-16.1667f,-20.3333f,-6.6283f,-29.3333f,-20.2478f,-23.8333f, 7.6667f,-29.1667f,-22.5000f,17.9704f,25.0605f};
        rxs_wave_12_x = new float[] {-51.6667f,59.2597f,14.5569f,44.9741f,-49.8333f,62.0000f,24.4304f,32.2175f,-49.8333f,-49.8333f,35.2096f,19.6436f};
        rxs_wave_12_y = new float[] {53.6667f,-31.1950f,-39.2317f,-30.8333f,55.8333f,-15.6667f,-34.5683f,-24.0186f,55.8333f,55.8333f,-27.1933f,-23.4426f};
        hxs_wave_12_x = new float[] {-25.7763f,   10.1765f,   34.2639f,    3.9298f,  -27.4088f,   17.4005f,   -7.5223f,   -7.6735f,  -34.7208f,  -36.5711f,   -4.5825f,  -14.8688f,};
        hxs_wave_12_y = new float[] {72.9708f,   37.4436f,  21.3237f,    42.2322f,   73.7968f,    42.5639f,  7.9692f,   52.3129f,   73.9574f,   77.3458f,  55.7841f,   61.1764f,};

        lxs_wave_15_x = new float[] {                -0.1207f,-30.3333f,-40.0000f,-38.9264f,-25.6667f, -4.8500f,-27.3700f,-39.0000f,-58.1667f,-22.3333f,-11.1667f,-30.8333f,-45.8353f, 51.0000f,-41.5035f};
        lxs_wave_15_y = new float[] {                -28.8822f,-13.1667f, 18.3333f, 16.4069f,-23.3333f,-27.6217f,-20.2478f, -3.5000f, 20.3333f,-16.6667f,-26.8333f,-19.5000f, 17.9704f,-13.5000f, -4.5257f};
        rxs_wave_15_x = new float[] {        -51.6667f, 59.7490f, 25.4105f, 20.9711f,-49.8333f,-49.8333f, 62.0000f, 30.6667f, 20.8333f, 55.0663f,-50.8333f,-49.8333f, 35.2096f, 16.8333f, 44.1667f};
        rxs_wave_15_y = new float[] {        53.6667f,-18.1954f,-46.1667f,-34.0722f, 56.0000f, 55.8333f,-15.6667f,-37.0000f,-28.6667f,-17.6687f, 55.5000f, 56.0000f,-27.1933f,-22.3333f,-15.1667f};
        hxs_wave_15_x = new float[] {        -24.9057f,   14.7456f,   -6.4096f,   -8.0644f,  -36.9538f,  -27.2430f,   17.5769f,   -3.8313f,  -17.9869f,   16.5030f,  -30.2788f,  -40.2266f,   -4.6591f,   34.4108f,    2.1106f,};
        hxs_wave_15_y = new float[] {           73.1073f,    45.2227f,    46.9743f,   51.5015f,   77.0321f,   74.3036f,    42.0733f,  74.5059f,   56.3334f,    43.3123f,   75.2381f,   78.8599f,   56.0062f,    42.9428f,   50.9593f,};




        //  lxs_lw_3_x = new float[] { -100.0000f,-100.0000f,-100.0000f};
        //   lxs_lw_3_y = new float[] {-66.6667f,-66.6667f,-66.6667f};
        rxs_lw_3_x = new float[] {-52.3333f,-62.5000f,-62.6667f};
        rxs_lw_3_y = new float[] {43.6667f,55.3333f,54.6667f};

        //      -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //        -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        //   lxs_lw_6_x = new float[] {-36.7f, -38.4f, -27.2f, -38.2f, -19.2f, -58.3f};
        //  lxs_lw_6_y = new float[] {43.6667f,57.5000f,54.3333f,55.0000f,54.6667f,54.6667f};
        rxs_lw_6_x = new float[] {-52.3333f,-69.1667f,-61.8333f,-62.6667f,-62.5000f,-62.8333f};
        rxs_lw_6_y = new float[] {-2.2f, 1.2f, 0.2f, -3.4f, 12.5f, 11.1f};

        //            -100.0000 -100.0000 -100.0000 -100.0000 -100.0000 -100.0000 -100.0000 -100.0000 -100.0000f
        //           -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        //   lxs_lw_9_x = new float[] {  -36.6667f,  -48.0000f,  -21.5000f,  -34.3333f,  -39.0000f,  -16.8333f,  -40.0000f,  -43.7218f, -18.6667f};
        //   lxs_lw_9_y = new float[] {0.3f,-63.7f,-5.3f, 0.3f, 1.7f, 4.2f,1.8f,-0.9f, 6.8f};
        rxs_lw_9_x = new float[] {-52.3333f,-62.6667f,-65.6667f,-62.8333f,-62.8333f,-61.8333f,-64.3176f,-62.6667f,-62.6667f};
        rxs_lw_9_y = new float[] {43.6667f,54.8333f,53.0000f,54.6667f,54.5000f,47.7514f,55.6667f,54.6667f,54.5000f};

        //           -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //            -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        //    lxs_lw_12_x = new float[] {37.0855f,55.0867f,51.5428f,7.5000f,16.3333f,42.2908f,55.0658f,34.5000f,11.0000f,33.3333f,54.2093f,41.5588f};
        //    lxs_lw_12_y = new float[] {-2.1513f,-0.2680f,1.4149f,3.6667f,2.3333f,-1.7692f,0.5352f,-0.8333f,13.3333f,12.0000f,11.1445f,12.9510f};
        rxs_lw_12_x = new float[] {        -52.3333f,-63.0000f,-69.1667f,-65.6667f,-63.0000f,-62.6667f,-62.8333f,-60.6667f,-59.6667f,-64.3176f,-33.5000f,-62.8333f
        };
        rxs_lw_12_y = new float[] {        43.6667f,57.5000f,56.5000f,53.0000f,57.1667f,54.6667f,54.5000f,52.0000f,56.0000f,55.6667f,47.3333f,55.0000f
        };

        //           -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //            -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        //   lxs_lw_15_x = new float[] {-36.6667f,-49.5000f,-47.1667f,-36.5000f,-13.3333f,-22.5000f,-37.6667f,-40.4430f,-35.5758f,-16.8333f,-25.3333f,-42.6667f,-50.8333f,-58.3406f,-18.8333f};
        //   lxs_lw_15_y = new float[] {0.3333f,2.5000f,-62.5000f,0.6667f,1.6667f,0f,1.8333f,1.5543f,-0.0801f,4.1667f,-4.6667f,8.6667f,10.2261f,-3.9275f,8.0000f};
        rxs_lw_15_x = new float[] {        -52.3333f,-63.6667f,-62.8333f,-68.6667f,-67.3333f,-62.5000f,-62.6667f,-62.8333f,-62.8333f,-61.8333f,-62.6667f,-55.8333f,-33.5000f,-62.8333f,-62.8333f
        };
        rxs_lw_15_y = new float[] {        43.6667f,55.0000f,55.5000f,56.6667f,57.1667f,55.3333f,54.6667f,54.1667f,54.6667f,47.7514f,54.6667f,55.5000f,47.3333f,54.6667f,54.6667f
        };

        //      -100.0000f,-100.0000f,-100.0000f
        //              -66.6667f,-66.6667f,-66.666f
        ///     lxs_rw_3_x = new float[] {-36.f, -22.5f, -25.3f};
        //    lxs_rw_3_y = new float[] {0.33f, -0f, -4.67f};
        lxs_rw_3_x = new float[] { 19.1667f,-24.8333f,48.3333f};
        lxs_rw_3_y = new float[] {32.3333f,33.4346f,41.0792f};

        //    -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //            -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        lxs_rw_6_x = new float[] {19.1667f,52.5000f,-17.6667f,75.3333f,46.1667f,-18.6667f};
        lxs_rw_6_y = new float[] {        32.3333f,45.0000f,35.1667f,32.9876f,43.0000f,42.0746f
        };
        //    rxs_rw_6_x = new float[] {37.1f, 48.6f, 26.1f, 51.0f, 21.0f, 47.5f};
        //    rxs_rw_6_y = new float[] {-2.2f, 1.2f, 0.2f, -3.4f, 12.5f, 11.1f};

        //  -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //          -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        lxs_rw_9_x = new float[] { 19.1667f,73.0000f,42.7457f,-32.1667f,81.1667f,60.0000f,49.6636f,-23.3333f,-14.0000f};
        lxs_rw_9_y = new float[] {32.3333f,31.8038f,33.6667f,43.3333f,41.9221f,-44.5136f,43.1667f,42.0000f,32.6667f};
        ////   rxs_rw_9_x = new float[] {37.1f,   55.2f,   7.5f, 31.2f,   55.1f,  10.7f,  33.3f,  52.1f,    11.8f};
        //   rxs_rw_9_y = new float[] {-2.2f,    2.3f,    3.7f,   -1.3f,    0.5f,   15.2f,  12.0f,   10.7f,    21.0f};

        //  -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //          -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        lxs_rw_12_x = new float[] { 19.1667f,81.9474f,56.5588f,42.7457f,65.0000f,-31.4553f,81.1667f,-21.1667f,47.6667f,49.6636f,-9.0200f,-18.4175f};
        lxs_rw_12_y = new float[] {32.3333f,32.6080f,30.9346f,33.6667f,-41.1494f,32.5000f,41.9221f,40.9498f,43.0000f,43.1667f,41.8333f,42.9081f};
        //  rxs_rw_12_x = new float[] {-36.6667f,-51.5000f,-47.8333f,-21.5000f,-24.6667f,-37.6667f,-39.0000f,-35.7809f,-18.3333f,-40.0000f,-50.8333f,-57.7962f};
        //  rxs_rw_12_y = new float[] {0.3333f,3.5000f,-63.8333f,-5.3333f,-6.1667f,1.8333f,1.6667f,0.0990f,3.0000f,1.8333f,10.2261f,-3.1640f};

        //   -100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f,-100.0000f
        //           -66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f,-66.6667f
        lxs_rw_15_x = new float[] {19.1667f,20.0892f,64.0000f,47.5000f,42.5484f,-24.8333f,-31.4553f,18.6667f,-24.8333f,60.0000f,48.3333f,56.8949f,-9.0200f,-18.6667f,-7.8333f};
        lxs_rw_15_y = new float[] {        32.3333f,32.6178f,32.0000f,41.6667f,34.1667f,33.4346f,32.5000f,33.0000f,33.0000f,-44.5136f,41.0792f,46.6667f,41.8333f,42.0746f,41.5000f
        };
        //   rxs_rw_15_x = new float[] {37.0855f,54.4006f,55.4478f,36.6667f,9.8333f,20.6667f,42.2908f,54.9693f,46.4032f,10.6667f,11.8050f,37.9379f,54.2093f,47.5224f,11.1667f};
        //   rxs_rw_15_y = new float[] {-2.1513f,-1.5400f,3.9159f,-1.1667f,4.5000f,1.3333f,-1.7692f,2.1800f,-4.1016f,15.1667f,11.8972f,11.8616f,11.1445f,11.1493f,23.5000f};
    }

    public void draw(GL10 unused) {
//        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
//        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);
//        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
//        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);
//        GLES10.glDrawElements(GLES10.GL_TRIANGLES, 36, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);
//        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
//        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);
        count += 1;
        //  int currlen = 15;
        float[] currhands = lxs_clap_12_x;
        int maxcount = 45 / currlen;
        if (currspeed == 1) {
            maxcount = maxcount / 2;
        }
        if (count == maxcount) {
            id += 1;
            count = 0;
            if (currGes == 0) { // clap
                head.setArrays(40.0f, 0.0f, 60.0f, -10.0f);

                if (currlen == 3) {
                    lhand.setArrays(20.0f, lxs_clap_3_x[id], lxs_clap_3_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_clap_3_x[id], rxs_clap_3_y[id], 15.0f);
                } else if (currlen == 6) {
                    lhand.setArrays(20.0f, lxs_clap_6_x[id], lxs_clap_6_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_clap_6_x[id], rxs_clap_6_y[id], 15.0f);
                } else if (currlen == 9) {
                    lhand.setArrays(20.0f, lxs_clap_9_x[id], lxs_clap_9_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_clap_9_x[id], rxs_clap_9_y[id], 15.0f);
                } else if (currlen == 12) {
                    lhand.setArrays(20.0f, lxs_clap_12_x[id], lxs_clap_12_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_clap_12_x[id], rxs_clap_12_y[id], 15.0f);
                } else if (currlen == 15) {
                    lhand.setArrays(20.0f, lxs_clap_15_x[id], lxs_clap_15_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_clap_15_x[id], rxs_clap_15_y[id], 15.0f);
                }
            } else if (currGes == 1) {  //roll
                head.setArrays(40.0f, 0.0f, 60.0f, -10.0f);

                if (currlen == 3) {
                    lhand.setArrays(20.0f, lxs_roll_3_x[id], lxs_roll_3_x[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_roll_3_x[id], rxs_roll_3_y[id], 15.0f);
                } else if (currlen == 6) {
                    lhand.setArrays(20.0f, lxs_roll_6_x[id], lxs_roll_6_x[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_roll_6_x[id], rxs_roll_6_y[id], 15.0f);
                } else if (currlen == 9) {
                    lhand.setArrays(20.0f, lxs_roll_9_x[id], lxs_roll_9_x[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_roll_9_x[id], rxs_roll_9_y[id], 15.0f);
                } else if (currlen == 12) {
                    lhand.setArrays(20.0f, lxs_roll_12_x[id], lxs_roll_12_x[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_roll_12_x[id], rxs_roll_12_y[id], 15.0f);
                } else if (currlen == 15) {
                    lhand.setArrays(20.0f, lxs_roll_15_x[id], lxs_roll_15_x[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_roll_15_x[id], rxs_roll_15_y[id], 15.0f);
                }
            } else if (currGes == 2) { // wave

                if (currlen == 3) {
                    lhand.setArrays(20.0f, lxs_wave_3_x[id], lxs_wave_3_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_wave_3_x[id], rxs_wave_3_y[id], 15.0f);
                    head.setArrays(40.0f, hxs_wave_3_x[id], hxs_wave_3_y[id], 15.0f);
                } else if (currlen == 6) {
                    lhand.setArrays(20.0f, lxs_wave_6_x[id], lxs_wave_6_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_wave_6_x[id], rxs_wave_6_y[id], 15.0f);
                    head.setArrays(40.0f, hxs_wave_6_x[id], hxs_wave_6_y[id], 15.0f);

                } else if (currlen == 9) {
                    lhand.setArrays(20.0f, lxs_wave_9_x[id], lxs_wave_9_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_wave_9_x[id], rxs_wave_9_y[id], 15.0f);
                    head.setArrays(40.0f, hxs_wave_9_x[id], hxs_wave_9_y[id], 15.0f);

                } else if (currlen == 12) {
                    lhand.setArrays(20.0f, lxs_wave_12_x[id], lxs_wave_12_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_wave_12_x[id], rxs_wave_12_y[id], 15.0f);
                    head.setArrays(40.0f, hxs_wave_12_x[id], hxs_wave_12_y[id], 15.0f);

                } else if (currlen == 15) {
                    lhand.setArrays(20.0f, lxs_wave_15_x[id], lxs_wave_15_y[id], 15.0f);
                    rhand.setArrays(20.0f, rxs_wave_15_x[id], rxs_wave_15_y[id], 15.0f);
                    head.setArrays(40.0f, hxs_wave_15_x[id], hxs_wave_15_y[id], 15.0f);

                }
            } else if (currGes == 3) { //lwave
                head.setArrays(40.0f, 0.0f, 60.0f, -10.0f);

                rhand.setArrays(20.0f, 30.0f, -0.0f, 20.0f);

                if (currlen == 3) {
                    //   lhand.setArrays(20.0f, lxs_clap_3_x[id], lxs_clap_3_y[id], 15.0f);
                    lhand.setArrays(20.0f, rxs_lw_3_x[id], rxs_lw_3_y[id], 15.0f);
                } else if (currlen == 6) {
                    //    lhand.setArrays(20.0f, lxs_clap_6_x[id], lxs_clap_6_y[id], 15.0f);
                    lhand.setArrays(20.0f, rxs_lw_6_x[id], rxs_lw_6_y[id], 15.0f);
                } else if (currlen == 9) {
                    //   lhand.setArrays(20.0f, lxs_clap_9_x[id], lxs_clap_9_y[id], 15.0f);
                    lhand.setArrays(20.0f, rxs_lw_9_x[id], rxs_lw_9_y[id], 15.0f);
                } else if (currlen == 12) {
                    //   lhand.setArrays(20.0f, lxs_clap_12_x[id], lxs_clap_12_y[id], 15.0f);
                    lhand.setArrays(20.0f, rxs_lw_12_x[id], rxs_lw_12_y[id], 15.0f);
                } else if (currlen == 15) {
                    //   lhand.setArrays(20.0f, lxs_clap_15_x[id], lxs_clap_15_y[id], 15.0f);
                    lhand.setArrays(20.0f, rxs_lw_15_x[id], rxs_lw_15_y[id], 15.0f);
                }
            }  else if (currGes == 4) {   // rwave
                head.setArrays(40.0f, 0.0f, 60.0f, -10.0f);

                lhand.setArrays(20.0f, -30.0f, -0.0f, 20.0f);

                if (currlen == 3) {
                    rhand.setArrays(20.0f, lxs_rw_3_x[id], lxs_rw_3_y[id], 15.0f);
                    //    rhand.setArrays(20.0f, rxs_clap_3_x[id], rxs_clap_3_y[id], 15.0f);
                } else if (currlen == 6) {
                    rhand.setArrays(20.0f, lxs_rw_6_x[id], lxs_rw_6_y[id], 15.0f);
                    //     rhand.setArrays(20.0f, rxs_clap_6_x[id], rxs_clap_6_y[id], 15.0f);
                } else if (currlen == 9) {
                    rhand.setArrays(20.0f, lxs_rw_9_x[id], lxs_rw_9_y[id], 15.0f);
                    //    rhand.setArrays(20.0f, rxs_clap_9_x[id], rxs_clap_9_y[id], 15.0f);
                } else if (currlen == 12) {
                    rhand.setArrays(20.0f, lxs_rw_12_x[id], lxs_rw_12_y[id], 15.0f);
                    //     rhand.setArrays(20.0f, rxs_clap_12_x[id], rxs_clap_12_y[id], 15.0f);
                } else if (currlen == 15) {
                    rhand.setArrays(20.0f, lxs_rw_15_x[id], lxs_rw_15_y[id], 15.0f);
                    //    rhand.setArrays(20.0f, rxs_clap_15_x[id], rxs_clap_15_y[id], 15.0f);
                }
            }
        }
        if (id == currlen-1) {
            id = 0;
        }
     //   body.draw(unused);
     ////   lhand.draw(unused);
    //    rhand.draw(unused);
    //    head.draw(unused);
        ball.draw(unused);
    }
}