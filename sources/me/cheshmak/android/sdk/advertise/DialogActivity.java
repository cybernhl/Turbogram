package me.cheshmak.android.sdk.advertise;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import com.mohamadamin.persianmaterialdatetimepicker.date.MonthView;
import me.cheshmak.android.sdk.C0451R;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0521f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.tgnet.ConnectionsManager;

public class DialogActivity extends Activity {
    /* renamed from: a */
    LocalBroadcastManager f405a;
    /* renamed from: b */
    BroadcastReceiver f406b = new C04551(this);
    /* renamed from: c */
    public boolean f407c = false;
    /* renamed from: d */
    private String f408d;

    /* renamed from: me.cheshmak.android.sdk.advertise.DialogActivity$1 */
    class C04551 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ DialogActivity f403a;

        C04551(DialogActivity dialogActivity) {
            this.f403a = dialogActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if ("me.cheshmak.push.action.close".equals(intent.getAction())) {
                CharSequence stringExtra = intent.getStringExtra("pushId");
                if (stringExtra != null) {
                    C0480a.m743c(stringExtra);
                }
                if (TextUtils.equals(stringExtra, this.f403a.f408d)) {
                    this.f403a.finish();
                }
            }
        }
    }

    /* renamed from: me.cheshmak.android.sdk.advertise.DialogActivity$2 */
    class C04562 extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ DialogActivity f404a;

        C04562(DialogActivity dialogActivity) {
            this.f404a = dialogActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            webView.setVisibility(0);
        }
    }

    /* renamed from: a */
    private void m603a(LayoutParams layoutParams, String str, String str2) {
        try {
            Point j = C0520e.m919j(getApplicationContext());
            if (j != null) {
                if (str2 != null) {
                    if (str2.contains("dp")) {
                        layoutParams.height = Integer.valueOf(str2.replace("dp", "")).intValue();
                    } else if (str2.contains("%")) {
                        layoutParams.height = (int) Math.ceil((double) ((((float) Integer.valueOf(str2.replace("%", "")).intValue()) / 100.0f) * ((float) j.y)));
                    }
                }
                if (str == null) {
                    return;
                }
                if (str.contains("dp")) {
                    layoutParams.width = Integer.valueOf(str.replace("dp", "")).intValue();
                } else if (str.contains("%")) {
                    layoutParams.width = (int) Math.ceil((double) (((float) j.x) * (((float) Integer.valueOf(str.replace("%", "")).intValue()) / 100.0f)));
                }
            }
        } catch (Exception e) {
        }
    }

    public void onBackPressed() {
        C0521f.m920a("onBackPressed", this, new JSONObject());
        super.onBackPressed();
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0451R.layout.activity_web);
        try {
            WebView webView = (WebView) findViewById(C0451R.id.ches_custom_web);
            webView.setScrollBarStyle(ConnectionsManager.FileTypeVideo);
            webView.getSettings().setAppCacheEnabled(false);
            webView.getSettings().setCacheMode(2);
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.setFocusable(false);
            webView.addJavascriptInterface(new C0472k(getApplicationContext(), (Activity) this), "Cheshmak");
            webView.clearCache(true);
            webView.clearHistory();
            if (VERSION.SDK_INT >= 11) {
                webView.setLayerType(1, null);
            }
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("pushId")) {
                this.f408d = intent.getStringExtra("pushId");
            }
            if (!(intent == null || intent.getStringExtra("data") == null)) {
                JSONObject jSONObject = new JSONObject(intent.getStringExtra("data"));
                int optInt = jSONObject.optInt("marginTop", 0);
                int optInt2 = jSONObject.optInt("marginBottom", 0);
                int optInt3 = jSONObject.optInt("marginLeft", 0);
                int optInt4 = jSONObject.optInt("marginRight", 0);
                String optString = jSONObject.optString("width");
                String optString2 = jSONObject.optString(MonthView.VIEW_PARAMS_HEIGHT);
                JSONArray optJSONArray = jSONObject.optJSONArray("positions");
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
                layoutParams.setMargins(optInt3, optInt, optInt4, optInt2);
                m603a(layoutParams, optString, optString2);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        layoutParams.addRule(optJSONArray.getInt(i), -1);
                    }
                }
                webView.setLayoutParams(layoutParams);
                String optString3 = jSONObject.optString("webviewURL", null);
                String optString4 = jSONObject.optString("webviewContent", null);
                if (optString3 != null) {
                    webView.loadUrl(optString3);
                } else if (optString4 != null) {
                    webView.loadDataWithBaseURL("https://ads.cheshmak.me", optString4, "text/html", "utf-8", null);
                }
                webView.setWebViewClient(new C04562(this));
                webView.getSettings().setJavaScriptEnabled(true);
                this.f405a = LocalBroadcastManager.getInstance(this);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("me.cheshmak.push.action.close");
                this.f405a.registerReceiver(this.f406b, intentFilter);
            }
            C0521f.m920a("Displayed", this, new JSONObject());
        } catch (Exception e) {
            try {
                if (e instanceof JSONException) {
                    this.f407c = true;
                }
                finish();
            } catch (Exception e2) {
            }
        }
    }

    protected void onPause() {
        super.onPause();
        try {
            this.f405a.unregisterReceiver(this.f406b);
        } catch (Exception e) {
        }
    }

    protected void onUserLeaveHint() {
        C0521f.m920a("homePressed", this, new JSONObject());
        super.onUserLeaveHint();
    }
}
