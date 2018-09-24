package me.cheshmak.android.sdk.advertise;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p001a.p002a.p003a.C0233k;
import me.cheshmak.android.sdk.advertise.p017a.C0460a;
import me.cheshmak.android.sdk.core.network.C0558c;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.push.p030a.C0575a;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.advertise.k */
public class C0472k {
    /* renamed from: a */
    View f431a;
    /* renamed from: b */
    Context f432b;
    /* renamed from: c */
    Activity f433c;

    /* renamed from: me.cheshmak.android.sdk.advertise.k$1 */
    class C04711 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0472k f430a;

        C04711(C0472k c0472k) {
            this.f430a = c0472k;
        }

        public void run() {
            this.f430a.f431a.setVisibility(8);
        }
    }

    public C0472k(Context context, Activity activity) {
        this.f432b = context;
        this.f433c = activity;
    }

    public C0472k(Context context, View view) {
        this.f432b = context;
        this.f431a = view;
    }

    @JavascriptInterface
    public void action(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("action");
            if (string != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("me.cheshmak.data", jSONObject.getJSONObject("data"));
                new C0575a(string, this.f432b, C0544l.m1035a(jSONObject2)).m1150a();
            }
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    @JavascriptInterface
    public void alert(String str) {
        Dialog dialog = new Dialog(this.f432b);
        dialog.getWindow().requestFeature(1);
        View relativeLayout = new RelativeLayout(this.f432b);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.setPadding(30, 30, 30, 30);
        View scrollView = new ScrollView(this.f432b);
        View textView = new TextView(this.f432b);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(15);
        textView.setLayoutParams(layoutParams);
        scrollView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(str);
        scrollView.addView(textView);
        relativeLayout.addView(scrollView);
        dialog.setContentView(relativeLayout);
        dialog.show();
    }

    @JavascriptInterface
    public void close() {
        try {
            if (this.f433c != null) {
                this.f433c.finish();
            }
            if (this.f431a != null) {
                this.f431a.post(new C04711(this));
            }
        } catch (Exception e) {
        }
    }

    @JavascriptInterface
    public void loadInParallel(JSONArray jSONArray) {
        if (jSONArray != null) {
            new C0558c(jSONArray).m1095a();
        }
    }

    @JavascriptInterface
    public void log(String str, String str2, String str3) {
    }

    @JavascriptInterface
    public void onAdFailedToLoad() {
    }

    @JavascriptInterface
    public void onAdLoaded() {
    }

    @JavascriptInterface
    public void onAdOpened() {
    }

    @JavascriptInterface
    public void onClientReady() {
    }

    @JavascriptInterface
    public void onRemoveAdsRequested() {
    }

    @JavascriptInterface
    public void sendEvent(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appKey", C0477a.m656a().m677b());
                jSONObject.put("params", str2);
                jSONObject.put("deviceId", C0477a.m656a().m684c());
                jSONObject.put("options", new JSONObject());
                C0233k b = C0491a.m778b(this.f432b);
                if (b != null) {
                    b.m488a(new C0460a(C0468g.m634b(str), jSONObject.toString()));
                }
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }
}
