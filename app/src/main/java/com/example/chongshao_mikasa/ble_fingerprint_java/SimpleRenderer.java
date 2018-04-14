package com.example.chongshao_mikasa.ble_fingerprint_java;

import android.graphics.Bitmap;
import android.opengl.Matrix;
import android.util.Log;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.base.rendering.People2;
import org.artoolkit.ar.base.rendering.People3;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import static com.example.chongshao_mikasa.ble_fingerprint_java.Utils.cameraToStr;
import static com.example.chongshao_mikasa.ble_fingerprint_java.Utils.getInitM;

/**
 * Created by chongshao-mikasa on 11/13/16.
 */

public class SimpleRenderer extends ARRenderer  {
 //   private int markerID = -1;

//    private People guy = new People(40.0f, 0.0f, 0.0f, 20.0f);
    private People2 guy = new People2(40.0f, 0.0f, 0.0f, -200.0f);

 //   private People3 guy = new People3();
//
    private float angle = 0.0f;
    private boolean spinning = false;
    private int drawCount = 0;
    // private float angle = 0.f;
    private float rangle = 0.0f;
    String uuid = "none";
    Map<String, Integer> beaconAngle = new HashMap<String, Integer>();
    MainActivity activity;

    float[] m;
    float angle1;
    float angle2;
    float angle3;

    float[] cameraM;

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    Bitmap bitmap;

    int[] textures = new int[1];
    boolean setModeled = false;


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

        m = getInitM();

        float[] rotateM = new float[9];

        // cameraM
        cameraM = new float[16];
        Matrix.invertM(cameraM, 0, m, 0);
        Matrix.invertM(m, 0, cameraM, 0);

        Log.d("T", "cameraM: " + cameraToStr(cameraM));
        Log.d("T", "m: " + cameraToStr(m));

        rotateM[0] = cameraM[0];
        rotateM[1] = cameraM[1];
        rotateM[2] = cameraM[2];
        rotateM[3] = cameraM[4];
        rotateM[4] = cameraM[5];
        rotateM[5] = cameraM[6];
        rotateM[6] = cameraM[8];
        rotateM[7] = cameraM[9];
        rotateM[8] = cameraM[10];

        float[] angles = mToAngles(rotateM);
        angle1 = radToDegree(angles[0]);
        angle2 = radToDegree(angles[1]);
        angle3 = radToDegree(angles[2]);
        Log.d("T", "angles: " + String.valueOf(angle1) + " " + String.valueOf(angle2) + " " + String.valueOf(angle3));
    }

    public void setBitmap(Bitmap bitmap) {
        guy.setBitmap(bitmap);
    }

    public void reset() {
        // reset m matrix
        m = getInitM();
        Matrix.invertM(cameraM, 0, m, 0);
        Matrix.invertM(m, 0, cameraM, 0);
    }

    public float[] getCameraAngles() {
        float[] angles = mToAngles(new float[]{cameraM[0], cameraM[1], cameraM[2], cameraM[4], cameraM[5], cameraM[6], cameraM[8], cameraM[9], cameraM[10]});
        return new float[]{angles[0] * 180.0f / (float)Math.PI, angles[1] * 180.0f / (float)Math.PI, angles[2] * 180.0f / (float)Math.PI};
    }

    public float[] getCameraTranslation() {
        float[] translation = new float[]{cameraM[12], cameraM[13], cameraM[14]};
        return translation;
    }

    public float[] getAnglesFromPoseM(Mat pose) {
        float[] angles = mToAngles(new float[]{(float)pose.get(0,0)[0], (float)pose.get(1,0)[0], (float)pose.get(2,0)[0],
                                               (float)pose.get(0,1)[0], (float)pose.get(1,1)[0], (float)pose.get(2,1)[0],
                                               (float)pose.get(0,2)[0], (float)pose.get(1,2)[0], (float)pose.get(2,2)[0],});
        return new float[]{angles[0] * 180.0f / (float)Math.PI, angles[1] * 180.0f / (float)Math.PI, angles[2] * 180.0f / (float)Math.PI};
    }

    public float[] getTranslationFromPoseM(Mat pose) {
        return new float[]{(float)pose.get(0,3)[0], (float)pose.get(1,3)[0], (float)pose.get(2,3)[0]};
    }

    public static float radToDegree(float radi) {
        return (float) ((double)radi * 180.0 / Math.PI);
    }

    public void setRAngle(float angle) {
        this.rangle = angle;
    }

    public void setUUID(String UUID) {
        this.uuid = UUID;
    }

    @Override
    public boolean configureARScene() {
      //  markerID = ARToolKit.getInstance().addMarker("single;Data/patt.hiro;80");
      //  if (markerID < 0) return false;
        return true;
    }

    public void click() {
        spinning = !spinning;
    }

    // TODO: merge every two to 1 method
    public void xup() {
        this.cameraM[12] += 5.0f;
        updateM(false);
    }

    public void xdown() {
        this.cameraM[12] -= 5.0f;
        updateM(false);
    }

    public void yup() {
        this.cameraM[13] += 5.0f;
        updateM(false);
    }

    public void ydown() {
        this.cameraM[13] -= 5.0f;
        updateM(false);
    }

    public void zup() {
        this.cameraM[14] += 5.0f;
        updateM(false);
    }

    public void zdown() {
        this.cameraM[14] -= 5.0f;
        updateM(false);
    }

    public void rotate1(float delta_angle) {
     //   this.angle1 += delta_angle;
        Matrix.rotateM(cameraM, 0, cameraM, 0, delta_angle, 1, 0, 0);
        updateM(true);
     //   this.displayAngles();
    }

    public void rotate2(float delta_angle) {
        Matrix.rotateM(cameraM, 0, cameraM, 0, delta_angle, 0, 1, 0);
        updateM(true);
    }

    public void rotate3(float delta_angle) {
        Matrix.rotateM(cameraM, 0, cameraM, 0, delta_angle, 0, 0, 1);
        updateM(true);
    }

    public void translate1(float delta_d) {
        Matrix.translateM(cameraM, 0, cameraM, 0, delta_d, 0, 0);
        updateM(true);
    }

    public void translate2(float delta_d) {
        Matrix.translateM(cameraM, 0, cameraM, 0, 0, delta_d, 0);
        updateM(true);
    }

    public void translate3(float delta_d) {
        Matrix.translateM(cameraM, 0, cameraM, 0, 0, 0, delta_d);
        updateM(true);
    }

    public void updateM(boolean updateRotation) {
        // TODO: from camera M, update m
//        if (updateRotation) {
//            float[] angles = {angle1, angle2, angle3};
//            float[] rotateM = anglesToM(angles);
//            cameraM[0] = rotateM[0];
//            cameraM[1] = rotateM[1];
//            cameraM[2] = rotateM[2];
//            cameraM[4] = rotateM[3];
//            cameraM[5] = rotateM[4];
//            cameraM[6] = rotateM[5];
//            cameraM[8] = rotateM[6];
//            cameraM[9] = rotateM[7];
//            cameraM[10] = rotateM[8];
//        }
//        Log.d("T", "in update, cameraM: " + cameraToStr(cameraM));
        Matrix.invertM(m, 0, cameraM, 0);
    }

    public static float[] mToAngles(float[] m) {
        // TODO: here maybe the angle is inverted. Let's see
        double sy = Math.sqrt((double)(m[0] * m[0] + m[1] * m[1]));
     //   Log.d("T", "sy" + String.valueOf(sy));
        boolean singular = sy < 0.000001;
        double x, y, z;
        if (singular) {
            x = Math.atan2(-m[7] , m[4]);
            y = Math.atan2(-m[2] , sy);
            z = 0.0;
        } else {
            x = Math.atan2(m[5] , m[8]);
            y = Math.atan2(-m[2] , sy);
            z = Math.atan2(-m[1] , m[0]);
        }
        float[] angles = {(float)x, (float)y, (float)z};
  //      Log.d("T", "in mtoangles, angles:" + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z));
        return angles;
    }

    public float[] anglesToM(float[] angles) {
        float angle1 = angles[0];
        float angle2 = angles[1];
        float angle3 = angles[2];
        float[] matrixr3 = new float[9];
        float[] matrixr2 = new float[9];
        float[] matrixr1 = new float[9];

        matrixr3[0] = cosd(angle3);
        matrixr3[1] = sind(angle3);
        matrixr3[2] = 0.0f;
        matrixr3[3] = -sind(angle3);
        matrixr3[4] = cosd(angle3);
        matrixr3[5] = 0.0f;
        matrixr3[6] = 0.0f;
        matrixr3[7] = 0.0f;
        matrixr3[8] = 1.0f;

        matrixr2[0] = cosd(angle2);
        matrixr2[1] = 0.0f;
        matrixr2[2] = -sind(angle2);
        matrixr2[3] = 0.0f;
        matrixr2[4] = 1.0f;
        matrixr2[5] = 0.0f;
        matrixr2[6] = sind(angle2);
        matrixr2[7] = 0.0f;
        matrixr2[8] = cosd(angle2);

        matrixr1[0] = 1.0f;
        matrixr1[1] = 0.0f;
        matrixr1[2] = 0.0f;
        matrixr1[3] = 0.0f;
        matrixr1[4] = cosd(angle1);
        matrixr1[5] = sind(angle1);
        matrixr1[6] = 0.0f;
        matrixr1[7] = -sind(angle1);
        matrixr1[8] = cosd(angle1);

        float[] zyx = new float[9];

        Mat matr1 = new Mat(3, 3, CvType.CV_64FC1);
        Mat matr2 = new Mat(3, 3, CvType.CV_64FC1);
        Mat matr3 = new Mat(3, 3, CvType.CV_64FC1);
        for (int i = 0; i < 9; i++) {
            matr1.put(i % 3, i / 3, matrixr1[i]);
            matr2.put(i % 3, i / 3, matrixr2[i]);
            matr3.put(i % 3, i / 3, matrixr3[i]);
        }
        Mat matyx = new Mat();
        Mat matzyx = new Mat();
        Core.multiply(matr2, matr1, matyx);
        Core.multiply(matr3, matyx, matzyx);
        for (int i = 0; i < 9; i++) {
            double[] val = matzyx.get(i % 3, i / 3);
            zyx[i] = (float)val[0];
        }
        return zyx;
    }

    public static float sind(float angle) {
        double angleinradius = Math.PI * (double)angle / 180.0;
        return (float)Math.sin(angleinradius);
    }

    public static float cosd(float angle) {
        double angleinradius = Math.PI * (double)angle / 180.0;
        return (float)Math.cos(angleinradius);
    }

    @Override
    public void draw(GL10 gl) {
        Log.d("DDL", "drawing");
       if (!setModeled) {
       //   guy.setModel(R.raw.tinker, activity);
           setModeled = true;
       }
        //  this.drawCount += 1;
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

    //    float xangle;
    //    if (beaconAngle.get(activity.getClosestUUID()) == null) {
    //        xangle = 0.0f;
    //    }
    //    else {
    //        xangle = (float)beaconAngle.get(activity.getClosestUUID());
    //    }

    //    Matrix.rotateM(m, 0, m, 0, xangle, 0,0,1);
        reset();

        gl.glLoadMatrixf(m, 0);
        gl.glPushMatrix();
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);

        guy.currlen = this.activity.getCurrentLen();

        guy.currGes = this.activity.getCurrentGes();
        guy.currspeed = this.activity.getCurrspeed();

        guy.draw(gl);
        gl.glPopMatrix();

        // TODO 03/26: temporarly disable spinning
          if (spinning) angle += 5.0f;
    }

    public void resetPeople() {
        guy.count = 0;
        guy.id = 0;
    }

    // test
    public void testAnglesToM() {
        Log.d("T", "display angles 1:" );
        displayAngles();
        float[] currRotateM = anglesToM(new float[]{angle1, angle2, angle3});
        Log.d("T", "rotate M: " + String.valueOf(currRotateM[0]) + " " + String.valueOf(currRotateM[1]) + " " +
        String.valueOf(currRotateM[2]) + " " + String.valueOf(currRotateM[3]) + " " + String.valueOf(currRotateM[4]) + " " +
        String.valueOf(currRotateM[5]) + " " + String.valueOf(currRotateM[6]) + " " + String.valueOf(currRotateM[7]) + " " +
        String.valueOf(currRotateM[8]));
        Matrix.rotateM(cameraM, 0, cameraM, 0, 5, 0,0,1);
        Matrix.invertM(m, 0, cameraM, 0);
        float[] angles = mToAngles(new float[]{cameraM[0], cameraM[1], cameraM[2], cameraM[4], cameraM[5], cameraM[6], cameraM[8], cameraM[9], cameraM[10]});
        angle1 = radToDegree(angles[0]);
        angle2 = radToDegree(angles[1]);
        angle3 = radToDegree(angles[2]);

        Log.d("T", "display angles 2:" );
        displayAngles();
    }

    public void displayAngles() {
        Log.d("T", "angles: " + String.valueOf(angle1) + " " + String.valueOf(angle2) + " " + String.valueOf(angle3));
    }
}