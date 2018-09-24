package me.cheshmak.android.sdk.core.p027i;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.URL;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* renamed from: me.cheshmak.android.sdk.core.i.b */
public class C0508b extends Thread {
    /* renamed from: a */
    private final int f507a;
    /* renamed from: b */
    private C0507a f508b;
    /* renamed from: c */
    private WeakHashMap<String, String> f509c;

    C0508b(int i, WeakHashMap<String, String> weakHashMap, C0507a c0507a) {
        this.f508b = c0507a;
        this.f509c = weakHashMap;
        this.f507a = i;
    }

    /* renamed from: a */
    private Bitmap m845a(String str) {
        return BitmapFactory.decodeStream(new URL(str).openStream());
    }

    /* renamed from: a */
    private void m846a(WeakHashMap<String, Bitmap> weakHashMap) {
        if (this.f508b != null) {
            this.f508b.mo4404a(this.f507a, weakHashMap);
        }
    }

    public void run() {
        WeakHashMap weakHashMap = new WeakHashMap();
        for (Entry entry : this.f509c.entrySet()) {
            try {
                weakHashMap.put(entry.getKey(), m845a((String) entry.getValue()));
            } catch (Exception e) {
            }
        }
        m846a(weakHashMap);
    }
}
