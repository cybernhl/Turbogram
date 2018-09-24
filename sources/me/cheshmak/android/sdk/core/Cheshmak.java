package me.cheshmak.android.sdk.core;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.advertise.CheshmakAds;
import me.cheshmak.android.sdk.core.network.C0571i;
import me.cheshmak.android.sdk.core.network.EventSendService;
import me.cheshmak.android.sdk.core.network.SinglePushResponseListener;
import me.cheshmak.android.sdk.core.p018f.C0494a;
import me.cheshmak.android.sdk.core.p018f.C0496b;
import me.cheshmak.android.sdk.core.p018f.C0496b.C0473a;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p019a.C0478b;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0523h;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0550q;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p029k.C0515b;
import me.cheshmak.android.sdk.core.push.p030a.C0585j;
import me.cheshmak.android.sdk.core.receivers.AlarmReceiver;
import org.json.JSONObject;

public class Cheshmak {
    /* renamed from: a */
    private static Cheshmak f435a = null;
    public static int appVersion;
    public static Context applicationContext;
    /* renamed from: b */
    private static C0515b f436b;
    /* renamed from: c */
    private static C0494a f437c;
    /* renamed from: d */
    private static UncaughtExceptionHandler f438d;
    /* renamed from: e */
    private static UncaughtExceptionHandler f439e;
    /* renamed from: f */
    private static C0473a f440f = new C04741();
    /* renamed from: g */
    private static boolean f441g;
    public static int googlePlayServiceVersion;
    public static String packageName;

    /* renamed from: me.cheshmak.android.sdk.core.Cheshmak$1 */
    static class C04741 implements C0473a {
        C04741() {
        }

        @TargetApi(14)
        /* renamed from: a */
        public void mo4393a() {
            Map hashMap = new HashMap();
            hashMap.put("CH_EVENT_CATEGORY", "0");
            hashMap.put("CH_EVENT_ACTION", "1");
            Cheshmak.f436b.m874a(TtmlNode.START, hashMap);
            C0478b.f453e = false;
        }

        /* renamed from: a */
        public void mo4394a(Activity activity) {
            C0477a.m656a().m679b(C0516a.m879a());
            C0478b.f453e = true;
            if (C0477a.m656a().m708j() != C0477a.m656a().m728w()) {
                C0477a.m656a().m713l(C0477a.m656a().m708j());
                C0516a.m884a(activity.getApplicationContext(), AlarmReceiver.class, 231728925, Long.valueOf(C0477a.m656a().m708j()));
            }
            C0550q.m1061a((Context) activity);
            if (!C0552s.m1073a(activity.getApplicationContext(), EventSendService.class)) {
                C0550q.m1065a(activity.getApplicationContext(), new Intent(activity.getApplicationContext(), EventSendService.class), true);
            }
            if (!"me.cheshmak.android.sdk.core.ui.WebActivity".equals(activity.getLocalClassName())) {
                List f = C0480a.m749f();
                if (f.size() != 0) {
                    try {
                        if (C0516a.m879a() - C0477a.m656a().m659B() >= C0477a.m656a().m660C()) {
                            new C0585j(activity, C0544l.m1035a(new JSONObject((String) f.get(0)))).mo4410a();
                        }
                    } catch (Throwable th) {
                        ThrowableExtension.printStackTrace(th);
                    }
                }
            }
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.Cheshmak$a */
    private static class C0475a implements UncaughtExceptionHandler {
        private C0475a() {
        }

        public void uncaughtException(java.lang.Thread r3, java.lang.Throwable r4) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x001a in list [B:9:0x0022]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1740223770.run(Unknown Source)
*/
            /*
            r2 = this;
            if (r4 == 0) goto L_0x000d;
        L_0x0002:
            r0 = new me.cheshmak.android.sdk.core.Cheshmak$b;	 Catch:{ Exception -> 0x001b, all -> 0x002a }
            r0.<init>(r4);	 Catch:{ Exception -> 0x001b, all -> 0x002a }
            r1 = 0;	 Catch:{ Exception -> 0x001b, all -> 0x002a }
            r1 = new java.lang.Void[r1];	 Catch:{ Exception -> 0x001b, all -> 0x002a }
            r0.execute(r1);	 Catch:{ Exception -> 0x001b, all -> 0x002a }
        L_0x000d:
            r0 = me.cheshmak.android.sdk.core.Cheshmak.f438d;
            if (r0 == 0) goto L_0x001a;
        L_0x0013:
            r0 = me.cheshmak.android.sdk.core.Cheshmak.f438d;
            r0.uncaughtException(r3, r4);
        L_0x001a:
            return;
        L_0x001b:
            r0 = move-exception;
            r0 = me.cheshmak.android.sdk.core.Cheshmak.f438d;
            if (r0 == 0) goto L_0x001a;
        L_0x0022:
            r0 = me.cheshmak.android.sdk.core.Cheshmak.f438d;
            r0.uncaughtException(r3, r4);
            goto L_0x001a;
        L_0x002a:
            r0 = move-exception;
            r1 = me.cheshmak.android.sdk.core.Cheshmak.f438d;
            if (r1 == 0) goto L_0x0038;
        L_0x0031:
            r1 = me.cheshmak.android.sdk.core.Cheshmak.f438d;
            r1.uncaughtException(r3, r4);
        L_0x0038:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.Cheshmak.a.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.Cheshmak$b */
    private static class C0476b extends AsyncTask<Void, Void, Void> {
        /* renamed from: a */
        Throwable f434a;

        C0476b(Throwable th) {
            this.f434a = th;
        }

        /* renamed from: a */
        protected Void m648a(Void... voidArr) {
            try {
                Cheshmak.f436b.m876a(this.f434a, true);
                if (C0523h.m928d(this.f434a)) {
                    try {
                        WeakHashMap weakHashMap = new WeakHashMap();
                        weakHashMap.put("SECTION", "SERVICE");
                        weakHashMap.put("CLASS", "Cheshmak");
                        weakHashMap.put("METHOD", "CheshmakUncaughtException");
                        C0545m.m1043a(0, weakHashMap, this.f434a);
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
                ThrowableExtension.printStackTrace(th2);
            }
            return null;
        }

        /* renamed from: a */
        protected void m649a(Void voidR) {
            super.onPostExecute(voidR);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m648a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m649a((Void) obj);
        }
    }

    private Cheshmak(Context context) {
        applicationContext = context.getApplicationContext();
        appVersion = C0520e.m914f(context);
        googlePlayServiceVersion = C0552s.m1075b(context);
        packageName = context.getPackageName();
        C0477a.m657a(context);
        C0480a.m736a(context);
        f441g = C0477a.m656a().m724s();
        C0480a.m737a(f441g);
        if (C0477a.m656a().m724s()) {
            m654b(context);
        }
        if (!C0477a.m656a().m700f()) {
            C0550q.m1064a(context, new Intent(context, EventSendService.class));
        }
        C0545m.m1044a(context);
        C0491a.m777a(context);
    }

    /* renamed from: a */
    private static void m651a(Context context) {
        if (VERSION.SDK_INT >= 14) {
            C0477a.m656a().m679b(0);
            C0496b.m787a((Application) context.getApplicationContext());
            C0496b.m788a(context).m793a(f440f);
        }
    }

    /* renamed from: a */
    private static void m652a(boolean z) {
        CheshmakAds.setAdsEnabled(z);
    }

    /* renamed from: b */
    private void m654b(Context context) {
        if (!C0516a.m892b(context, AlarmReceiver.class, 231728925)) {
            C0516a.m883a(context, AlarmReceiver.class, 231728925, C0477a.m656a().m728w());
        }
    }

    /* renamed from: c */
    private static boolean m655c() {
        return !"2.0.22".equals(C0477a.m656a().m725t());
    }

    public static void deleteAllTags() {
        if (f436b != null && f441g) {
            f436b.m873a("removealltags", null);
        }
    }

    public static void deleteTag(String str) {
        if (f436b != null && f441g && str != null) {
            List arrayList = new ArrayList();
            arrayList.add(str);
            f436b.m873a("removetags", arrayList);
        }
    }

    public static void deleteTags(List<String> list) {
        if (f436b != null && f441g && list != null) {
            f436b.m873a("removetags", (List) list);
        }
    }

    public static void disableAdvertises() {
        m652a(false);
    }

    public static void enableAdvertises() {
        m652a(true);
    }

    @TargetApi(14)
    public static void enableAutoActivityReports(Context context) {
        if (VERSION.SDK_INT >= 14) {
            Application application = (Application) context.getApplicationContext();
            if (f437c == null) {
                f437c = new C0494a();
                application.registerActivityLifecycleCallbacks(f437c);
            }
        }
    }

    public static void enableCheshmakExceptionHandler() {
        if (f438d == null) {
            f438d = Thread.getDefaultUncaughtExceptionHandler();
        }
        if (f439e == null) {
            f439e = new C0475a();
            Thread.setDefaultUncaughtExceptionHandler(f439e);
        }
    }

    public static void getSinglePushUniqueId(Context context, SinglePushResponseListener singlePushResponseListener) {
        if (C0477a.m656a() == null) {
            C0477a.m657a(context);
        }
        String I = C0477a.m656a().m666I();
        if (I != null) {
            singlePushResponseListener.onUniqueIdReceived(I);
        } else {
            C0571i.m1123a().m1130a(singlePushResponseListener);
        }
    }

    public static void initTracker(String str) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    String b = C0477a.m656a().m677b();
                    if (f436b == null) {
                        f436b = new C0515b();
                    }
                    if (!str.equals(b) || m655c()) {
                        C0477a.m656a().m726u();
                        C0477a.m656a().m675a(str);
                        C0477a.m656a().m674a(false);
                        C0477a.m656a().m692d("2.0.22");
                        C0480a.m742c();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                return;
            }
        }
        Log.e("CHESHMAK", "APPKEY IS INVALID");
    }

    public static void isTestDevice(boolean z) {
        if (z) {
            sendTag("is_test");
        } else {
            deleteTag("is_test");
        }
    }

    public static void sendTag(String str) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else if (str != null) {
            List arrayList = new ArrayList();
            arrayList.add(str);
            f436b.m877a(arrayList);
        }
    }

    public static void sendTags(List<String> list) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else if (list != null) {
            f436b.m877a((List) list);
        }
    }

    public static void setOrganization(String str) {
        try {
            C0477a.m656a().m696e(str);
        } catch (Throwable th) {
        }
    }

    public static void setPushLargeIcon(@DrawableRes int i) {
        try {
            C0477a.m656a().m669a(i);
        } catch (Exception e) {
        }
    }

    public static void setPushSmallIcon(@DrawableRes int i) {
        try {
            C0477a.m656a().m678b(i);
        } catch (Exception e) {
        }
    }

    public static void startView(String str) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else {
            f436b.m870a(str);
        }
    }

    public static void stopView(String str) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else {
            f436b.m878b(str);
        }
    }

    public static void trackEvent(String str) {
        Log.e("Cheshmak", "this method is a premium method, you can't access it with norma SDK");
    }

    public static void trackEvent(String str, Map<String, String> map) {
        Log.e("Cheshmak", "this method is a premium method, you can't access it with norma SDK");
    }

    public static void trackException(String str, Throwable th) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else {
            f436b.m871a(str, th);
        }
    }

    public static void trackException(String str, Throwable th, boolean z) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else {
            f436b.m872a(str, th, z);
        }
    }

    public static void trackException(Throwable th) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else {
            f436b.m875a(th);
        }
    }

    public static void trackException(Throwable th, boolean z) {
        if (f436b == null || !f441g) {
            Log.e("Cheshmak Error ", "Tracker is not initiated , Do you forget calling Cheshmak.with(this).initTracker(APP_KEY) ?");
        } else {
            f436b.m876a(th, z);
        }
    }

    public static Cheshmak with(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context should not be null.");
        }
        if (f435a == null) {
            f435a = new Cheshmak(context);
            m651a(context);
            enableCheshmakExceptionHandler();
            enableAutoActivityReports(context);
            Intent intent = new Intent(context, EventSendService.class);
            intent.setAction("me.cheshmak.android.sdk.advertise.action.AdvertiseID");
            C0550q.m1064a(context, intent);
        }
        return f435a;
    }
}
