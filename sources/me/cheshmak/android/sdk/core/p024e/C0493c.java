package me.cheshmak.android.sdk.core.p024e;

import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.p001a.p002a.p003a.C0225i;
import com.p001a.p002a.p003a.p007b.C0160a;
import me.cheshmak.android.sdk.advertise.C0469h;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0519d;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.receivers.C0590a;
import me.cheshmak.android.sdk.core.task.C0459a;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.e.c */
class C0493c implements C0160a {
    C0493c() {
    }

    /* renamed from: a */
    public void mo4398a(@NonNull C0225i c0225i) {
    }

    /* renamed from: a */
    public void mo4399a(@NonNull C0225i c0225i, int i) {
        if (1 == i) {
            try {
                C0459a c0459a = (C0459a) c0225i;
                if (c0459a.m415d() != null && c0459a.m415d().contains("EventJob")) {
                    Object o = c0459a.m610o();
                    String string = c0459a.m609n().getString("response");
                    C0519d.m898b("JobCallback onJobRun", "response: " + string);
                    if (!TextUtils.isEmpty(o) && C0544l.m1037a(string)) {
                        long optLong;
                        boolean optBoolean;
                        int b;
                        JSONObject jSONObject = new JSONObject(string);
                        JSONObject jSONObject2 = new JSONObject(o);
                        String string2 = jSONObject2.getString("adsType");
                        jSONObject.put(Param.TYPE, jSONObject2.getInt(Param.TYPE));
                        if (jSONObject.has("data")) {
                            jSONObject2 = jSONObject.getJSONObject("data").optJSONObject("setting");
                            if (jSONObject2 != null) {
                                optLong = jSONObject2.optLong("showTime", 0);
                                optBoolean = jSONObject2.optBoolean("showWhenScreenOn", false);
                                C0519d.m898b("advScheduledPush", "inja mikhad schedule kone ba in parameter showTime:" + optLong);
                                if (optLong <= 0) {
                                    b = C0516a.m891b(optLong);
                                    jSONObject.put("scheduleID", b);
                                    C0480a.m732a(1, jSONObject);
                                    C0516a.m886a(c0225i.m422k(), jSONObject.toString(), optLong, string2, b);
                                    C0519d.m898b("advScheduledPush", "inja schedule kard ");
                                }
                                C0480a.m732a(1, jSONObject);
                                if (optBoolean) {
                                    C0469h.m639a(c0225i.m422k(), string2, jSONObject, jSONObject.optString("params"));
                                    return;
                                } else if (C0520e.m917h(c0225i.m422k())) {
                                    c0225i.m422k().registerReceiver(new C0590a(), new IntentFilter("android.intent.action.SCREEN_ON"));
                                    return;
                                } else {
                                    C0469h.m639a(c0225i.m422k(), string2, jSONObject, jSONObject.getString("params"));
                                    return;
                                }
                            }
                        }
                        optBoolean = false;
                        optLong = 0;
                        C0519d.m898b("advScheduledPush", "inja mikhad schedule kone ba in parameter showTime:" + optLong);
                        if (optLong <= 0) {
                            C0480a.m732a(1, jSONObject);
                            if (optBoolean) {
                                C0469h.m639a(c0225i.m422k(), string2, jSONObject, jSONObject.optString("params"));
                                return;
                            } else if (C0520e.m917h(c0225i.m422k())) {
                                c0225i.m422k().registerReceiver(new C0590a(), new IntentFilter("android.intent.action.SCREEN_ON"));
                                return;
                            } else {
                                C0469h.m639a(c0225i.m422k(), string2, jSONObject, jSONObject.getString("params"));
                                return;
                            }
                        }
                        b = C0516a.m891b(optLong);
                        jSONObject.put("scheduleID", b);
                        C0480a.m732a(1, jSONObject);
                        C0516a.m886a(c0225i.m422k(), jSONObject.toString(), optLong, string2, b);
                        C0519d.m898b("advScheduledPush", "inja schedule kard ");
                    }
                }
            } catch (Throwable e) {
                C0519d.m899b("JobCallback", "onJobRun", e);
            }
        }
    }

    /* renamed from: a */
    public void mo4400a(@NonNull C0225i c0225i, boolean z, @Nullable Throwable th) {
    }

    /* renamed from: b */
    public void mo4401b(@NonNull C0225i c0225i) {
    }

    /* renamed from: b */
    public void mo4402b(@NonNull C0225i c0225i, int i) {
    }
}
