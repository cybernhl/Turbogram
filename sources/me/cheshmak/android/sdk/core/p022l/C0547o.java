package me.cheshmak.android.sdk.core.p022l;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;

/* renamed from: me.cheshmak.android.sdk.core.l.o */
public class C0547o {
    /* renamed from: a */
    public static int m1051a() {
        try {
            return new Random().nextInt(100001);
        } catch (Throwable th) {
            return 0;
        }
    }

    /* renamed from: a */
    public static long m1052a(long j) {
        return j == Long.MAX_VALUE ? 1 : 1 + j;
    }

    /* renamed from: a */
    public static <T> List<T> m1053a(JSONArray jSONArray) {
        List arrayList = new ArrayList();
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.get(i));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static long[] m1054b(JSONArray jSONArray) {
        long[] jArr = null;
        if (jSONArray != null) {
            int length = jSONArray.length();
            jArr = new long[length];
            for (int i = 0; i < length; i++) {
                jArr[i] = jSONArray.optLong(i, 0);
            }
        }
        return jArr;
    }
}
