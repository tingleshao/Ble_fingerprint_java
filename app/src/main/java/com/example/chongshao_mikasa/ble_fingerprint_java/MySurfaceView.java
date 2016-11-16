package com.example.chongshao_mikasa.ble_fingerprint_java;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by chongshao-mikasa on 11/13/16.
 */

public class MySurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    // AR renderer
    //   private SimpleRenderer sr;

    public MySurfaceView(Context ctx, AttributeSet attr) {
        super(ctx,attr);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
        Log.d("T", "My Surface View Init!!!");
        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    //   public void setSR(SimpleRenderer sr) {
    //     this.sr = sr;
    //   }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, we are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRenderer.setAngle(
                        mRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                //        Log.d("T", "new angle: " + String.valueOf((mRenderer.getAngle() +
                //               ((dx + dy) * TOUCH_SCALE_FACTOR)) % 360));
                //             sr.setRAngle((mRenderer.getAngle() +
                //                    ((dx + dy) * TOUCH_SCALE_FACTOR)) % 360);
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}
