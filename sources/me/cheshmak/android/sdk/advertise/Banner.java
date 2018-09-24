package me.cheshmak.android.sdk.advertise;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import org.json.JSONObject;

public class Banner extends C0453a implements OnSharedPreferenceChangeListener, C0454e {
    public static final String TAG = "Adv/Banner";

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m600b();
    }

    @TargetApi(11)
    /* renamed from: b */
    private void m600b() {
        if (CheshmakAds.isAdsEnabled()) {
            C0464b.m625a().m627a(C0468g.m631a(getContext()), this);
        }
    }

    /* renamed from: c */
    private void m601c() {
        setVisibility(8);
    }

    public int[] getSize() {
        return new int[]{480, 64};
    }

    protected void onAttachedToWindow() {
        C0477a.m656a().m671a((OnSharedPreferenceChangeListener) this);
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        C0477a.m656a().m680b((OnSharedPreferenceChangeListener) this);
        super.onDetachedFromWindow();
    }

    public void onFailureResponse(Exception exception) {
    }

    public void onReadyResponse(final String str) {
        if (str != null) {
            this.a.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ Banner f398b;

                public void run() {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("webviewContent", null);
                        if (optString != null) {
                            this.f398b.loadDataWithBaseURL("https://ads.cheshmak.me", optString, "text/html", "utf-8", null);
                        }
                        String optString2 = jSONObject.optString("webviewURL", null);
                        if (optString2 != null) {
                            this.f398b.loadUrl(optString2);
                        }
                        if (optString != null || optString2 != null) {
                            this.f398b.m599a();
                            this.f398b.setVisibility(0);
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!TextUtils.equals(C0477a.m656a().m667J(), str)) {
            return;
        }
        if (C0477a.m656a().m668K()) {
            m600b();
        } else {
            m601c();
        }
    }

    public String type() {
        return "banner";
    }
}
