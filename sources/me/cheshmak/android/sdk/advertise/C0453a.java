package me.cheshmak.android.sdk.advertise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

/* renamed from: me.cheshmak.android.sdk.advertise.a */
public abstract class C0453a extends WebView {
    /* renamed from: a */
    protected Handler f399a;

    public C0453a(Context context) {
        super(context);
        m597b();
    }

    public C0453a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m597b();
    }

    public C0453a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m597b();
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface", "NewApi"})
    /* renamed from: b */
    private void m597b() {
        if (CheshmakAds.isAdsEnabled()) {
            m598c();
            setVisibility(8);
            setVerticalScrollBarEnabled(false);
            setHorizontalScrollBarEnabled(false);
            getSettings().setJavaScriptEnabled(true);
            getSettings().setDomStorageEnabled(true);
            getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            setFocusable(false);
            addJavascriptInterface(new C0472k(getContext(), (View) this), "Cheshmak");
            clearCache(true);
            clearHistory();
            getSettings().setAppCacheEnabled(false);
            getSettings().setCacheMode(2);
            getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            getSettings().setLoadWithOverviewMode(true);
            getSettings().setUseWideViewPort(true);
            setWebChromeClient(new WebChromeClient());
            if (VERSION.SDK_INT >= 19) {
                C0453a.setWebContentsDebuggingEnabled(true);
            }
            getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
            this.f399a = new Handler(Looper.getMainLooper());
        } else if (CheshmakAds.isLoggingEnabled()) {
            Log.d("DEBUG_CHESHMAK", "Cheshmak Ads is disabled. Read the docs to find out how to enable it");
        }
    }

    /* renamed from: c */
    private void m598c() {
        LayoutParams layoutParams = getLayoutParams();
        setBackgroundColor(Color.parseColor("#00000000"));
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -1);
        } else {
            layoutParams.width = -1;
            layoutParams.height = -1;
        }
        setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    protected void m599a() {
        int i = getSize()[0];
        int i2 = getSize()[1];
        int round = Math.round(((Float) C0470i.m641a(getContext(), "DISPLAY_DENSITY")).floatValue() * ((float) i));
        i2 = Math.round(((Float) C0470i.m641a(getContext(), "DISPLAY_DENSITY")).floatValue() * ((float) i2));
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(round, i2);
        } else {
            layoutParams.width = round;
            layoutParams.height = i2;
        }
        setLayoutParams(layoutParams);
    }

    public abstract int[] getSize();

    public abstract String type();
}
