package com.example.chongshao_mikasa.ble_fingerprint_java;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import android.util.Log;

import org.artoolkit.ar.base.camera.CameraEventListener;
import org.artoolkit.ar.base.camera.CaptureCameraPreview;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chongshao-mikasa on 11/17/16.
 */

public class MyCaptureCameraPreview extends CaptureCameraPreview {
    /**
     * Constructor takes a {@link CameraEventListener} which will be called on
     * to handle camera related events.
     *
     * @param activity
     * @param cel      CameraEventListener to use. Can be null.
     */
    public MyCaptureCameraPreview(Activity activity, CameraEventListener cel) {
        super(activity, cel);
    }

    private Bitmap bitmap;

    @Override
    public void onPreviewFrame (byte[] data, Camera camera) {
        super.onPreviewFrame(data, camera);
        Camera.Parameters parameters = camera.getParameters();
        int width = parameters.getPreviewSize().width;
        int height = parameters.getPreviewSize().height;

        YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuv.compressToJpeg(new Rect(0, 0, width, height), 50, out);

        byte[] bytes = out.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    //    Bitmap bitmap = Bitmap.createBitmap(r.width(), r.height(), Bitmap.Config.ARGB_8888);
      //  Allocation bmData = renderScriptNV21ToRGBA888(
//                mContext,
//                r.width(),
//                r.height(),
//                data);
//        bmData.copyTo(bitmap);
    }

    public Allocation renderScriptNV21ToRGBA888(Context context, int width, int height, byte[] nv21) {
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicYuvToRGB yuvToRgbIntrinsic = ScriptIntrinsicYuvToRGB.create(rs, Element.U8_4(rs));

        Type.Builder yuvType = new Type.Builder(rs, Element.U8(rs)).setX(nv21.length);
        Allocation in = Allocation.createTyped(rs, yuvType.create(), Allocation.USAGE_SCRIPT);

        Type.Builder rgbaType = new Type.Builder(rs, Element.RGBA_8888(rs)).setX(width).setY(height);
        Allocation out = Allocation.createTyped(rs, rgbaType.create(), Allocation.USAGE_SCRIPT);

        in.copyFrom(nv21);

        yuvToRgbIntrinsic.setInput(in);
        yuvToRgbIntrinsic.forEach(out);
        return out;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
