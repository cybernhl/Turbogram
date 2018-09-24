package me.cheshmak.android.sdk.core.p021c;

import android.support.media.ExifInterface;

/* renamed from: me.cheshmak.android.sdk.core.c.a */
public enum C0481a {
    DIRECT("0"),
    MARKET("1"),
    ALL(ExifInterface.GPS_MEASUREMENT_2D);
    
    /* renamed from: d */
    private String f463d;

    private C0481a(String str) {
        this.f463d = str;
    }

    /* renamed from: a */
    public static C0481a m753a(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    obj = null;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return DIRECT;
            case 1:
                return MARKET;
            default:
                return ALL;
        }
    }

    /* renamed from: a */
    public String m754a() {
        return this.f463d;
    }
}
