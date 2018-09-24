package com.logentries.misc;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern HOSTNAME_REGEX = Pattern.compile("[$/\\\"&+,:;=?#|<>_* \\[\\]]");
    private static final String TAG = "LogentriesAndroidLogger";
    private static String hostName;
    private static String traceID;

    static {
        traceID = "";
        hostName = "";
        try {
            traceID = computeTraceID();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Cannot get traceID from device's properties!");
            traceID = "unknown";
        }
        try {
            hostName = getProp("net.hostname");
            if (hostName.equals("")) {
                hostName = InetAddress.getLocalHost().getHostName();
            }
        } catch (UnknownHostException e2) {
        }
    }

    public static boolean checkIfHostNameValid(String str) {
        return !HOSTNAME_REGEX.matcher(str).find();
    }

    public static boolean checkValidUUID(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static String computeTraceID() {
        String prop = getProp("ro.build.fingerprint");
        String prop2 = getProp("ro.build.display.id");
        String prop3 = getProp("ro.hardware");
        String prop4 = getProp("ro.product.device");
        String prop5 = getProp("ril.IMEI");
        MessageDigest instance = MessageDigest.getInstance("MD5");
        if (((((prop.isEmpty() & prop2.isEmpty()) & prop3.isEmpty()) & prop4.isEmpty()) & prop5.isEmpty()) != 0) {
            Log.e(TAG, "Cannot obtain any of device's properties - will use default Trace ID source.");
            prop = Double.valueOf(Math.random() + 3.141592653589793d).toString() + Double.valueOf(Math.random() + 3.141592653589793d).toString().replace(".", "");
            instance.update((prop.length() >= 36 ? prop.substring(2, 34) : prop.substring(2)).getBytes());
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(prop).append(prop2).append(prop3).append(prop4).append(prop5);
            instance.update(stringBuilder.toString().getBytes());
        }
        byte[] digest = instance.digest();
        StringBuilder stringBuilder2 = new StringBuilder();
        int length = digest.length;
        for (int i = 0; i < length; i++) {
            stringBuilder2.append(String.format("%02x", new Object[]{Integer.valueOf(digest[i] & 255)}).toUpperCase());
        }
        return stringBuilder2.toString();
    }

    public static String formatMessage(String str, boolean z, boolean z2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (z2) {
            stringBuilder.append("{\"event\": {");
        }
        if (z) {
            stringBuilder.append(getFormattedHostName(z2));
            stringBuilder.append(z2 ? ", " : " ");
        }
        stringBuilder.append(getFormattedTraceID(z2)).append(" ");
        stringBuilder.append(z2 ? ", " : " ");
        long currentTimeMillis = System.currentTimeMillis();
        if (z2) {
            stringBuilder.append("\"Timestamp\": ").append(Long.toString(currentTimeMillis)).append(", ");
        } else {
            stringBuilder.append("Timestamp=").append(Long.toString(currentTimeMillis)).append(" ");
        }
        if (z2) {
            stringBuilder.append("\"Message\": \"").append(str);
            stringBuilder.append("\"}}");
        } else {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static String getFormattedHostName(boolean z) {
        return z ? "\"Host\": \"" + hostName + "\"" : "Host=" + hostName;
    }

    public static String getFormattedTraceID(boolean z) {
        return z ? "\"TraceID\": \"" + traceID + "\"" : "TraceID=" + traceID;
    }

    public static String getHostName() {
        return hostName;
    }

    private static String getProp(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, new Object[]{str}).toString();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String getTraceID() {
        return traceID;
    }

    public static String[] splitStringToChunks(String str, int i) {
        int i2 = 1;
        if (i < 0) {
            throw new IllegalArgumentException("Chunk length must be greater or equal to zero!");
        }
        int length = str.length();
        if (i == 0 || length <= i) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        int i3 = length / i;
        if (length % i <= 0) {
            i2 = 0;
        }
        i3 += i2;
        length = 0;
        for (i2 = 0; i2 < i3; i2++) {
            if (i2 < i3 - 1) {
                arrayList.add(str.substring(length, length + i));
            } else {
                arrayList.add(str.substring(length));
            }
            length += i;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
