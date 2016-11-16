package com.example.chongshao_mikasa.ble_fingerprint_java;

import android.opengl.Matrix;
import android.util.Log;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.base.rendering.Arrow;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 11/13/16.
 */

public class SimpleRenderer extends ARRenderer  {
    private int markerID = -1;

    private Arrow cube = new Arrow(40.0f, 0.0f, 0.0f, 20.0f);
    private float angle = 0.0f;
    private boolean spinning = false;
    private int drawCount = 0;
    // private float angle = 0.f;
    private float rangle = 0.0f;
    String uuid = "none";
    Map<String, Integer> beaconAngle = new HashMap<String, Integer>();
    MainActivity activity;

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public SimpleRenderer() {
        beaconAngle.put("none", -1);
        beaconAngle.put("a75fa152-a904-4502-8ea8-192f8fcfee6a", 0);
        beaconAngle.put("9795a656-a244-47f5-b8ab-a24cf9728976", 45);
        beaconAngle.put("58deb431-0387-4aff-b04d-bf773f2409cc", 90);
        beaconAngle.put("e14f37ee-cd9c-41a1-b145-134570f9a8e8", 135);
        beaconAngle.put("b6fc3980-846c-4f48-bfc5-2b2e0ca2d702", 180);
        beaconAngle.put("9aa9bca6-b207-41f5-a076-d294c9b374db", 225);
        beaconAngle.put("21f158a6-a083-4020-9b40-8cd34380ffc3", 270);
        beaconAngle.put("7691f1bd-284f-439d-8b1a-d223f0249b9b", 315);
    }

    public void setRAngle(float angle) {
        this.rangle = angle;
    }

    public void setUUID(String UUID) {
        this.uuid = UUID;
    }

    @Override
    public boolean configureARScene() {

        markerID = ARToolKit.getInstance().addMarker("single;Data/patt.hiro;80");
        if (markerID < 0) return false;

        return true;
    }

    public void click() {
        spinning = !spinning;
    }

    public void draw(GL10 gl) {
        this.drawCount += 1;
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glFrontFace(GL10.GL_CW);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        //   if (ARToolKit.getInstance().queryMarkerVisible(markerID)) {
        //     float[] m = ARToolKit.getInstance().queryMarkerTransformation(markerID);
        float[] m = {-0.9930515f, 0.060360666f, 0.10102971f, 0.0f,
                -0.06912676f, -0.993927f, -0.08563979f, 0.0f,
                0.095246725f, -0.09202827f, 0.9911907f, 0.0f,
                -104.001564f, -24.064268f, -271.0829f, 1.0f};

        float xangle;
        if (beaconAngle.get(activity.getClosestUUID()) == null) {
            xangle = 0.0f;
        }
        else {
            xangle = (float)beaconAngle.get(activity.getClosestUUID());
        }
        Log.d("T", "Xangle:" +  String.valueOf(xangle));

        Matrix.rotateM(m, 0, m, 0, xangle, 0,0,1);
        gl.glLoadMatrixf(m, 0);
        gl.glPushMatrix();
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);

        cube.draw(gl);
        gl.glPopMatrix();

        if (spinning) angle += 5.0f;
        //   }
    }


}
