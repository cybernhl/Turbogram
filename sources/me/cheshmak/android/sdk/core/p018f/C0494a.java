package me.cheshmak.android.sdk.core.p018f;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.media.ExifInterface;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p029k.C0515b;

@TargetApi(14)
/* renamed from: me.cheshmak.android.sdk.core.f.a */
public class C0494a implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    private C0515b f475a;
    /* renamed from: b */
    private HashMap<String, Object> f476b;

    public C0494a() {
        if (this.f475a == null) {
            this.f475a = new C0515b();
            this.f476b = new HashMap();
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("CH_EVENT_CATEGORY", ExifInterface.GPS_MEASUREMENT_2D);
            hashMap.put("CH_EVENT_ACTION", "17");
            if (this.f476b.get(activity.getLocalClassName()) != null) {
                ArrayList arrayList = (ArrayList) this.f476b.get(activity.getLocalClassName());
                if (arrayList.get(1).equals(Long.valueOf(C0477a.m656a().m694e()))) {
                    hashMap.put("duration", Long.valueOf(C0516a.m879a() - ((Long) arrayList.get(0)).longValue()) + "");
                    this.f476b.remove(activity.getLocalClassName());
                    this.f476b.remove(Param.TIMESTAMP);
                }
            }
            this.f475a.m874a(activity.getLocalClassName(), hashMap);
        } catch (Exception e) {
        }
    }

    public void onActivityResumed(Activity activity) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("CH_EVENT_CATEGORY", ExifInterface.GPS_MEASUREMENT_2D);
            hashMap.put("CH_EVENT_ACTION", "16");
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(C0516a.m879a()));
            arrayList.add(Long.valueOf(C0477a.m656a().m694e()));
            this.f476b.put(activity.getLocalClassName(), arrayList);
            this.f475a.m874a(activity.getLocalClassName(), hashMap);
        } catch (Exception e) {
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
