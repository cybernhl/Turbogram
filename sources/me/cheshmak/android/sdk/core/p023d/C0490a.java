package me.cheshmak.android.sdk.core.p023d;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.math.BigDecimal;
import java.util.List;
import me.cheshmak.android.sdk.advertise.C0469h;
import me.cheshmak.android.sdk.core.p022l.C0519d;
import me.cheshmak.android.sdk.core.p022l.C0541i;
import me.cheshmak.android.sdk.core.p022l.C0541i.C0482d;
import me.cheshmak.android.sdk.core.p022l.C0541i.C0485c;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.push.p030a.C0575a;
import net.hockeyapp.android.UpdateFragment;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.d.a */
public class C0490a {
    /* renamed from: a */
    private static C0482d f472a = new C04831();
    /* renamed from: b */
    private static C0482d f473b = new C04842();

    /* renamed from: me.cheshmak.android.sdk.core.d.a$1 */
    static class C04831 implements C0482d {
        C04831() {
        }

        /* renamed from: a */
        public BigDecimal mo4395a() {
            return BigDecimal.ZERO;
        }

        /* renamed from: b */
        public String mo4396b() {
            return "0";
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.d.a$2 */
    static class C04842 implements C0482d {
        C04842() {
        }

        /* renamed from: a */
        public BigDecimal mo4395a() {
            return BigDecimal.ONE;
        }

        /* renamed from: b */
        public String mo4396b() {
            return "1";
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.d.a$3 */
    static class C04863 extends C0485c {
        /* renamed from: a */
        final /* synthetic */ Context f468a;

        C04863(C0541i c0541i, String str, int i, boolean z, Context context) {
            this.f468a = context;
            c0541i.getClass();
            super(c0541i, str, i, z);
        }

        /* renamed from: a */
        public C0482d mo4397a(List<C0482d> list) {
            return C0552s.m1078c(this.f468a, ((C0482d) list.get(0)).mo4396b()) ? C0490a.f473b : C0490a.f472a;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.d.a$4 */
    static class C04874 extends C0485c {
        /* renamed from: a */
        final /* synthetic */ Context f469a;

        C04874(C0541i c0541i, String str, int i, Context context) {
            this.f469a = context;
            c0541i.getClass();
            super(c0541i, str, i);
        }

        /* renamed from: a */
        public C0482d mo4397a(List<C0482d> list) {
            try {
                JSONObject b = C0544l.m1039b(((C0482d) list.get(0)).mo4396b());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("me.cheshmak.data", b.getJSONObject("data"));
                new C0575a(b.optString("action"), this.f469a, C0544l.m1035a(jSONObject)).m1150a();
            } catch (Throwable e) {
                C0519d.m897a("Functions", "action", e);
            }
            return C0490a.f473b;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.d.a$5 */
    static class C04885 extends C0485c {
        C04885(C0541i c0541i, String str, int i) {
            c0541i.getClass();
            super(c0541i, str, i);
        }

        /* renamed from: a */
        public C0482d mo4397a(List<C0482d> list) {
            C0519d.m896a("Expression", "nothing");
            return C0490a.f473b;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.d.a$6 */
    static class C04896 extends C0485c {
        /* renamed from: a */
        final /* synthetic */ Context f470a;
        /* renamed from: b */
        final /* synthetic */ String f471b;

        C04896(C0541i c0541i, String str, int i, Context context, String str2) {
            this.f470a = context;
            this.f471b = str2;
            c0541i.getClass();
            super(c0541i, str, i);
        }

        /* renamed from: a */
        public C0482d mo4397a(List<C0482d> list) {
            try {
                C0519d.m896a("Expression", "showAdv");
                JSONObject b = C0544l.m1039b(((C0482d) list.get(0)).mo4396b());
                C0469h.m639a(this.f470a, this.f471b, b, b.getString("params"));
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
            return C0552s.m1078c(this.f470a, ((C0482d) list.get(0)).mo4396b()) ? C0490a.f473b : C0490a.f472a;
        }
    }

    /* renamed from: a */
    public static C0485c m769a(C0541i c0541i) {
        c0541i.getClass();
        return new C04885(c0541i, "nothing", 0);
    }

    /* renamed from: a */
    public static C0485c m770a(C0541i c0541i, Context context) {
        c0541i.getClass();
        return new C04863(c0541i, "packageIsInstalled", 1, true, context);
    }

    /* renamed from: a */
    private static C0485c m771a(C0541i c0541i, Context context, String str) {
        String str2 = "";
        if (str.equals(UpdateFragment.FRAGMENT_DIALOG)) {
            str2 = "showDialog";
        } else if (str.equals("notification")) {
            str2 = "showNotification";
        }
        c0541i.getClass();
        return new C04896(c0541i, str2, 1, context, str);
    }

    /* renamed from: b */
    public static C0485c m773b(C0541i c0541i, Context context) {
        c0541i.getClass();
        return new C04874(c0541i, "action", 1, context);
    }

    /* renamed from: c */
    public static C0485c m775c(C0541i c0541i, Context context) {
        return C0490a.m771a(c0541i, context, "notification");
    }

    /* renamed from: d */
    public static C0485c m776d(C0541i c0541i, Context context) {
        return C0490a.m771a(c0541i, context, UpdateFragment.FRAGMENT_DIALOG);
    }
}
