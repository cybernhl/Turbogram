package me.cheshmak.android.sdk.core.p027i;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.RemoteViews;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.p001a.p002a.p003a.C0233k;
import java.lang.ref.WeakReference;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.C0451R;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0547o;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p027i.C0510c.C0509a;
import me.cheshmak.android.sdk.core.push.MessageHandler;
import me.cheshmak.android.sdk.core.task.C0591b;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.i.d */
public class C0512d implements C0507a {
    /* renamed from: a */
    static final /* synthetic */ boolean f536a = (!C0512d.class.desiredAssertionStatus());
    /* renamed from: b */
    private final C0511a f537b;
    /* renamed from: c */
    private WeakReference<Context> f538c;
    /* renamed from: d */
    private Builder f539d;
    /* renamed from: e */
    private String f540e = "LargeIcon";
    /* renamed from: f */
    private String f541f = "BigPicture";
    /* renamed from: g */
    private String f542g = "CustomViewBackground";
    /* renamed from: h */
    private RemoteViews f543h;

    /* renamed from: me.cheshmak.android.sdk.core.i.d$a */
    private class C0511a {
        /* renamed from: a */
        String f515a;
        /* renamed from: b */
        JSONArray f516b;
        /* renamed from: c */
        String f517c;
        /* renamed from: d */
        String f518d;
        /* renamed from: e */
        String f519e;
        /* renamed from: f */
        String f520f;
        /* renamed from: g */
        String f521g;
        /* renamed from: h */
        String f522h;
        /* renamed from: i */
        String f523i;
        /* renamed from: j */
        String f524j;
        /* renamed from: k */
        String f525k;
        /* renamed from: l */
        String f526l;
        /* renamed from: m */
        String f527m;
        /* renamed from: n */
        int f528n = -1;
        /* renamed from: o */
        String f529o = "none";
        /* renamed from: p */
        String f530p;
        /* renamed from: q */
        int f531q;
        /* renamed from: r */
        JSONObject f532r;
        /* renamed from: s */
        String f533s;
        /* renamed from: t */
        boolean f534t;
        /* renamed from: u */
        final /* synthetic */ C0512d f535u;

        C0511a(C0512d c0512d, Bundle bundle) {
            String string;
            this.f535u = c0512d;
            this.f517c = bundle.getString("title");
            this.f518d = bundle.getString(TtmlNode.ATTR_ID);
            this.f519e = bundle.getString("icon");
            this.f520f = bundle.getString(Param.TYPE);
            this.f521g = bundle.getString("customData");
            this.f522h = bundle.getString("message");
            this.f523i = bundle.getString("shortMessage");
            this.f524j = bundle.getString("shareText");
            this.f527m = bundle.getString("showType");
            this.f525k = bundle.getString("bigPicture");
            this.f526l = bundle.getString("bigText");
            this.f530p = bundle.getString("smallIcon");
            this.f515a = bundle.getString("loadInParallel");
            this.f531q = bundle.getInt("deadline", 0);
            this.f534t = bundle.getBoolean("isAdvertise", false);
            try {
                JSONObject jSONObject = new JSONObject(bundle.getString("setting"));
                try {
                    this.f529o = jSONObject.optString("vibrateType", "none");
                } catch (Exception e) {
                }
                try {
                    this.f528n = jSONObject.optInt("ledColor", -1);
                } catch (Exception e2) {
                }
            } catch (Exception e3) {
            }
            try {
                string = bundle.getString("buttons");
                if (string != null) {
                    this.f516b = new JSONArray(string);
                }
            } catch (Throwable th) {
            }
            try {
                string = bundle.getString("customViewData");
                if (string != null) {
                    this.f532r = new JSONObject(string);
                }
                this.f533s = bundle.getString("params");
            } catch (Throwable th2) {
            }
        }

        C0511a(C0512d c0512d, String str) {
            this.f535u = c0512d;
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f517c = jSONObject.optString("title");
                this.f518d = jSONObject.optString(TtmlNode.ATTR_ID);
                this.f519e = jSONObject.optString("icon");
                this.f520f = jSONObject.optString(Param.TYPE);
                this.f521g = jSONObject.optString("customData");
                this.f522h = jSONObject.optString("message");
                this.f523i = jSONObject.optString("shortMessage");
                this.f524j = jSONObject.optString("shareText");
                this.f527m = jSONObject.optString("showType");
                this.f525k = jSONObject.optString("bigPicture");
                this.f526l = jSONObject.optString("bigText");
                this.f530p = jSONObject.optString("smallIcon");
                JSONArray optJSONArray = jSONObject.optJSONArray("loadInParallel");
                if (optJSONArray != null) {
                    this.f515a = optJSONArray.toString();
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("setting");
                if (optJSONObject != null) {
                    this.f528n = optJSONObject.optInt("ledColor", -1);
                    this.f529o = optJSONObject.optString("vibrateType", "long");
                }
                this.f516b = jSONObject.optJSONArray("buttons");
                this.f531q = jSONObject.optInt("deadline", 0);
                this.f532r = jSONObject.optJSONObject("customViewData");
                this.f533s = jSONObject.optString("params");
                this.f534t = jSONObject.optBoolean("isAdvertise", false);
            } catch (Exception e) {
            }
        }

        /* renamed from: a */
        public int m853a() {
            String str = this.f529o;
            int i = -1;
            switch (str.hashCode()) {
                case 3327612:
                    if (str.equals("long")) {
                        i = 2;
                        break;
                    }
                    break;
                case 3387192:
                    if (str.equals("none")) {
                        i = 0;
                        break;
                    }
                    break;
                case 109413500:
                    if (str.equals("short")) {
                        i = 1;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    return 0;
            }
        }
    }

    public C0512d(Context context, Bundle bundle) {
        this.f537b = new C0511a(this, bundle);
        this.f538c = new WeakReference(context);
    }

    public C0512d(Context context, String str) {
        this.f537b = new C0511a(this, str);
        this.f538c = new WeakReference(context);
    }

    /* renamed from: a */
    private static int m854a(String str) {
        String replaceAll = str.replaceAll("[^0-9]", "");
        if (replaceAll.length() > 9) {
            replaceAll = replaceAll.substring(0, 8);
        }
        return Integer.parseInt(replaceAll);
    }

    /* renamed from: a */
    private PendingIntent m855a(int i) {
        Intent intent = new Intent((Context) this.f538c.get(), MessageHandler.class);
        intent.putExtra("me.cheshmak.data", this.f537b.f521g);
        intent.putExtra("pushId", this.f537b.f518d);
        intent.putExtra("message", this.f537b.f522h);
        intent.putExtra("title", this.f537b.f517c);
        intent.putExtra("shortMessage", this.f537b.f523i);
        intent.putExtra(Param.TYPE, this.f537b.f520f);
        intent.putExtra("button", "0");
        intent.putExtra("notifId", i);
        intent.putExtra("loadInParallel", this.f537b.f515a);
        intent.putExtra("params", this.f537b.f533s);
        intent.putExtra("isAdvertise", this.f537b.f534t);
        return PendingIntent.getBroadcast((Context) this.f538c.get(), i, intent, 268435456);
    }

    /* renamed from: a */
    public static void m856a(Context context, String str) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(C0512d.m854a(str));
        }
    }

    /* renamed from: a */
    private void m857a(Builder builder) {
        if (this.f537b.f528n != -1) {
            builder.setLights(this.f537b.f528n, 1000, 1000);
        }
        if (!"none".equals(this.f537b.f529o) && C0552s.m1074a((Context) this.f538c.get(), "android.permission.VIBRATE")) {
            if ("short".equals(this.f537b.f529o)) {
                builder.setVibrate(new long[]{0, 100, 0, 100});
            } else if ("long".equals(this.f537b.f529o)) {
                builder.setVibrate(new long[]{0, 1000});
            }
        }
        builder.setPriority(2);
    }

    /* renamed from: a */
    private void m858a(JSONArray jSONArray, int i) {
        if (jSONArray != null) {
            try {
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                    CharSequence optString = jSONObject.optString("name", null);
                    String optString2 = jSONObject.optString("icon", null);
                    String optString3 = jSONObject.optString("action", null);
                    String optString4 = jSONObject.optString("data");
                    String optString5 = jSONObject.optString("loadInParallel");
                    Intent intent = new Intent((Context) this.f538c.get(), MessageHandler.class);
                    intent.putExtra("me.cheshmak.data", optString4);
                    intent.putExtra("pushId", this.f537b.f518d);
                    intent.putExtra("message", this.f537b.f522h);
                    intent.putExtra("title", this.f537b.f517c);
                    intent.putExtra("shortMessage", this.f537b.f523i);
                    if (optString5 != null) {
                        intent.putExtra("loadInParallel", optString5);
                    }
                    intent.putExtra(Param.TYPE, optString3);
                    intent.putExtra("notifId", i);
                    intent.putExtra("button", (length - i2) + "");
                    this.f539d.addAction(C0552s.m1079d((Context) this.f538c.get(), optString2), optString, PendingIntent.getBroadcast((Context) this.f538c.get(), C0547o.m1051a(), intent, 268435456));
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    private void m859b() {
        int a = C0512d.m854a(this.f537b.f518d);
        m860b(a);
        WeakHashMap weakHashMap = new WeakHashMap();
        if ("4".equals(this.f537b.f527m)) {
            this.f543h = new RemoteViews(((Context) this.f538c.get()).getPackageName(), C0451R.layout.ches_image_notification_view);
            this.f539d.setCustomContentView(this.f543h);
            if (URLUtil.isValidUrl(this.f537b.f532r.optString("background"))) {
                weakHashMap.put(this.f542g, this.f537b.f532r.optString("background"));
            }
        } else if ("5".equals(this.f537b.f527m)) {
            this.f543h = new RemoteViews(((Context) this.f538c.get()).getPackageName(), C0451R.layout.ches_simple_notification_background_image_view);
            this.f543h.setTextViewText(C0451R.id.ches_notification_title, this.f537b.f517c);
            this.f543h.setTextViewText(C0451R.id.ches_notification_content, this.f537b.f523i);
            if (this.f537b.f532r.optString("titleTextBackground") != null) {
                this.f543h.setTextColor(C0451R.id.ches_notification_title, Color.parseColor(this.f537b.f532r.optString("titleTextBackground")));
            }
            if (this.f537b.f532r.optString("contentTextBackground") != null) {
                this.f543h.setTextColor(C0451R.id.ches_notification_content, Color.parseColor(this.f537b.f532r.optString("contentTextBackground")));
            }
            this.f539d.setCustomContentView(this.f543h);
            if (!(this.f537b.f532r.optString("background") == null || "".equals(this.f537b.f532r.optString("background")))) {
                if (URLUtil.isValidUrl(this.f537b.f532r.optString("background"))) {
                    weakHashMap.put(this.f542g, this.f537b.f532r.optString("background"));
                } else {
                    this.f543h.setInt(C0451R.id.ches_background_notification, "setBackgroundColor", Color.parseColor(this.f537b.f532r.optString("background")));
                }
            }
            if (URLUtil.isValidUrl(this.f537b.f519e)) {
                weakHashMap.put(this.f540e, this.f537b.f519e);
            } else {
                this.f543h.setImageViewBitmap(C0451R.id.ches_large_icon_notification, BitmapFactory.decodeResource(((Context) this.f538c.get()).getResources(), C0477a.m656a().m676b((Context) this.f538c.get())));
            }
        } else {
            m858a(this.f537b.f516b, a);
            String d = m863d();
            if (d != null) {
                weakHashMap.put(this.f540e, d);
            }
            this.f539d.setContentTitle(this.f537b.f517c).setContentText(this.f537b.f523i);
            if (ExifInterface.GPS_MEASUREMENT_2D.equals(this.f537b.f527m)) {
                m861c();
            } else if (ExifInterface.GPS_MEASUREMENT_3D.equals(this.f537b.f527m)) {
                weakHashMap.put(this.f541f, this.f537b.f525k);
            }
        }
        if (weakHashMap.isEmpty()) {
            m862c(a);
        } else {
            new C0508b(a, weakHashMap, this).start();
        }
    }

    /* renamed from: b */
    private void m860b(int i) {
        this.f539d = new Builder((Context) this.f538c.get(), "general_id").setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(m855a(i));
        this.f539d.setDefaults(0);
        int c = (this.f537b.f530p == null || "".equals(this.f537b.f530p)) ? C0477a.m656a().m683c((Context) this.f538c.get()) : C0552s.m1079d((Context) this.f538c.get(), this.f537b.f530p);
        this.f539d.setSmallIcon(c);
        if (!C0510c.m849b()) {
            m857a(this.f539d);
        } else if (this.f538c != null) {
            Context context = (Context) this.f538c.get();
            if (context != null) {
                C0510c.m847a().m850a(context, "general", "general_id");
                C0510c.m847a().m852a(context, "general_id", new C0509a(this.f537b.f528n, this.f537b.m853a()));
            }
        }
    }

    /* renamed from: c */
    private void m861c() {
        Style bigText = new BigTextStyle().bigText(this.f537b.f522h);
        bigText.bigText(this.f537b.f526l);
        this.f539d.setStyle(bigText);
    }

    /* renamed from: c */
    private void m862c(int i) {
        NotificationManager notificationManager = (NotificationManager) ((Context) this.f538c.get()).getSystemService("notification");
        Notification build = this.f539d.build();
        build.flags |= 16;
        build.flags |= 1;
        if (f536a || notificationManager != null) {
            notificationManager.notify(i, build);
            C0480a.m743c(this.f537b.f518d);
            if (this.f537b.f531q != 0) {
                C0233k b = C0491a.m778b((Context) this.f538c.get());
                if (b != null) {
                    b.m488a(new C0591b(i, this.f537b.f531q));
                    return;
                }
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: d */
    private String m863d() {
        if (this.f537b.f519e == null || "".equals(this.f537b.f519e)) {
            this.f539d.setLargeIcon(BitmapFactory.decodeResource(((Context) this.f538c.get()).getResources(), C0477a.m656a().m676b((Context) this.f538c.get())));
        } else if (Patterns.WEB_URL.matcher(this.f537b.f519e).matches()) {
            try {
                return this.f537b.f519e;
            } catch (Exception e) {
            }
        } else {
            int e2 = C0552s.m1080e((Context) this.f538c.get(), this.f537b.f519e);
            if (e2 != 0) {
                this.f539d.setLargeIcon(BitmapFactory.decodeResource(((Context) this.f538c.get()).getResources(), e2));
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m864a() {
        try {
            m859b();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public void mo4404a(int i, WeakHashMap<String, Bitmap> weakHashMap) {
        try {
            for (Entry entry : weakHashMap.entrySet()) {
                if ("4".equals(this.f537b.f527m) || "5".equals(this.f537b.f527m)) {
                    if (this.f542g.equals(entry.getKey())) {
                        this.f543h.setBitmap(C0451R.id.ches_background_notification, "setImageBitmap", (Bitmap) entry.getValue());
                    }
                    if (this.f540e.equals(entry.getKey())) {
                        this.f543h.setBitmap(C0451R.id.ches_large_icon_notification, "setImageBitmap", (Bitmap) entry.getValue());
                    }
                } else {
                    if (this.f540e.equals(entry.getKey())) {
                        this.f539d.setLargeIcon((Bitmap) entry.getValue());
                    }
                    if (this.f541f.equals(entry.getKey())) {
                        Style bigPictureStyle = new BigPictureStyle();
                        bigPictureStyle.setBigContentTitle(this.f537b.f517c);
                        bigPictureStyle.setSummaryText(this.f537b.f523i);
                        bigPictureStyle.bigPicture((Bitmap) entry.getValue());
                        this.f539d.setStyle(bigPictureStyle);
                    }
                }
                m862c(i);
            }
        } catch (Exception e) {
        }
    }
}
