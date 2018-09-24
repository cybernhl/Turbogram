package me.cheshmak.android.sdk.core.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PointerIconCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import me.cheshmak.android.sdk.C0451R;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p021c.C0481a;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0543k;
import me.cheshmak.android.sdk.core.push.p030a.C0575a;
import me.cheshmak.android.sdk.core.view.DualSelectorView;
import me.cheshmak.android.sdk.core.view.DualSelectorView.C0599a;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateDialogActivity extends Activity implements OnClickListener, C0599a {
    /* renamed from: a */
    BroadcastReceiver f759a = new C05961(this);
    /* renamed from: b */
    private TextView f760b;
    /* renamed from: c */
    private TextView f761c;
    /* renamed from: d */
    private DualSelectorView f762d;
    /* renamed from: e */
    private boolean f763e;
    /* renamed from: f */
    private String f764f;
    /* renamed from: g */
    private Button f765g;
    /* renamed from: h */
    private Button f766h;
    /* renamed from: i */
    private C0481a f767i;
    /* renamed from: j */
    private LocalBroadcastManager f768j;
    /* renamed from: k */
    private String f769k;
    /* renamed from: l */
    private String f770l;
    /* renamed from: m */
    private HorizontalScrollView f771m;
    /* renamed from: n */
    private LinearLayout f772n;
    /* renamed from: o */
    private String[] f773o;

    /* renamed from: me.cheshmak.android.sdk.core.ui.UpdateDialogActivity$1 */
    class C05961 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ UpdateDialogActivity f755a;

        C05961(UpdateDialogActivity updateDialogActivity) {
            this.f755a = updateDialogActivity;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals("me.cheshmak.push.action.close")) {
                    String stringExtra = intent.getStringExtra("pushId");
                    if (stringExtra != null) {
                        C0480a.m743c(stringExtra);
                    }
                    this.f755a.finish();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: c */
    private void m1209c() {
        this.f760b = (TextView) findViewById(C0451R.id.install_dialog_title);
        this.f761c = (TextView) findViewById(C0451R.id.install_dialog_message);
        this.f762d = (DualSelectorView) findViewById(C0451R.id.update_options);
        this.f765g = (Button) findViewById(C0451R.id.install_btn);
        this.f766h = (Button) findViewById(C0451R.id.update_cancel_btn);
        this.f771m = (HorizontalScrollView) findViewById(C0451R.id.market_list_container);
        this.f772n = (LinearLayout) findViewById(C0451R.id.market_list);
        this.f771m.setVisibility(4);
    }

    /* renamed from: d */
    private void m1210d() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(getIntent().getStringExtra("data"));
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
            jSONObject = null;
        }
        this.f763e = getIntent().getBooleanExtra("force", false);
        this.f764f = getIntent().getStringExtra("url");
        this.f769k = jSONObject.optString("title", "");
        this.f770l = jSONObject.optString("bigText");
        this.f767i = C0481a.m753a(getIntent().getStringExtra("updateMethod"));
        this.f773o = getIntent().getStringExtra("markets").replace("[", "").replace("]", "").replace("\"", "").replace(" ", "").split(",");
        Log.i("MY LOG", "packages = " + getIntent().getStringExtra("markets"));
        switch (this.f767i) {
            case ALL:
                this.f762d.setVisibility(0);
                this.f767i = C0481a.MARKET;
                return;
            default:
                this.f762d.setVisibility(8);
                return;
        }
    }

    /* renamed from: e */
    private void m1211e() {
        if (this.f772n.getChildCount() == 0) {
            for (final String str : this.f773o) {
                try {
                    Drawable applicationIcon = getPackageManager().getApplicationIcon(str);
                    View imageView = new ImageView(this);
                    imageView.setLayoutParams(new LayoutParams(-2, -2));
                    imageView.setImageDrawable(applicationIcon);
                    imageView.setOnClickListener(new OnClickListener(this) {
                        /* renamed from: b */
                        final /* synthetic */ UpdateDialogActivity f757b;

                        public void onClick(View view) {
                            this.f757b.m1215i();
                            String str = str;
                            Object obj = -1;
                            switch (str.hashCode()) {
                                case -1643329052:
                                    if (str.equals(LicenseChecker.MYKET_PACKAGE_NAME)) {
                                        obj = 1;
                                        break;
                                    }
                                    break;
                                case -1046965711:
                                    if (str.equals("com.android.vending")) {
                                        obj = 3;
                                        break;
                                    }
                                    break;
                                case -715767161:
                                    if (str.equals("ir.tgbs.android.iranapp")) {
                                        obj = 2;
                                        break;
                                    }
                                    break;
                                case -175711094:
                                    if (str.equals("com.farsitel.bazaar")) {
                                        obj = null;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj) {
                                case null:
                                    C0543k.m1031a(this.f757b, C0520e.m916g(this.f757b));
                                    break;
                                case 1:
                                    C0543k.m1032b(this.f757b, C0520e.m916g(this.f757b));
                                    break;
                                case 2:
                                    C0543k.m1033c(this.f757b, C0520e.m916g(this.f757b));
                                    break;
                                case 3:
                                    C0543k.m1034d(this.f757b, C0520e.m916g(this.f757b));
                                    break;
                            }
                            this.f757b.finish();
                        }
                    });
                    this.f772n.addView(imageView);
                } catch (Throwable e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
    }

    /* renamed from: f */
    private void m1212f() {
        m1214h();
        m1211e();
    }

    /* renamed from: g */
    private void m1213g() {
        try {
            JSONObject jSONObject = new JSONObject(new JSONArray(new JSONObject(getIntent().getStringExtra("data")).optString("buttons", "[]")).optString(0));
            Bundle bundle = new Bundle();
            bundle.putString("me.cheshmak.data", jSONObject.optString("data"));
            new C0575a("10", getApplicationContext(), bundle).m1150a();
            finish();
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* renamed from: h */
    private void m1214h() {
        this.f771m.setVisibility(0);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.f771m.getHeight(), 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        this.f771m.startAnimation(translateAnimation);
    }

    /* renamed from: i */
    private void m1215i() {
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.f771m.getHeight());
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        this.f771m.startAnimation(translateAnimation);
    }

    /* renamed from: a */
    public void mo4415a() {
        this.f767i = C0481a.MARKET;
    }

    /* renamed from: b */
    public void mo4416b() {
        this.f767i = C0481a.DIRECT;
    }

    public void onBackPressed() {
    }

    public void onClick(View view) {
        if (view.getId() != C0451R.id.install_btn) {
            finish();
        } else if (this.f767i.m754a().equals(C0481a.MARKET.m754a())) {
            m1212f();
        } else {
            m1215i();
            if (VERSION.SDK_INT < 23) {
                m1213g();
            } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                m1213g();
            } else {
                requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, PointerIconCompat.TYPE_ALIAS);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0451R.layout.activity_update);
        m1209c();
        m1210d();
        this.f762d.m1219a(this);
        this.f761c.setText(this.f770l);
        this.f760b.setText(this.f769k);
        this.f765g.setOnClickListener(this);
        this.f766h.setOnClickListener(this);
        if (this.f763e) {
            this.f766h.setVisibility(8);
        } else {
            this.f766h.setVisibility(0);
        }
        this.f762d.m1220a(C0451R.string.update_option_market, C0451R.string.update_option_direct);
        this.f768j = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("me.cheshmak.push.action.close");
        this.f768j.registerReceiver(this.f759a, intentFilter);
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            this.f768j.unregisterReceiver(this.f759a);
        } catch (Exception e) {
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == PointerIconCompat.TYPE_ALIAS && iArr[0] == 0) {
            m1213g();
        }
    }
}
