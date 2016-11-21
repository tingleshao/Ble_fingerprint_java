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
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.camera.CaptureCameraPreview;
import org.artoolkit.ar.base.rendering.ARRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imdecode;
import static org.opencv.imgcodecs.Imgcodecs.imread;


public class MainActivity extends ARActivity implements SensorEventListener  {

    Core.MinMaxLocResult minmax;

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 133;

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
                    Bitmap bitmap = MainActivity.this.preview.getBitmap();

                //    Bitmap bitmapg = toGrayscale(bitmap);
                    Bitmap bitmap2 = locateFeaturePoint(bitmap);
                    if (bitmap2!=null) {
                        imageView.setImageBitmap(bitmap2);
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

        Bitmap bMap= BitmapFactory.decodeResource(getResources(),R.drawable.box);
        Utils.bitmapToMat(bMap, image);
     //   InputStream inpT = getResources().openRawResource(R.drawable.box);
     //   try {
     //       image = readInputStreamIntoMat(inpT);
     //   } catch (IOException e) {
     //       Log.d("E", "IO Exception");
      //  }

     //   Utils.bitmapToMat(input, image);
        Mat imagei = new Mat();
        Imgproc.cvtColor(image,  imagei, Imgproc.COLOR_RGBA2GRAY);
        Utils.matToBitmap(imagei, bMap);
        Mat image2 = imread("box.png", CV_LOAD_IMAGE_GRAYSCALE);

        MatOfKeyPoint keypoints = new MatOfKeyPoint();
        detector.detect(imagei, keypoints);

        Log.d("T", "image width:" + String.valueOf(imagei.width()) + " " + String.valueOf(imagei.height()));
        Log.d("T", "image2 width:" + String.valueOf(image2.width()) + " " + String.valueOf(image2.height()));

        //   Bitmap bitmap2 = this.preview.getBitmap();
        Log.d("T", "keypoints size: " + String.valueOf(keypoints.size()));
    //    if (!detector.empty()){
            // Draw kewpoints
            Mat outputImage = new Mat();
            Scalar color = new Scalar(0, 0, 255); // BGR
            int flags = Features2d.DRAW_RICH_KEYPOINTS; // For each keypoint, the circle around keypoint with keypoint size and orientation will be drawn.
            Features2d.drawKeypoints(imagei, keypoints, outputImage, color , flags);
            Utils.matToBitmap(outputImage, bMap);
            //        displayImage(Mat2BufferedImage(outputImage), "Feautures_"+detectorType);
     //   }
    //    else {
     //       Log.d("T", "detector empty!");
     //   }
        return bMap;
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
            rotationX = event.values[0];
            rotationY = event.values[1];
            rotationZ = event.values[2];

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
    public void estimatePose(Bitmap bitmap1, Bitmap bitmap2) {
        Mat mat1 = new Mat();
        Mat mat2 = new Mat();
        Utils.bitmapToMat(bitmap1, mat1);
        Utils.bitmapToMat(bitmap2, mat2);

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

        Mat matH = Calib3d.findHomography(obj, scene);
        Mat warping = mat1.clone();
        org.opencv.core.Size ims = new org.opencv.core.Size(mat1.cols(),mat1.rows());
        Imgproc.warpPerspective(mat1, warping , matH, ims);
    }
}