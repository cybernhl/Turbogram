package me.cheshmak.android.sdk.advertise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.media.ExifInterface;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.p001a.p002a.p003a.C0225i;
import com.p001a.p002a.p003a.C0233k;
import java.math.BigDecimal;
import me.cheshmak.android.sdk.advertise.p017a.C0460a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0518c;
import me.cheshmak.android.sdk.core.p022l.C0519d;
import me.cheshmak.android.sdk.core.p022l.C0541i;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p023d.C0490a;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p027i.C0512d;
import me.cheshmak.android.sdk.core.push.p030a.C0575a;
import me.cheshmak.android.sdk.core.task.C0591b;
import net.hockeyapp.android.UpdateFragment;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.advertise.h */
public class C0469h {
    /* renamed from: a */
    public static void m635a(Context context, Bundle bundle) {
        C0469h.m638a(context, bundle.getString("params"), bundle.getString("adsType"), C0544l.m1038b(bundle).toString());
    }

    /* renamed from: a */
    private static void m636a(Context context, String str) {
        C0233k b = C0491a.m778b(context);
        if (b != null) {
            b.m488a(new C0591b(str, 0));
        }
    }

    /* renamed from: a */
    private static void m637a(Context context, String str, String str2) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    obj = 2;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    obj = null;
                    break;
                }
                break;
            case 50:
                if (str.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    obj = 1;
                    break;
                }
                break;
            case 51:
                if (str.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    obj = 3;
                    break;
                }
                break;
        }
        JSONObject jSONObject;
        switch (obj) {
            case null:
                try {
                    C0469h.m639a(context, UpdateFragment.FRAGMENT_DIALOG, new JSONObject(str2), null);
                    return;
                } catch (Throwable e) {
                    C0519d.m899b("CoreAdvertise", "handleExpression:1", e);
                    return;
                }
            case 1:
                try {
                    jSONObject = new JSONObject(str2);
                    C0469h.m639a(context, "notification", jSONObject, jSONObject.getString("params"));
                    return;
                } catch (Throwable e2) {
                    C0519d.m899b("CoreAdvertise", "handleExpression:2", e2);
                    return;
                }
            case 2:
                C0519d.m896a("CoreAdvertise", "handleExpression:0:do nothing");
                return;
            case 3:
                try {
                    jSONObject = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject(str2);
                    jSONObject.put("me.cheshmak.data", jSONObject2.getString("data"));
                    new C0575a(jSONObject2.optString("action"), context, C0544l.m1035a(jSONObject)).m1150a();
                    return;
                } catch (Throwable e22) {
                    C0519d.m899b("CoreAdvertise", "handleExpression:3", e22);
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    private static void m638a(Context context, String str, String str2, String str3) {
        try {
            C0233k b = C0491a.m778b(context);
            if (b != null) {
                C0225i c0460a = new C0460a(C0468g.m632a(str2), C0468g.m633a(context, str).toString());
                c0460a.mo4384a(str3);
                b.m488a(c0460a);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static void m639a(Context context, String str, JSONObject jSONObject, String str2) {
        if (CheshmakAds.isAdsEnabled() && str != null) {
            Object obj = -1;
            try {
                switch (str.hashCode()) {
                    case -1332085432:
                        if (str.equals(UpdateFragment.FRAGMENT_DIALOG)) {
                            int i = 1;
                            break;
                        }
                        break;
                    case -861311717:
                        if (str.equals("condition")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 595233003:
                        if (str.equals("notification")) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        if (jSONObject.has("data")) {
                            C0519d.m898b("onReceive ", " push received id: " + jSONObject.getString(TtmlNode.ATTR_ID));
                            new C0512d(context, jSONObject.getJSONObject("data").put("isAdvertise", true).put(TtmlNode.ATTR_ID, jSONObject.getString(TtmlNode.ATTR_ID)).put("params", str2).toString()).m864a();
                            return;
                        }
                        return;
                    case 1:
                        Intent intent = new Intent("me.cheshmak.push.action.close");
                        intent.putExtra("pushId", jSONObject.optString(TtmlNode.ATTR_ID));
                        intent.setClass(context, DialogActivity.class);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        intent = new Intent(context, DialogActivity.class);
                        intent.putExtra("data", jSONObject.toString());
                        intent.putExtra("pushId", jSONObject.optString(TtmlNode.ATTR_ID));
                        context.startActivity(intent);
                        return;
                    case 2:
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                        C0541i c0541i = new C0541i(jSONObject2.getString("expression"));
                        c0541i.m1029a(C0490a.m773b(c0541i, context));
                        c0541i.m1029a(C0490a.m769a(c0541i));
                        c0541i.m1029a(C0490a.m770a(c0541i, context));
                        c0541i.m1029a(C0490a.m776d(c0541i, context));
                        c0541i.m1029a(C0490a.m775c(c0541i, context));
                        BigDecimal a = c0541i.m1026a();
                        if (a != null && jSONObject2.has(a.toPlainString())) {
                            C0469h.m637a(context, a.toPlainString(), jSONObject2.getString(a.toPlainString()));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                C0519d.m898b("showAdvertise", "error " + e.getMessage());
            }
        }
    }

    /* renamed from: b */
    public static void m640b(Context context, @NonNull Bundle bundle) {
        String string = bundle.getString(Param.TYPE, "");
        Object obj = -1;
        switch (string.hashCode()) {
            case 49586:
                if (string.equals("200")) {
                    obj = 1;
                    break;
                }
                break;
            case 49587:
                if (string.equals("201")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                String string2 = bundle.getString(TtmlNode.ATTR_ID);
                C0519d.m898b("onReceive ", "cancel push received id: " + string2);
                if (C0480a.m745d(string2) != 0) {
                    int e = C0480a.m746e(string2);
                    if (e != 0) {
                        C0516a.m881a(context, e);
                    }
                }
                C0469h.m636a(context, string2);
                C0480a.m743c(string2);
                return;
            case 1:
                C0469h.m635a(context, bundle);
                return;
            default:
                if (TextUtils.isDigitsOnly(bundle.getString(Param.TYPE)) && Integer.parseInt(bundle.getString(Param.TYPE)) < 100) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("me.cheshmak.data", bundle.getString("customData"));
                    C0518c.m895a(context.getApplicationContext(), bundle.getString(Param.TYPE), bundle2);
                    return;
                }
                return;
        }
    }
}
