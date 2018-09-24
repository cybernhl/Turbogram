package me.cheshmak.android.sdk.core.p019a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;
import com.google.ads.AdRequest;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.util.Set;
import me.cheshmak.android.sdk.core.p022l.C0547o;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.a.a */
public class C0477a {
    /* renamed from: a */
    static C0477a f442a = null;
    /* renamed from: d */
    private static String f443d = "APPKEY_PREFERENCE";
    /* renamed from: e */
    private static String f444e = "SINGLE_PUSH_UNIQUE_ID";
    /* renamed from: f */
    private static String f445f = "ADVERTISE_STATE";
    /* renamed from: b */
    public SharedPreferences f446b;
    /* renamed from: c */
    public Editor f447c;
    /* renamed from: g */
    private long f448g = -1;

    protected C0477a(Context context) {
        this.f446b = context.getSharedPreferences("cheshmak_storage_preference", 0);
        this.f447c = this.f446b.edit();
    }

    /* renamed from: a */
    public static C0477a m656a() {
        return f442a;
    }

    /* renamed from: a */
    public static C0477a m657a(Context context) {
        if (f442a == null) {
            f442a = new C0477a(context);
        }
        return f442a;
    }

    /* renamed from: A */
    public long m658A() {
        return this.f446b.getLong("LISTENER_WIFI_INTRV", 1800);
    }

    /* renamed from: B */
    public long m659B() {
        return this.f446b.getLong("UPDATE_LAST_SHOWN", 0);
    }

    /* renamed from: C */
    public long m660C() {
        return this.f446b.getLong("UPDATE_REPEAT_GAP", 86400);
    }

    /* renamed from: D */
    public String m661D() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("SIGNAL_COLLECTOR", null);
        }
        return string;
    }

    /* renamed from: E */
    public String m662E() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("USER_INTRACTION_COLLECTOR", null);
        }
        return string;
    }

    /* renamed from: F */
    public boolean m663F() {
        boolean z;
        synchronized (f442a) {
            z = this.f446b.getBoolean("IS_SEND_DEVICEID_BROADCAST", false);
        }
        return z;
    }

    /* renamed from: G */
    public long m664G() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("DEVICEID_BROADCAST_TIMESTAMP", -1);
        }
        return j;
    }

    /* renamed from: H */
    public String m665H() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("ADVERTISEMENT_ID", null);
        }
        return string;
    }

    /* renamed from: I */
    public String m666I() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString(f444e, null);
        }
        return string;
    }

    /* renamed from: J */
    public String m667J() {
        return f445f;
    }

    /* renamed from: K */
    public boolean m668K() {
        boolean z;
        synchronized (f442a) {
            z = this.f446b.getBoolean(f445f, true);
        }
        return z;
    }

    /* renamed from: a */
    public void m669a(int i) {
        synchronized (f442a) {
            this.f447c.putInt("PUSH_LARGE_RESOURCE_ID", i).commit();
        }
    }

    /* renamed from: a */
    public void m670a(long j) {
        synchronized (f442a) {
            this.f447c.putLong("LAST_SENT_TIME_STAMP", j).commit();
        }
    }

    /* renamed from: a */
    public void m671a(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (f442a) {
            this.f446b.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    /* renamed from: a */
    public void m672a(Set<String> set) {
        synchronized (f442a) {
            this.f447c.putString("SENT_EVENT_IDS", TextUtils.join(",", set.toArray())).commit();
        }
    }

    /* renamed from: a */
    public void m673a(JSONObject jSONObject) {
        synchronized (f442a) {
            try {
                String E = m662E();
                JSONArray jSONArray = E == null ? new JSONArray() : new JSONArray(E);
                jSONArray.put(jSONObject);
                this.f447c.putString("USER_INTRACTION_COLLECTOR", jSONArray.toString()).commit();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public void m674a(boolean z) {
        synchronized (f442a) {
            this.f447c.putBoolean("IS_INITIATE", z).commit();
        }
    }

    /* renamed from: a */
    public boolean m675a(String str) {
        boolean z = false;
        if (!(str == null || "".equals(str))) {
            synchronized (f442a) {
                z = this.f447c.putString(f443d, str).commit();
            }
        }
        return z;
    }

    /* renamed from: b */
    public int m676b(Context context) {
        synchronized (f442a) {
            int i = this.f446b.getInt("PUSH_LARGE_RESOURCE_ID", -1);
        }
        if (i != -1) {
            return i;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 128).applicationInfo.icon;
        } catch (Exception e) {
            return i;
        }
    }

    /* renamed from: b */
    public String m677b() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString(f443d, null);
        }
        return string;
    }

    /* renamed from: b */
    public void m678b(int i) {
        synchronized (f442a) {
            this.f447c.putInt("PUSH_SMALL_RESOURCE_ID", i).commit();
        }
    }

    /* renamed from: b */
    public void m679b(long j) {
        synchronized (f442a) {
            this.f447c.putLong("FIRST_TIME_STAMP_EVENT_SESSION", j).commit();
        }
    }

    /* renamed from: b */
    public void m680b(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (f442a) {
            this.f446b.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    /* renamed from: b */
    public void m681b(String str) {
        synchronized (f442a) {
            this.f447c.putString("DEVICE_ID", str).commit();
        }
    }

    /* renamed from: b */
    public void m682b(boolean z) {
        synchronized (f442a) {
            this.f447c.putBoolean("SEND_REQUEST", z).commit();
        }
    }

    /* renamed from: c */
    public int m683c(Context context) {
        synchronized (f442a) {
            int i = this.f446b.getInt("PUSH_SMALL_RESOURCE_ID", -1);
        }
        if (i != -1) {
            return i;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 128).applicationInfo.icon;
        } catch (Exception e) {
            return i;
        }
    }

    /* renamed from: c */
    public String m684c() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("DEVICE_ID", null);
        }
        return string;
    }

    /* renamed from: c */
    public void m685c(int i) {
        synchronized (f442a) {
            this.f447c.putInt("CONFIG_LOG_STATUS", i).commit();
        }
    }

    /* renamed from: c */
    public void m686c(long j) {
        synchronized (f442a) {
            this.f447c.putLong("COUNT_PER_REQUEST", j).commit();
        }
    }

    /* renamed from: c */
    public void m687c(String str) {
        synchronized (f442a) {
            this.f447c.putString("GCM_TOKEN", str).commit();
        }
    }

    /* renamed from: c */
    public void m688c(boolean z) {
        synchronized (f442a) {
            this.f447c.putBoolean("CONFIG_LOG_REPORT", z).commit();
        }
    }

    /* renamed from: d */
    public void m689d() {
        synchronized (f442a) {
            try {
                this.f447c.putLong("CURRENT_SESSION_ID", C0547o.m1052a(m694e())).commit();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: d */
    public void m690d(int i) {
        this.f447c.putInt("LISTENER_WIFI_STATUS", i).commit();
    }

    /* renamed from: d */
    public void m691d(long j) {
        if (j < 86400) {
            long j2 = 1000 * j;
            synchronized (f442a) {
                this.f447c.putLong("SEND_INTERVAL_MIN", j2).commit();
            }
        }
    }

    /* renamed from: d */
    public void m692d(String str) {
        synchronized (f442a) {
            this.f447c.putString("CURRENT_SDK_VERSION", str).commit();
        }
    }

    /* renamed from: d */
    public void m693d(boolean z) {
        synchronized (f442a) {
            this.f447c.putBoolean("IS_SEND_DEVICEID_BROADCAST", z).commit();
        }
    }

    /* renamed from: e */
    public long m694e() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("CURRENT_SESSION_ID", 1);
        }
        return j;
    }

    /* renamed from: e */
    public void m695e(long j) {
        synchronized (f442a) {
            this.f447c.putLong("MAX_HTTP_COUNT", j).commit();
        }
    }

    /* renamed from: e */
    public void m696e(String str) {
        this.f447c.putString("ORGANIZATION", str).commit();
    }

    /* renamed from: e */
    public void m697e(boolean z) {
        synchronized (f442a) {
            this.f447c.putBoolean(f445f, z).commit();
        }
    }

    /* renamed from: f */
    public void m698f(long j) {
        long j2 = 1000 * j;
        synchronized (f442a) {
            this.f447c.putLong("SEND_INTERVAL_DISTANCE", j2).commit();
        }
    }

    /* renamed from: f */
    public void m699f(String str) {
        synchronized (f442a) {
            this.f447c.putString("ADVERTISEMENT_ID", str).commit();
        }
    }

    /* renamed from: f */
    public boolean m700f() {
        boolean z;
        synchronized (f442a) {
            z = this.f446b.getBoolean("IS_INITIATE", false);
        }
        return z;
    }

    /* renamed from: g */
    public long m701g() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("LAST_SENT_TIME_STAMP", 0);
        }
        return j;
    }

    /* renamed from: g */
    public void m702g(long j) {
        synchronized (f442a) {
            this.f447c.putLong("HTTP_REQUEST_COUNT", j).commit();
        }
    }

    /* renamed from: g */
    public void m703g(String str) {
        synchronized (f442a) {
            this.f447c.putString(f444e, str).commit();
        }
    }

    /* renamed from: h */
    public long m704h() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("FIRST_TIME_STAMP_EVENT_SESSION", 0);
        }
        return j;
    }

    /* renamed from: h */
    public void m705h(long j) {
        synchronized (f442a) {
            this.f447c.putLong("GCM_PROJECT_ID", j).commit();
        }
    }

    /* renamed from: i */
    public long m706i() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("COUNT_PER_REQUEST", 50);
        }
        return j;
    }

    /* renamed from: i */
    public void m707i(long j) {
        synchronized (f442a) {
            this.f447c.putLong("SEND_AVAILABILITY_INTERVAL", 1000 * j).commit();
        }
    }

    /* renamed from: j */
    public long m708j() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("SEND_INTERVAL_MIN", ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        }
        return j;
    }

    /* renamed from: j */
    public void m709j(long j) {
        synchronized (f442a) {
            this.f447c.putLong("SERVER_TIME", j).commit();
            this.f448g = -1;
        }
    }

    /* renamed from: k */
    public long m710k() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("MAX_HTTP_COUNT", 4);
        }
        return j;
    }

    /* renamed from: k */
    public void m711k(long j) {
        synchronized (f442a) {
            this.f447c.putLong("THRESHOLD_TIME", j).commit();
        }
    }

    /* renamed from: l */
    public long m712l() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("SEND_INTERVAL_DISTANCE", ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        }
        return j;
    }

    /* renamed from: l */
    public void m713l(long j) {
        synchronized (f442a) {
            this.f447c.putLong("CURRENT_SEND_INTERVAL", j).commit();
        }
    }

    /* renamed from: m */
    public long m714m() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("HTTP_REQUEST_COUNT", 0);
        }
        return j;
    }

    /* renamed from: m */
    public void m715m(long j) {
        this.f447c.putLong("LISTENER_WIFI_INTRV", j).commit();
    }

    /* renamed from: n */
    public void m716n() {
        m702g(m714m() - 1);
    }

    /* renamed from: n */
    public void m717n(long j) {
        this.f447c.putLong("UPDATE_LAST_SHOWN", j).commit();
    }

    /* renamed from: o */
    public String m718o() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("GCM_TOKEN", null);
        }
        return string;
    }

    /* renamed from: o */
    public void m719o(long j) {
        this.f447c.putLong("UPDATE_REPEAT_GAP", j).commit();
    }

    /* renamed from: p */
    public long m720p() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("GCM_PROJECT_ID", 0);
        }
        return j;
    }

    /* renamed from: p */
    public void m721p(long j) {
        synchronized (f442a) {
            this.f447c.putLong("DEVICEID_BROADCAST_TIMESTAMP", j).commit();
        }
    }

    /* renamed from: q */
    public String m722q() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("SENT_EVENT_IDS", null);
        }
        return string;
    }

    /* renamed from: r */
    public long m723r() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("SEND_AVAILABILITY_INTERVAL", 120);
        }
        return j;
    }

    /* renamed from: s */
    public boolean m724s() {
        boolean z;
        synchronized (f442a) {
            z = this.f446b.getBoolean("SEND_REQUEST", true);
        }
        return z;
    }

    /* renamed from: t */
    public String m725t() {
        String string;
        synchronized (f442a) {
            string = this.f446b.getString("CURRENT_SDK_VERSION", AdRequest.VERSION);
        }
        return string;
    }

    /* renamed from: u */
    public void m726u() {
        try {
            this.f447c.clear().commit();
        } catch (Exception e) {
        }
    }

    /* renamed from: v */
    public long m727v() {
        long j;
        synchronized (f442a) {
            if (this.f448g == -1) {
                this.f448g = this.f446b.getLong("SERVER_TIME", -1);
            }
            j = this.f448g;
        }
        return j;
    }

    /* renamed from: w */
    public long m728w() {
        long j;
        synchronized (f442a) {
            j = this.f446b.getLong("CURRENT_SEND_INTERVAL", -1);
        }
        return j;
    }

    /* renamed from: x */
    public boolean m729x() {
        boolean z;
        synchronized (f442a) {
            z = this.f446b.getBoolean("CONFIG_LOG_REPORT", true);
        }
        return z;
    }

    /* renamed from: y */
    public int m730y() {
        int i;
        synchronized (f442a) {
            i = this.f446b.getInt("CONFIG_LOG_STATUS", 0);
        }
        return i;
    }

    /* renamed from: z */
    public String m731z() {
        return this.f446b.getString("ORGANIZATION", null);
    }
}
