package me.cheshmak.android.sdk.core.p022l;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import me.cheshmak.android.sdk.core.network.C0555a;

/* renamed from: me.cheshmak.android.sdk.core.l.n */
public class C0546n {
    /* renamed from: a */
    public static int f662a = 1;
    /* renamed from: b */
    public static int f663b = 2;
    /* renamed from: c */
    public static int f664c = 0;

    /* renamed from: a */
    public static int m1048a(@NonNull Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return f662a;
                }
                if (activeNetworkInfo.getType() == 0) {
                    return f663b;
                }
            }
        } catch (Exception e) {
            Log.e("ERROR_CHESHMAK", "Do you forget to add <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" /> into your Manifest.xml ?");
        }
        return f664c;
    }

    /* renamed from: b */
    public static WifiInfo m1049b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getNetworkInfo(1) : null;
        if (networkInfo == null || !networkInfo.isConnected()) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        return wifiManager != null ? wifiManager.getConnectionInfo() : null;
    }

    /* renamed from: c */
    public static boolean m1050c(Context context) {
        if (C0546n.m1048a(context) == 0) {
            return false;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://google.com/generate_204").openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Test");
            httpURLConnection.setRequestProperty("Connection", "close");
            httpURLConnection.setConnectTimeout(C0555a.f679a);
            httpURLConnection.connect();
            return httpURLConnection.getResponseCode() == 204;
        } catch (Throwable th) {
            return true;
        }
    }
}
