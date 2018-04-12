package com.example.chongshao_mikasa.ble_fingerprint_java;

/**
 * Created by chongshao on 4/11/18.
 */

public class Utils {

    public static int[][] fingerprintDatabase = new int[][]{
            {-74, -74, -75, -77, -83, -69, -78, -78},
            {-74, -74, -74, -76, -82, -72, -78, -76},
            {-68, -77, -74, -77, -84, -76, -84, -77},
            {-69, -74, -75, -85, -85, -72, -74, -77},
            {-68, -70, -74, -86, -79, -72, -77, -84},
            {-68, -72, -77, -88, -79, -78, -83, -85},
            {-71, -82, -77, -85, -82, -75, -81, -81},
            {-66, -80, -76, -83, -83, -78, -79, -81},
            {-68, -77, -76, -80, -83, -77, -75, -80},
            {-75, -75, -81, -79, -80, -78, -77, -80},
            {-81, -75, -79, -82, -79, -81, -78, -80},
            {-81, -75, -79, -82, -79, -81, -78, -80},
            {-73, -70, -80, -80, -86, -77, -77, -82},
            {-73, -70, -81, -74, -86, -74, -77, -82},
            {-72, -71, -79, -83, -85, -78, -75, -83},
            {-69, -70, -79, -80, -85, -76, -78, -75},
            {-69, -72, -80, -80, -85, -76, -77, -76},
            {-71, -73, -82, -80, -85, -76, -76, -75},
            {-74, -77, -78, -81, -81, -69, -80, -77},
            {-74, -73, -77, -81, -81, -68, -77, -77},
            {-72, -69, -76, -80, -81, -67, -75, -76},
            {-73, -75, -76, -82, -81, -80, -75, -78},
            {-72, -74, -76, -82, -81, -78, -75, -78},
            {-72, -72, -76, -81, -80, -74, -77, -78},
            {-77, -75, -79, -83, -80, -82, -77, -80},
            {-77, -77, -78, -83, -80, -78, -78, -80},
            {-78, -81, -77, -84, -80, -74, -79, -81},
            {-79, -71, -79, -81, -88, -77, -76, -81},
            {-77, -68, -81, -80, -86, -79, -77, -80},
            {-76, -71, -81, -81, -86, -81, -77, -81},
            {-70, -75, -77, -78, -79, -80, -77, -81},
            {-71, -76, -77, -76, -80, -81, -77, -81},
            {-71, -76, -76, -75, -80, -79, -79, -81},
            {-70, -76, -81, -81, -81, -75, -81, -77},
            {-70, -76, -80, -82, -81, -77, -80, -75},
            {-69, -76, -78, -83, -81, -78, -80, -74},
            {-72, -72, -78, -82, -79, -78, -80, -80},
            {-70, -71, -80, -81, -77, -74, -78, -80},
            {-70, -71, -81, -81, -77, -73, -77, -80},
            {-69, -78, -79, -82, -81, -74, -81, -79},
            {-73, -84, -77, -78, -81, -72, -81, -81},
            {-73, -84, -76, -78, -78, -71, -81, -81},
            {-74, -69, -78, -76, -80, -69, -79, -79},
            {-76, -72, -76, -79, -79, -76, -80, -79},
            {-76, -73, -76, -80, -79, -77, -80, -83},
            {-72, -74, -78, -77, -85, -74, -79, -80},
            {-75, -70, -78, -84, -86, -70, -76, -86},
            {-75, -71, -78, -84, -86, -69, -76, -86},
            {-71, -73, -76, -85, -82, -81, -85, -83},
            {-69, -76, -76, -82, -80, -81, -86, -82},
            {-69, -78, -75, -79, -79, -80, -86, -82},
            {-64, -71, -73, -83, -81, -80, -83, -76},
            {-64, -71, -73, -84, -81, -79, -82, -76},
            {-64, -70, -72, -84, -84, -79, -83, -75},
            {-67, -76, -72, -81, -81, -78, -78, -80},
            {-70, -76, -72, -81, -81, -78, -77, -80},
            {-71, -75, -72, -81, -83, -82, -75, -80},
            {-75, -75, -77, -81, -83, -72, -77, -80},
            {-77, -76, -76, -85, -83, -72, -77, -79},
            {-81, -76, -77, -85, -81, -73, -83, -79},
            {-76, -77, -78, -82, -78, -78, -79, -81},
            {-75, -75, -76, -79, -78, -76, -80, -81},
            {-75, -70, -73, -77, -77, -75, -76, -81},
            {-67, -80, -79, -83, -79, -73, -87, -81},
            {-67, -79, -78, -83, -79, -72, -87, -81},
            {-67, -79, -77, -83, -79, -71, -87, -84},
            {-70, -75, -78, -79, -75, -74, -78, -80},
            {-70, -73, -73, -76, -87, -75, -83, -82},
            {-71, -73, -73, -75, -87, -76, -84, -82},
            {-69, -72, -74, -82, -84, -70, -81, -79},
            {-73, -72, -76, -85, -85, -69, -78, -83},
            {-75, -70, -80, -87, -86, -75, -76, -83},
            {-74, -82, -81, -83, -85, -76, -81, -86},
            {-74, -86, -82, -83, -86, -78, -80, -85},
            {-72, -79, -80, -84, -84, -75, -78, -83},
            {-75, -76, -77, -77, -79, -79, -82, -80},
            {-77, -76, -79, -76, -77, -79, -84, -82},
            {-77, -76, -79, -76, -77, -77, -85, -82},
            {-76, -76, -73, -76, -75, -75, -82, -83},
            {-74, -76, -75, -77, -74, -73, -80, -81},
            {-74, -76, -77, -78, -74, -71, -79, -81},
            {-73, -78, -79, -75, -78, -74, -85, -82},
            {-73, -82, -76, -74, -80, -75, -86, -83},
            {-72, -79, -76, -75, -79, -77, -86, -86},
            {-77, -76, -79, -77, -77, -76, -81, -84},
            {-74, -74, -79, -86, -76, -74, -83, -83},
            {-73, -72, -82, -85, -76, -75, -80, -82},
            {-74, -78, -81, -82, -85, -72, -85, -83},
            {-75, -78, -78, -82, -85, -72, -83, -81},
            {-74, -78, -75, -81, -79, -73, -82, -79},
            {-74, -68, -74, -77, -77, -67, -88, -85},
            {-74, -69, -73, -76, -77, -67, -86, -85},
            {-69, -76, -73, -82, -77, -74, -81, -85},
            {-67, -76, -77, -76, -86, -77, -77, -89},
            {-69, -73, -78, -76, -80, -80, -80, -76},
            {-69, -73, -76, -77, -79, -83, -79, -76},
            {-79, -73, -75, -84, -78, -77, -79, -81},
            {-79, -71, -76, -81, -78, -78, -78, -81},
            {-78, -70, -76, -80, -78, -80, -79, -81},
            {-70, -73, -76, -81, -78, -79, -83, -83},
            {-69, -71, -75, -78, -79, -75, -85, -81},
            {-69, -72, -76, -74, -80, -75, -85, -81},
            {-70, -75, -84, -78, -77, -75, -83, -83},
            {-70, -75, -85, -80, -80, -76, -87, -83},
            {-69, -80, -81, -84, -80, -77, -88, -83},
            {-72, -80, -76, -80, -77, -72, -79, -86},
            {-73, -81, -74, -81, -76, -72, -78, -85},
            {-75, -81, -73, -82, -76, -71, -75, -85},
            {-73, -78, -76, -81, -82, -77, -82, -80},
            {-71, -73, -81, -83, -84, -74, -75, -81},
            {-72, -74, -81, -84, -84, -72, -75, -79},
            {-69, -72, -79, -87, -81, -72, -81, -83},
            {-69, -72, -77, -86, -81, -76, -81, -80},
            {-72, -73, -76, -84, -81, -79, -81, -79},
            {-73, -77, -76, -87, -85, -74, -76, -80},
            {-71, -76, -75, -84, -85, -75, -76, -80},
            {-70, -76, -75, -82, -85, -75, -76, -80},
            {-76, -79, -78, -82, -82, -78, -82, -81},
            {-76, -78, -76, -80, -82, -78, -82, -84},
            {-77, -78, -75, -78, -82, -78, -81, -84},
            {-82, -73, -79, -76, -83, -73, -82, -75},
            {-82, -74, -79, -80, -85, -74, -83, -75},
            {-82, -75, -79, -84, -85, -74, -87, -78},
            {-78, -73, -79, -80, -88, -74, -82, -78},
            {-78, -74, -80, -76, -87, -73, -81, -78},
            {-78, -74, -80, -76, -86, -73, -82, -78},
            {-74, -74, -79, -79, -80, -79, -84, -81},
            {-76, -73, -79, -78, -81, -81, -84, -81},
            {-76, -73, -81, -77, -81, -83, -83, -81},
            {-69, -75, -82, -82, -80, -73, -81, -76},
            {-67, -73, -85, -80, -82, -74, -80, -76},
            {-66, -72, -85, -80, -82, -75, -76, -74},
            {-65, -78, -81, -80, -83, -74, -76, -82},
            {-66, -80, -81, -80, -83, -75, -77, -82},
            {-66, -78, -77, -81, -84, -75, -78, -81},
            {-68, -81, -74, -79, -85, -69, -85, -82},
            {-68, -82, -77, -78, -85, -68, -86, -81},
            {-66, -84, -78, -78, -86, -68, -89, -81},
            {-70, -79, -78, -79, -85, -78, -80, -81},
            {-69, -80, -80, -79, -87, -79, -77, -81},
            {-74, -80, -80, -79, -87, -77, -77, -82},
            {-70, -78, -77, -83, -88, -81, -86, -82},
            {-69, -80, -78, -84, -88, -85, -86, -82},
            {-68, -80, -78, -87, -88, -87, -88, -82},
            {-66, -69, -75, -89, -81, -76, -77, -80},
            {-65, -66, -75, -90, -79, -73, -80, -79},
            {-64, -64, -74, -87, -77, -71, -83, -81},
            {-68, -69, -79, -82, -81, -76, -86, -78},
            {-70, -67, -79, -82, -81, -75, -88, -78},
            {-71, -65, -80, -84, -81, -74, -88, -78},
            {-67, -64, -80, -79, -87, -76, -82, -73},
            {-66, -68, -80, -81, -88, -76, -81, -73},
            {-67, -73, -81, -82, -86, -74, -83, -75},
            {-70, -74, -78, -82, -85, -78, -83, -76},
            {-71, -73, -76, -83, -84, -76, -83, -76},
            {-77, -71, -73, -84, -84, -74, -85, -76},
            {-76, -81, -78, -87, -86, -77, -83, -85},
            {-76, -80, -78, -87, -86, -78, -83, -86},
            {-76, -81, -78, -88, -86, -79, -82, -86},
            {-73, -77, -79, -80, -88, -74, -85, -86},
            {-72, -77, -80, -82, -88, -74, -85, -86},
            {-72, -77, -81, -82, -88, -74, -87, -86},
            {-69, -77, -72, -86, -89, -73, -85, -87},
            {-69, -77, -72, -86, -89, -73, -85, -87},
            {-67, -71, -73, -81, -86, -71, -80, -82},
            {-66, -71, -70, -87, -89, -77, -82, -80},
            {-64, -70, -70, -87, -88, -78, -84, -80},
            {-64, -71, -71, -86, -88, -78, -85, -80},
            {-68, -74, -76, -83, -79, -76, -82, -81},
            {-68, -74, -77, -82, -79, -76, -83, -81},
            {-68, -74, -79, -84, -79, -77, -83, -81},
            {-65, -81, -79, -80, -84, -83, -84, -86},
            {-66, -81, -76, -81, -84, -81, -84, -86},
            {-66, -81, -76, -83, -84, -80, -84, -86},
            {-68, -77, -77, -83, -78, -79, -80, -80},
            {-74, -76, -78, -81, -79, -80, -80, -80},
            {-74, -76, -79, -78, -81, -83, -82, -80},
            {-75, -79, -82, -76, -82, -75, -83, -80},
            {-73, -79, -80, -78, -82, -75, -83, -80},
            {-73, -78, -80, -78, -82, -73, -82, -83},
            {-76, -76, -77, -78, -82, -79, -81, -85},
            {-74, -78, -70, -81, -88, -73, -80, -82},
            {-76, -77, -69, -80, -88, -74, -81, -82},
            {-67, -73, -76, -81, -88, -78, -83, -83},
            {-67, -73, -76, -81, -88, -78, -83, -83},
            {-67, -72, -75, -79, -85, -75, -85, -80},
            {-66, -69, -75, -78, -82, -73, -79, -76},
            {-64, -69, -75, -77, -81, -72, -78, -76},
            {-64, -68, -75, -77, -81, -72, -78, -76},
            {-69, -76, -80, -82, -83, -76, -82, -81},
            {-69, -76, -80, -82, -83, -76, -82, -81},
            {-69, -76, -80, -83, -82, -75, -82, -81},
            {-69, -78, -76, -74, -84, -76, -82, -81},
            {-69, -77, -76, -75, -84, -75, -85, -79},
            {-67, -78, -76, -76, -87, -72, -86, -79},
            {-74, -77, -73, -82, -87, -75, -77, -78},
            {-74, -77, -73, -82, -87, -75, -77, -78},
            {-75, -76, -70, -84, -87, -74, -76, -78},
            {-72, -76, -75, -84, -89, -72, -77, -82},
            {-72, -77, -76, -84, -84, -72, -76, -80},
            {-72, -80, -72, -85, -85, -80, -80, -83},
            {-70, -75, -74, -82, -84, -80, -82, -80},
            {-71, -74, -75, -82, -84, -80, -82, -80},
            {-72, -74, -76, -83, -84, -80, -81, -78},
            {-65, -76, -75, -81, -80, -76, -78, -81},
            {-65, -79, -75, -82, -82, -76, -78, -83},
            {-65, -80, -75, -82, -82, -75, -80, -84},
            {-64, -80, -77, -80, -83, -76, -77, -81},
            {-64, -79, -76, -77, -83, -77, -76, -81},
            {-63, -80, -76, -77, -81, -80, -78, -79},
            {-68, -72, -77, -86, -87, -80, -85, -82},
            {-68, -72, -77, -82, -87, -80, -85, -82},
            {-70, -75, -76, -85, -86, -80, -85, -82}
    };


    public static String[] locations = new String[]{"00", "01", "02", "03", "04", "05", "06",
            "10", "11", "12", "13", "14", "15",
            "20", "21", "22", "23", "24", "25",
            "30", "31", "32", "33", "34", "35",
            "40", "41", "42", "43", "44", "45",
            "50", "51", "52", "53", "54", "55", "56",
            "60", "61", "62", "63", "64", "65", "66",
            "70", "71", "72", "73", "74", "75", "76",
            "80", "81", "82", "83", "84", "85", "86",
            "90", "91", "92", "93", "94", "95",
            "a0", "a1", "a2", "a3", "a4", "a5"};

    public static float[] getInitM() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                -15.0f, -50.0f, -2000.0f, 1.0f};

        ////        m = new float[]{-0.9930515f, 0.060360666f, 0.10102971f, 0.0f,
//                -0.06912676f, -0.993927f, -0.08563979f, 0.0f,
//                0.095246725f, -0.09202827f, 0.9911907f, 0.0f,
//                -0.001564f, -0.064268f, -0.0829f, 1.0f};

        //      m = new float[]{-0.9930515f, 0.060360666f, 0.10102971f, 0.0f,
        //              -0.06912676f, -0.993927f, -0.08563979f, 0.0f,
        //              0.095246725f, -0.09202827f, 0.9911907f, 0.0f,
        //             -50.001564f, -24.064268f, -271.0829f, 1.0f};
    }

    public static String cameraToStr(float[] m) {
        return String.valueOf(m[0]) + " " + String.valueOf(m[1]) + " " +
                String.valueOf(m[2]) + " " + String.valueOf(m[3]) + " " + String.valueOf(m[4]) + " " +
                String.valueOf(m[5]) + " " + String.valueOf(m[6]) + " " + String.valueOf(m[7]) + " " +
                String.valueOf(m[8]) + " " + String.valueOf(m[9]) + " " + String.valueOf(m[10]) + " " +
                String.valueOf(m[11]) + " " + String.valueOf(m[12]) + " " + String.valueOf(m[13]) + " " +
                String.valueOf(m[14]) + " " + String.valueOf(m[15]);
    }
}
