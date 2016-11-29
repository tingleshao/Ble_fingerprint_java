package com.example.chongshao_mikasa.ble_fingerprint_java;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.camera.CaptureCameraPreview;
import org.artoolkit.ar.base.rendering.ARRenderer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import android.view.ViewGroup.LayoutParams;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.KalmanFilter;

import static org.opencv.core.Core.norm;
import static org.opencv.core.Core.normalize;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imdecode;


public class MainActivity extends ARActivity implements SensorEventListener  {

    Core.MinMaxLocResult minmax;

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 133;

    private static int[][] fingerprintDatabase = new int[][]{
            {-74 -74 -75 -77 -83 -69 -78 -78},
            {-74 -74 -74 -76 -82 -72 -78 -76},
            {-68 -77 -74 -77 -84 -76 -84 -77},
            {-69 -74 -75 -85 -85 -72 -74 -77},
            {-68 -70 -74 -86 -79 -72 -77 -84},
            {-68 -72 -77 -88 -79 -78 -83 -85},
            {-71 -82 -77 -85 -82 -75 -81 -81},
            {-66 -80 -76 -83 -83 -78 -79 -81},
            {-68 -77 -76 -80 -83 -77 -75 -80},
            {-75 -75 -81 -79 -80 -78 -77 -80},
            {-81 -75 -79 -82 -79 -81 -78 -80},
            {-81 -75 -79 -82 -79 -81 -78 -80},
            {-73 -70 -80 -80 -86 -77 -77 -82},
            {-73 -70 -81 -74 -86 -74 -77 -82},
            {-72 -71 -79 -83 -85 -78 -75 -83},
            {-69 -70 -79 -80 -85 -76 -78 -75},
            {-69 -72 -80 -80 -85 -76 -77 -76},
            {-71 -73 -82 -80 -85 -76 -76 -75},
            {-74 -77 -78 -81 -81 -69 -80 -77},
            {-74 -73 -77 -81 -81 -68 -77 -77},
            {-72 -69 -76 -80 -81 -67 -75 -76},
            {-73 -75 -76 -82 -81 -80 -75 -78},
            {-72 -74 -76 -82 -81 -78 -75 -78},
            {-72 -72 -76 -81 -80 -74 -77 -78},
            {-77 -75 -79 -83 -80 -82 -77 -80},
            {-77 -77 -78 -83 -80 -78 -78 -80},
            {-78 -81 -77 -84 -80 -74 -79 -81},
            {-79 -71 -79 -81 -88 -77 -76 -81},
            {-77 -68 -81 -80 -86 -79 -77 -80},
            {-76 -71 -81 -81 -86 -81 -77 -81},
            {-70 -75 -77 -78 -79 -80 -77 -81},
            {-71 -76 -77 -76 -80 -81 -77 -81},
            {-71 -76 -76 -75 -80 -79 -79 -81},
            {-70 -76 -81 -81 -81 -75 -81 -77},
            {-70 -76 -80 -82 -81 -77 -80 -75},
            {-69 -76 -78 -83 -81 -78 -80 -74},
            {-72 -72 -78 -82 -79 -78 -80 -80},
            {-70 -71 -80 -81 -77 -74 -78 -80},
            {-70 -71 -81 -81 -77 -73 -77 -80},
            {-69 -78 -79 -82 -81 -74 -81 -79},
            {-73 -84 -77 -78 -81 -72 -81 -81},
            {-73 -84 -76 -78 -78 -71 -81 -81},
            {-74 -69 -78 -76 -80 -69 -79 -79},
            {-76 -72 -76 -79 -79 -76 -80 -79},
            {-76 -73 -76 -80 -79 -77 -80 -83},
            {-72 -74 -78 -77 -85 -74 -79 -80},
            {-75 -70 -78 -84 -86 -70 -76 -86},
            {-75 -71 -78 -84 -86 -69 -76 -86},
            {-71 -73 -76 -85 -82 -81 -85 -83},
            {-69 -76 -76 -82 -80 -81 -86 -82},
            {-69 -78 -75 -79 -79 -80 -86 -82},
            {-64 -71 -73 -83 -81 -80 -83 -76},
            {-64 -71 -73 -84 -81 -79 -82 -76},
            {-64 -70 -72 -84 -84 -79 -83 -75},
            {-67 -76 -72 -81 -81 -78 -78 -80},
            {-70 -76 -72 -81 -81 -78 -77 -80},
            {-71 -75 -72 -81 -83 -82 -75 -80},
            {-75 -75 -77 -81 -83 -72 -77 -80},
            {-77 -76 -76 -85 -83 -72 -77 -79},
            {-81 -76 -77 -85 -81 -73 -83 -79},
            {-76 -77 -78 -82 -78 -78 -79 -81},
            {-75 -75 -76 -79 -78 -76 -80 -81},
            {-75 -70 -73 -77 -77 -75 -76 -81},
            {-67 -80 -79 -83 -79 -73 -87 -81},
            {-67 -79 -78 -83 -79 -72 -87 -81},
            {-67 -79 -77 -83 -79 -71 -87 -84},
            {-70 -75 -78 -79 -75 -74 -78 -80},
            {-70 -73 -73 -76 -87 -75 -83 -82},
            {-71 -73 -73 -75 -87 -76 -84 -82},
            {-69 -72 -74 -82 -84 -70 -81 -79},
            {-73 -72 -76 -85 -85 -69 -78 -83},
            {-75 -70 -80 -87 -86 -75 -76 -83},
            {-74 -82 -81 -83 -85 -76 -81 -86},
            {-74 -86 -82 -83 -86 -78 -80 -85},
            {-72 -79 -80 -84 -84 -75 -78 -83},
            {-75 -76 -77 -77 -79 -79 -82 -80},
            {-77 -76 -79 -76 -77 -79 -84 -82},
            {-77 -76 -79 -76 -77 -77 -85 -82},
            {-76 -76 -73 -76 -75 -75 -82 -83},
            {-74 -76 -75 -77 -74 -73 -80 -81},
            {-74 -76 -77 -78 -74 -71 -79 -81},
            {-73 -78 -79 -75 -78 -74 -85 -82},
            {-73 -82 -76 -74 -80 -75 -86 -83},
            {-72 -79 -76 -75 -79 -77 -86 -86},
            {-77 -76 -79 -77 -77 -76 -81 -84},
            {-74 -74 -79 -86 -76 -74 -83 -83},
            {-73 -72 -82 -85 -76 -75 -80 -82},
            {-74 -78 -81 -82 -85 -72 -85 -83},
            {-75 -78 -78 -82 -85 -72 -83 -81},
            {-74 -78 -75 -81 -79 -73 -82 -79},
            {-74 -68 -74 -77 -77 -67 -88 -85},
            {-74 -69 -73 -76 -77 -67 -86 -85},
            {-69 -76 -73 -82 -77 -74 -81 -85},
            {-67 -76 -77 -76 -86 -77 -77 -89},
            {-69 -73 -78 -76 -80 -80 -80 -76},
            {-69 -73 -76 -77 -79 -83 -79 -76},
            {-79 -73 -75 -84 -78 -77 -79 -81},
            {-79 -71 -76 -81 -78 -78 -78 -81},
            {-78 -70 -76 -80 -78 -80 -79 -81},
            {-70 -73 -76 -81 -78 -79 -83 -83},
            {-69 -71 -75 -78 -79 -75 -85 -81},
            {-69 -72 -76 -74 -80 -75 -85 -81},
            {-70 -75 -84 -78 -77 -75 -83 -83},
            {-70 -75 -85 -80 -80 -76 -87 -83},
            {-69 -80 -81 -84 -80 -77 -88 -83},
            {-72 -80 -76 -80 -77 -72 -79 -86},
            {-73 -81 -74 -81 -76 -72 -78 -85},
            {-75 -81 -73 -82 -76 -71 -75 -85},
            {-73 -78 -76 -81 -82 -77 -82 -80}};
    private static String[] locations = new String[]{"00", "01", "02", "03", "04", "05", "06",
                                      "10", "11", "12", "13", "14", "15",
                                      "20", "21", "22", "23", "24", "25",
                                      "30", "31", "32", "33", "34", "35",
                                      "40", "41", "42", "43", "44", "45",
                                      "50", "51", "52", "53", "54", "55"};


    private BeaconManager beaconManager;
    private Region region0;
    private Region region1;
    private Region region2;
    private Region region3;
    private Region region4;
    private Region region5;
    private Region region6;
    private Region region7;

    TextView beacon0;
    TextView beacon1;
    TextView beacon2;
    TextView beacon3;
    TextView beacon4;
    TextView beacon5;
    TextView beacon6;
    TextView beacon7;

    private String closestUUID = "no";
    private Map<String, Integer> beaconDistance = new HashMap<>();
    private SimpleRenderer simpleRenderer = new SimpleRenderer();
    Map<String, Integer> beaconAngle = new HashMap<String, Integer>();
    Map<String, String> beaconUUID = new HashMap<>();

    // IMU related variables
    private SensorManager mSensorManager;
    private Sensor mRotationVectorSensor, mAccelerationSensor;

    private final float[] mRotationMatrix = new float[16];
    private volatile float[] mAccelerometerMatrix = new float[4];

    private float rotationX = 0;
    private float rotationY = 0;
    private float rotationZ = 0;

    private TextView rotationMsg;

    public String getClosestUUID() {
        return closestUUID;
    }

    CaptureCameraPreview cam;
    Button button;
    byte[] myframe;

    private MyCaptureCameraPreview preview;
    FrameLayout mainLayout2;
    ImageView imageView;

    Mat image;
    Mat mask;
    FeatureDetector detector;

    // control translation
    Button xup;
    Button xdown;
    Button yup;
    Button ydown;
    Button zup;
    Button zdown;

    // control rotation
    Button r1up;
    Button r1down;
    Button r2up;
    Button r2down;
    Button r3up;
    Button r3down;

    // record fingerprint
    private String filename = "fingerprint.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    Button record;
    Spinner spinner;
    Button read;

    // camera pose estimation
    private Mat currCameraPoseFromCam;
    private Mat currCameraPoseFromBeacon;
    private Mat currCameraPostFromIMU; //TODO: update the unit

    // match fingerprint
    Button match;

    // test
    Button test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleRenderer.setActivity(this);

        mainLayout = (FrameLayout)this.findViewById(R.id.mainLayout);
        mainLayout2 = (FrameLayout)this.findViewById(R.id.mainLayout2);
        imageView = new ImageView(this);
        mainLayout2.addView(imageView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        beacon0 = (TextView)this.findViewById(R.id.beacon0);
        beacon1 = (TextView)this.findViewById(R.id.beacon1);
        beacon2 = (TextView)this.findViewById(R.id.beacon2);
        beacon3 = (TextView)this.findViewById(R.id.beacon3);
        beacon4 = (TextView)this.findViewById(R.id.beacon4);
        beacon5 = (TextView)this.findViewById(R.id.beacon5);
        beacon6 = (TextView)this.findViewById(R.id.beacon6);
        beacon7 = (TextView)this.findViewById(R.id.beacon7);

        beaconManager = new BeaconManager(this);

        region0 = new Region("ranged region",
                UUID.fromString("a75fa152-a904-4502-8ea8-192f8fcfee6a"), 37491, 43355); //1
        region1 = new Region("ranged region",
                UUID.fromString("9795a656-a244-47f5-b8ab-a24cf9728976"), 12703, 41115); //15
        region2 = new Region("ranged region",
                UUID.fromString("58deb431-0387-4aff-b04d-bf773f2409cc"), 918, 44776); //2
        region3 = new Region("ranged region",
                UUID.fromString("e14f37ee-cd9c-41a1-b145-134570f9a8e8"), 41600, 6645); //3
        region4 = new Region("ranged region",
                UUID.fromString("b6fc3980-846c-4f48-bfc5-2b2e0ca2d702"), 2143, 20816); //4
        region5 = new Region("ranged region",
                UUID.fromString("9aa9bca6-b207-41f5-a076-d294c9b374db"), 49523, 36217); //5
        region6 = new Region("ranged region",
                UUID.fromString("21f158a6-a083-4020-9b40-8cd34380ffc3"), 16355, 40884); //6
        region7 = new Region("ranged region",
                UUID.fromString("7691f1bd-284f-439d-8b1a-d223f0249b9b"), 30448, 19478); //7

        beaconAngle.put("a75fa152-a904-4502-8ea8-192f8fcfee6a", 0);
        beaconAngle.put("9795a656-a244-47f5-b8ab-a24cf9728976", 45);
        beaconAngle.put("58deb431-0387-4aff-b04d-bf773f2409cc", 90);
        beaconAngle.put("e14f37ee-cd9c-41a1-b145-134570f9a8e8", 135);
        beaconAngle.put("b6fc3980-846c-4f48-bfc5-2b2e0ca2d702", 180);
        beaconAngle.put("9aa9bca6-b207-41f5-a076-d294c9b374db", 225);
        beaconAngle.put("21f158a6-a083-4020-9b40-8cd34380ffc3", 270);
        beaconAngle.put("7691f1bd-284f-439d-8b1a-d223f0249b9b", 315);


        beaconUUID.put("0", "a75fa152-a904-4502-8ea8-192f8fcfee6a");
        beaconUUID.put("1", "9795a656-a244-47f5-b8ab-a24cf9728976");
        beaconUUID.put("2", "58deb431-0387-4aff-b04d-bf773f2409cc");
        beaconUUID.put("3", "e14f37ee-cd9c-41a1-b145-134570f9a8e8");
        beaconUUID.put("4", "b6fc3980-846c-4f48-bfc5-2b2e0ca2d702");
        beaconUUID.put("5", "9aa9bca6-b207-41f5-a076-d294c9b374db");
        beaconUUID.put("6", "21f158a6-a083-4020-9b40-8cd34380ffc3");
        beaconUUID.put("7", "7691f1bd-284f-439d-8b1a-d223f0249b9b");

        beaconDistance.put("0", -100);
        beaconDistance.put("1", -100);
        beaconDistance.put("2", -100);
        beaconDistance.put("3", -100);
        beaconDistance.put("4", -100);
        beaconDistance.put("5", -100);
        beaconDistance.put("6", -100);
        beaconDistance.put("7", -100);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    for (Beacon beacon : list) {
                        if (beacon.getProximityUUID().equals(UUID.fromString("a75fa152-a904-4502-8ea8-192f8fcfee6a"))) { // 1
                            Log.d("Airport", "Beacon 0 dist: " + String.valueOf(beacon.getRssi()));
                            beacon0.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("0", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("9795a656-a244-47f5-b8ab-a24cf9728976"))) { // 15
                            Log.d("Airport", "Beacon 1 dist: " + String.valueOf(beacon.getRssi()));
                            beacon1.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("1", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("58deb431-0387-4aff-b04d-bf773f2409cc"))) { // 2
                            Log.d("Airport", "Beacon 2 dist: " + String.valueOf(beacon.getRssi()));
                            beacon2.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("2", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("e14f37ee-cd9c-41a1-b145-134570f9a8e8"))) { // 3
                            Log.d("Airport", "Beacon 3 dist: " + String.valueOf(beacon.getRssi()));
                            beacon3.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("3", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("b6fc3980-846c-4f48-bfc5-2b2e0ca2d702"))) { // 4
                            Log.d("Airport", "Beacon 4 dist: " + String.valueOf(beacon.getRssi()));
                            beacon4.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("4", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("9aa9bca6-b207-41f5-a076-d294c9b374db"))) { // 5
                            Log.d("Airport", "Beacon 5 dist: " + String.valueOf(beacon.getRssi()));
                            beacon5.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("5", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("21f158a6-a083-4020-9b40-8cd34380ffc3"))) { // 6
                            Log.d("Airport", "Beacon 6 dist: " + String.valueOf(beacon.getRssi()));
                            beacon6.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("6", beacon.getRssi());
                        }
                        if (beacon.getProximityUUID().equals(UUID.fromString("7691f1bd-284f-439d-8b1a-d223f0249b9b"))) { // 7
                            Log.d("Airport", "Beacon 7 dist: " + String.valueOf(beacon.getRssi()));
                            beacon7.setText("distance: "+ String.valueOf(beacon.getRssi()));
                            beaconDistance.put("7", beacon.getRssi());
                        }
                    }
                }
                updateARObjDirection();
            }
        });

        if (!checkCameraPermission()) {
            //if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) { //ASK EVERY TIME - it's essential!
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }

        // When the screen is tapped, inform the renderer and vibrate the phone
        mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                simpleRenderer.click();
                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(40);
            }
        });

        //IMU related stuff
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mRotationVectorSensor = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ROTATION_VECTOR);
        mAccelerationSensor = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mRotationVectorSensor, 10000);
        mSensorManager.registerListener(this, mAccelerationSensor, 5000);
        rotationMsg = (TextView)this.findViewById(R.id.rotationMsg);

        //camera stuff
        cam = this.getCameraPreview();

        button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam = MainActivity.this.getCameraPreview();
                if (cam != null) {
                    Bitmap bitmap1 = MainActivity.this.preview.getBitmap();

                 //   Bitmap bitmap1g = toGrayscale(bitmap1);
                    Bitmap bitmap1f = locateFeaturePoint(bitmap1);

                    Bitmap bitmap2 =  BitmapFactory.decodeResource(getResources(), R.drawable.sample0);
                    Bitmap bitmap2g = toGrayscale(bitmap2);
                    Bitmap combinedBitmap = estimatePose(bitmap1, bitmap2g);

                    if (combinedBitmap!=null) {
                        imageView.setImageBitmap(combinedBitmap);
                    }
                    else {
                        Log.d("T", "bm null!"+ String.valueOf(MainActivity.this.myframe.length));
                    }
                    Log.d("T", "cam not null");
                }
                else {
                    Log.d("T", "cam null");
                }
            }
        });

        // control translation
        xup = (Button)this.findViewById(R.id.button2);
        xdown = (Button)this.findViewById(R.id.button4);
        yup = (Button)this.findViewById(R.id.button5);
        ydown = (Button)this.findViewById(R.id.button6);
        zup = (Button)this.findViewById(R.id.button7);
        zdown = (Button)this.findViewById(R.id.button8);

        xup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.xup();
            }
        });
        xdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.xdown();
            }
        });
        yup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.yup();
            }
        });
        ydown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.ydown();
            }
        });
        zup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.zup();
            }
        });
        zdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.zdown();
            }
        });

        // control rotation
        r1up = (Button)this.findViewById(R.id.button9);
        r1down = (Button)this.findViewById(R.id.button12);
        r2up = (Button)this.findViewById(R.id.button10);
        r2down = (Button)this.findViewById(R.id.button13);
        r3up = (Button)this.findViewById(R.id.button11);
        r3down = (Button)this.findViewById(R.id.button14);
        r1up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.rotate1(5);
            }
        });
        r1down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.rotate1(-5);
            }
        });
        r2up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.rotate2(5);
            }
        });
        r2down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.rotate2(-5);
            }
        });
        r3up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.rotate3(5);
            }
        });
        r3down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.rotate3(-5);
            }
        });

        // record fingerprint
        record = (Button)this.findViewById(R.id.record);
        record.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile, true);
                    ArrayList<Integer> fingerprint = MainActivity.this.getCurrentFingerPrint();
                    String entry = MainActivity.this.generateEntry(fingerprint);
                    fos.write(entry.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            record.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }
        spinner = (Spinner)this.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fingerprints_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        read = (Button)this.findViewById(R.id.read);
        read.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("T", "database: " + MainActivity.this.readFingerPrintDatabase());
            }
        }));

        // match fingerprint
        match = (Button)this.findViewById(R.id.match);
        match.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("T", "match:");
            }
        }));

        // test
        test = (Button)this.findViewById(R.id.test);
        test.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.simpleRenderer.testAnglesToM();
            }
        }));
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                    image = new Mat();
                    mask = new Mat();
                    detector = FeatureDetector.create(FeatureDetector.HARRIS);
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    private static Mat readInputStreamIntoMat(InputStream inputStream) throws IOException {
        // Read into byte-array
        byte[] temporaryImageInMemory = readStream(inputStream);
        // Decode into mat. Use any IMREAD_ option that describes your image appropriately
        Mat outputImage = imdecode(new MatOfByte(temporaryImageInMemory), IMREAD_GRAYSCALE);

        return outputImage;
    }

    private static byte[] readStream(InputStream stream) throws IOException {
        // Copy content of the image to byte-array
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] temporaryImageInMemory = buffer.toByteArray();
        buffer.close();
        stream.close();
        return temporaryImageInMemory;
    }

    public Bitmap locateFeaturePoint(Bitmap input) {
        Utils.bitmapToMat(input, image);
        Mat imagei = new Mat();
        Imgproc.cvtColor(image,  imagei, Imgproc.COLOR_RGBA2GRAY);

        MatOfKeyPoint keypoints = new MatOfKeyPoint();
        detector.detect(imagei, keypoints);

        Log.d("T", "image width:" + String.valueOf(imagei.width()) + " " + String.valueOf(imagei.height()));
        Log.d("T", "keypoints size: " + String.valueOf(keypoints.size()));

        Mat outputImage = new Mat();
        Scalar color = new Scalar(0, 0, 255); // BGR
        int flags = Features2d.DRAW_RICH_KEYPOINTS; // For each keypoint, the circle around keypoint with keypoint size and orientation will be drawn.
        Features2d.drawKeypoints(imagei, keypoints, outputImage, color , flags);
        Utils.matToBitmap(outputImage, input);

        return input;
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    private void updateARObjDirection() {
        // Construct a map between beacon and angle
        int largeRssi = -100;
        String largeId = "-1";
        for (int i = 0; i < 8; i++) {
            if (beaconDistance.get(String.valueOf(i)) > largeRssi) {
                largeId = String.valueOf(i);
                largeRssi = beaconDistance.get(String.valueOf(i));
            }
        }
        this.closestUUID = beaconUUID.get(largeId);
        return;
    }

    @Override
    public void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region0);
                beaconManager.startRanging(region1);
                beaconManager.startRanging(region2);
                beaconManager.startRanging(region3);
                beaconManager.startRanging(region4);
                beaconManager.startRanging(region5);
                beaconManager.startRanging(region6);
                beaconManager.startRanging(region7);
            }
        });

        preview = new MyCaptureCameraPreview(this, this);
        mainLayout.addView(preview, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        // Load OpenCV
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d("OpenCV", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region0);
        beaconManager.stopRanging(region1);
        beaconManager.stopRanging(region2);
        beaconManager.stopRanging(region3);
        beaconManager.stopRanging(region4);
        beaconManager.stopRanging(region5);
        beaconManager.stopRanging(region6);
        beaconManager.stopRanging(region7);
        super.onPause();
        mainLayout.removeView(preview);
    }

//    private List<String> placesNearBeacon(Beacon beacon) {
//        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
//        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
//            return PLACES_BY_BEACONS.get(beaconKey);
//        }
//        return Collections.emptyList();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private double computeDistance(Beacon beacon) {
        int txPower = beacon.getMeasuredPower();
        int rssi = beacon.getRssi();
        return Math.pow(10d, ((double) txPower - rssi) / (10 * 2));
    }

    @Override
    protected ARRenderer supplyRenderer() {
        if (!checkCameraPermission()) {
            Toast.makeText(this, "No camera permission - restart the app", Toast.LENGTH_LONG).show();
            return null;
        }
        return simpleRenderer;
    }

    @Override
    protected FrameLayout supplyFrameLayout() {
        return this.mainLayout;
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    // IMU related methods
    public void onSensorChanged(SensorEvent event) {
  //      Log.d("T", "sensor changed!");
        // we received a sensor event. it is a good practice to check
        // that we received the proper event
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mAccelerometerMatrix[0] = event.values[0];
            mAccelerometerMatrix[1] = event.values[1];
            mAccelerometerMatrix[2] = event.values[2];
            mAccelerometerMatrix[3] = 0;
        }

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            // convert the rotation-vector to a 4x4 matrix. the matrix
            // is interpreted by Open GL as the inverse of the
            // rotation-vector, which is what we want.

            SensorManager.getRotationMatrixFromVector(
                    mRotationMatrix, event.values);
            float currRotationX = event.values[0];
            float currRotationY = event.values[1];
            float currRotationZ = event.values[2];

            float diffRotationX = rotationX - currRotationX;
            float diffRotationY = rotationY - currRotationY;
            float diffRotationZ = rotationZ - currRotationZ;
       //     Log.d("T", "rotation detected!" + String.valueOf(diffRotationX) + " " + String.valueOf(diffRotationY) + " " + String.valueOf(diffRotationZ));
            MainActivity.this.simpleRenderer.rotate1(-diffRotationX*50.0f);
            MainActivity.this.simpleRenderer.rotate2(-diffRotationY*50.0f);
            MainActivity.this.simpleRenderer.rotate3(-diffRotationZ*50.0f);

            rotationX = currRotationX;
            rotationY = currRotationY;
            rotationZ = currRotationZ;

            rotationMsg.setText("rotation x: " + String.valueOf(rotationX) + "\n y: " +
                    String.valueOf(rotationY) + "\n z: " + String.valueOf(rotationZ));
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
//
//    @Override
//    public void cameraPreviewFrame(byte[] frame) {
//        super.cameraPreviewFrame(frame);
//     //   Log.d("T", "framelength:" + String.valueOf(frame.length));
//        this.myframe = frame;
//        this.getCameraPreview();
//    }

    @Override
    public CaptureCameraPreview getCameraPreview() {
        return preview;
    }

    // Camera pose related stuff
    public Bitmap estimatePose(Bitmap bitmap1, Bitmap bitmap2) {
        Mat mat1 = new Mat();
        Mat mat2 = new Mat();
        Utils.bitmapToMat(bitmap1, mat1);
        Utils.bitmapToMat(bitmap2, mat2);

        // TODO: convert RGB to GRAY
        MatOfDMatch matches = new MatOfDMatch();
        MatOfDMatch gm = new MatOfDMatch();

        LinkedList<DMatch> goodMatches = new LinkedList<>();
        LinkedList<Point> objList = new LinkedList<>();
        LinkedList<Point> sceneList = new LinkedList<>();

        MatOfKeyPoint keyPointsObject = new MatOfKeyPoint();
        MatOfKeyPoint keyPointScene = new MatOfKeyPoint();

        Mat descriptorsObject = new Mat();
        Mat descriptorsScene = new Mat();

        MatOfPoint2f obj = new MatOfPoint2f();
        MatOfPoint2f scene = new MatOfPoint2f();

        FeatureDetector fd = FeatureDetector.create(FeatureDetector.ORB);
        fd.detect(mat1, keyPointsObject);
        fd.detect(mat2, keyPointScene); // find orb features

        int con = 3;
        DescriptorExtractor extractor = DescriptorExtractor.create(con);
        extractor.compute(mat1, keyPointsObject, descriptorsObject); // extract descriptor
        extractor.compute(mat2, keyPointScene, descriptorsScene);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        matcher.match(descriptorsObject, descriptorsScene, matches); // match descriptor

        double maxDist = 0;
        double minDist = 100;
        List<DMatch> matchesList = matches.toList();
        for (int i = 0; i < descriptorsObject.rows(); i++) {
            Double dist = (double) matchesList.get(i).distance;
            if( dist < minDist ) minDist = dist;
            if( dist > maxDist ) maxDist = dist;
        }
        for(int i = 0; i < descriptorsObject.rows(); i++) {
            if(matchesList.get(i).distance < 3 * minDist){
                goodMatches.addLast(matchesList.get(i)); // get good matches
            }
        }

        gm.fromList(goodMatches);

        List<KeyPoint> keyPointsObjectList = keyPointsObject.toList();
        List<KeyPoint> keyPointsSceneList = keyPointScene.toList();
        for(int i = 0; i< goodMatches.size(); i++) {
            objList.addLast(keyPointsObjectList.get(goodMatches.get(i).queryIdx).pt);
            sceneList.addLast(keyPointsSceneList.get(goodMatches.get(i).trainIdx).pt);
        }

        obj.fromList(objList);
        scene.fromList(sceneList);

        Mat outputImage = new Mat();
        Bitmap comboMap = combineImages(bitmap1, bitmap2);
        Utils.bitmapToMat(comboMap, outputImage);

        Mat mat1rgb = new Mat();
        Mat mat2rgb = new Mat();
        Imgproc.cvtColor(mat1, mat1rgb, Imgproc.COLOR_RGBA2RGB, 1);
        Imgproc.cvtColor(mat2, mat2rgb, Imgproc.COLOR_RGBA2RGB, 1);
        Features2d.drawMatches(mat1rgb, keyPointsObject, mat2rgb, keyPointScene, gm, outputImage);

        Bitmap bitmap = Bitmap.createBitmap(outputImage.cols(), outputImage.rows(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(outputImage, bitmap);

        // TODO: old code here... not sure how to use it.
        Mat matH = Calib3d.findHomography(obj, scene);
        updateCameraPoseEstimation(matH);

   //     Mat warping = mat1.clone();
   //     org.opencv.core.Size ims = new org.opencv.core.Size(mat1.cols(),mat1.rows());
   //     Imgproc.warpPerspective(mat1, warping , matH, ims);

        return bitmap;
    }

    public void updateCameraPoseEstimation(Mat matH) {
        // TODO: check here if the pointer approach is working
        Mat pose = Mat.eye(3, 4, CvType.CV_32FC1);
        float norm1 = (float)norm(matH.col(0));
        float norm2 = (float)norm(matH.col(1));
        float tnorm = (norm1 + norm2) / 2.0f;

        Mat p1 = matH.col(0);
        Mat p2 = pose.col(0);
        normalize(p1, p2);

        p1 = matH.col(1);
        p2 = pose.col(2);
        normalize(p1, p2);

        p1 = pose.col(0);
        p2 = pose.col(1);

        Mat p3 = p1.cross(p2);
        Mat c2 = pose.col(2);

        p3.copyTo(c2);
        Core.divide(matH.col(2), new Scalar(tnorm), p3);
        c2 = pose.col(3);
        p3.copyTo(c2);
        currCameraPoseFromCam = pose;
    }


    public Bitmap combineImages(Bitmap c, Bitmap s) {
        Bitmap cs = null;
        int width, height = 0;

        if(c.getWidth() > s.getWidth()) {
            width = c.getWidth() + s.getWidth();
            height = c.getHeight();
        } else {
            width = s.getWidth() + s.getWidth();
            height = c.getHeight();
        }
        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, c.getWidth(), 0f, null);
        return cs;
    }


    public String matchFingerprint(Integer[] fingerprint) {
        int minSSD = 80000;
        int minSSDid = -1;
        for (int k = 0; k < fingerprintDatabase.length; k++) {
            int[] dbEntry = fingerprintDatabase[k];
            int currSSD = 0;
            for (int j = 0; j < 8; j++) {
                currSSD = currSSD + (int)Math.pow((double)(dbEntry[j] - fingerprint[j]), 2.0);
                if (currSSD < minSSD) {
                    minSSD = currSSD;
                    minSSDid = k;
                }
            }
        }
        return locations[minSSDid/3];
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private ArrayList<Integer> getCurrentFingerPrint() {
        ArrayList<Integer> currentFingerPrint = new ArrayList<>();
        if (beacon0.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon0.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon1.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon1.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon2.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon2.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon3.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon3.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon4.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon4.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon5.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon5.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon6.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon6.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        if (beacon7.getText().toString().equals("TextView")) {
            currentFingerPrint.add(-100);
        } else {
            Integer rssi = Integer.parseInt(beacon7.getText().toString().split(" ")[1]);
            currentFingerPrint.add(rssi);
        }
        return currentFingerPrint;
    }

    public String generateEntry(ArrayList<Integer> fingerprint) {
        String location = this.spinner.getSelectedItem().toString();
        String entry = "L"+location;
        for (Integer item : fingerprint) {
            entry = entry + " " + String.valueOf(item);
        }
        entry = entry + "#";
        return entry;
    }

    public String readFingerPrintDatabase() {
        String myData = "";
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            Log.e("T", "reading fingerprint data error: " + e.getMessage());
        }
        return myData;
    }

    public void updateUsingKalmanFilter() {
        KalmanFilter kf = new KalmanFilter(18, 6, 0, CvType.CV_32F);
      //  kalman.set
        int nStates= 18;
        int nMeasurements = 6;
        int nInputs = 0;
        // double dt = 0.125;

        //kf.set_processNoiseCov(); // TODO
       // kf.set_measurementNoiseCov(); // TODO
       // kf.set_errorCovPost(); // TODO

//        KF.transitionMatrix.at<double>(0,3) = dt;
//        KF.transitionMatrix.at<double>(1,4) = dt;
//        KF.transitionMatrix.at<double>(2,5) = dt;
//        KF.transitionMatrix.at<double>(3,6) = dt;
//        KF.transitionMatrix.at<double>(4,7) = dt;
//        KF.transitionMatrix.at<double>(5,8) = dt;
//        KF.transitionMatrix.at<double>(0,6) = 0.5*pow(dt,2);
//        KF.transitionMatrix.at<double>(1,7) = 0.5*pow(dt,2);
//        KF.transitionMatrix.at<double>(2,8) = 0.5*pow(dt,2);
//        // orientation
//        KF.transitionMatrix.at<double>(9,12) = dt;
//        KF.transitionMatrix.at<double>(10,13) = dt;
//        KF.transitionMatrix.at<double>(11,14) = dt;
//        KF.transitionMatrix.at<double>(12,15) = dt;
//        KF.transitionMatrix.at<double>(13,16) = dt;
//        KF.transitionMatrix.at<double>(14,17) = dt;
//        KF.transitionMatrix.at<double>(9,15) = 0.5*pow(dt,2);
//        KF.transitionMatrix.at<double>(10,16) = 0.5*pow(dt,2);
//        KF.transitionMatrix.at<double>(11,17) = 0.5*pow(dt,2);
//       /* MEASUREMENT MODEL */
//        //  [1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
//        //  [0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
//        //  [0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
//        //  [0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0]
//        //  [0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0]
//        //  [0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0]
//        KF.measurementMatrix.at<double>(0,0) = 1;  // x
//        KF.measurementMatrix.at<double>(1,1) = 1;  // y
//        KF.measurementMatrix.at<double>(2,2) = 1;  // z
//        KF.measurementMatrix.at<double>(3,9) = 1;  // roll
//        KF.measurementMatrix.at<double>(4,10) = 1; // pitch
//        KF.measurementMatrix.at<double>(5,11) = 1; // yaw


        // GOOD Measurement
//        if( inliers_idx.rows >= minInliersKalman )
//        {
//            // Get the measured translation
//            cv::Mat translation_measured(3, 1, CV_64F);
//            translation_measured = pnp_detection.get_t_matrix();
//            // Get the measured rotation
//            cv::Mat rotation_measured(3, 3, CV_64F);
//            rotation_measured = pnp_detection.get_R_matrix();
//            // fill the measurements vector
//            fillMeasurements(measurements, translation_measured, rotation_measured);
//        }
//// Instantiate estimated translation and rotation
//        cv::Mat translation_estimated(3, 1, CV_64F);
//        cv::Mat rotation_estimated(3, 3, CV_64F);
//// update the Kalman filter with good measurements
//        updateKalmanFilter( KF, measurements,
//                translation_estimated, rotation_estimated);

        //TODO: fill measurements
//        void fillMeasurements( cv::Mat &measurements,
//        const cv::Mat &translation_measured, const cv::Mat &rotation_measured)
//        {
//            // Convert rotation matrix to euler angles
//            cv::Mat measured_eulers(3, 1, CV_64F);
//            measured_eulers = rot2euler(rotation_measured);
//            // Set measurement to predict
//            measurements.at<double>(0) = translation_measured.at<double>(0); // x
//            measurements.at<double>(1) = translation_measured.at<double>(1); // y
//            measurements.at<double>(2) = translation_measured.at<double>(2); // z
//            measurements.at<double>(3) = measured_eulers.at<double>(0);      // roll
//            measurements.at<double>(4) = measured_eulers.at<double>(1);      // pitch
//            measurements.at<double>(5) = measured_eulers.at<double>(2);      // yaw
//        }

        // TODO: update kalman filter
//        void updateKalmanFilter( cv::KalmanFilter &KF, cv::Mat &measurement,
//                cv::Mat &translation_estimated, cv::Mat &rotation_estimated )
//        {
//            // First predict, to update the internal statePre variable
//            cv::Mat prediction = KF.predict();
//            // The "correct" phase that is going to use the predicted value and our measurement
//            cv::Mat estimated = KF.correct(measurement);
//            // Estimated translation
//            translation_estimated.at<double>(0) = estimated.at<double>(0);
//            translation_estimated.at<double>(1) = estimated.at<double>(1);
//            translation_estimated.at<double>(2) = estimated.at<double>(2);
//            // Estimated euler angles
//            cv::Mat eulers_estimated(3, 1, CV_64F);
//            eulers_estimated.at<double>(0) = estimated.at<double>(9);
//            eulers_estimated.at<double>(1) = estimated.at<double>(10);
//            eulers_estimated.at<double>(2) = estimated.at<double>(11);
//            // Convert estimated quaternion to rotation matrix
//            rotation_estimated = euler2rot(eulers_estimated);
//        }
    }
}