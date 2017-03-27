package com.example.chongshao_mikasa.ble_fingerprint_java;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 11/13/16.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Rectangle rect;
    private float mAngle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        rect = new Rectangle();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);

        // make adjustments for screen ratio
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
        gl.glLoadIdentity();                        // reset the matrix to its default state
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);  // apply the projection matrix
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        // Draw background color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Set GL_MODELVIEW transformation mode
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();   // reset the matrix to its default state

        // When using GL_MODELVIEW, you must set the view point
        GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Use the following code to generate constant rotation.
        // Leave this code out when using TouchEvents.
        //     long time = SystemClock.uptimeMillis() % 4000L;
        //    float angle = 0.090f * ((int) time);
        gl.glRotatef(mAngle, 0.0f, 0.0f, 1.0f);
        rect.draw(gl);
    }

//    public static int loadShader(int type, String shaderCode){
//
//        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
//        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
//        int shader = GLES20.glCreateShader(type);
//
//        // add the source code to the shader and compile it
//        GLES20.glShaderSource(shader, shaderCode);
//        GLES20.glCompileShader(shader);
//
//        return shader;
//    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
}
