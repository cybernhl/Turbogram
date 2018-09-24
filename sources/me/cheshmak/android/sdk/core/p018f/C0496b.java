package me.cheshmak.android.sdk.core.p018f;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@TargetApi(14)
/* renamed from: me.cheshmak.android.sdk.core.f.b */
public class C0496b implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    private static C0496b f478a;
    /* renamed from: b */
    private boolean f479b = false;
    /* renamed from: c */
    private boolean f480c = true;
    /* renamed from: d */
    private Handler f481d = new Handler();
    /* renamed from: e */
    private List<C0473a> f482e = new CopyOnWriteArrayList();
    /* renamed from: f */
    private Runnable f483f;

    /* renamed from: me.cheshmak.android.sdk.core.f.b$a */
    public interface C0473a {
        /* renamed from: a */
        void mo4393a();

        /* renamed from: a */
        void mo4394a(Activity activity);
    }

    /* renamed from: me.cheshmak.android.sdk.core.f.b$1 */
    class C04951 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0496b f477a;

        C04951(C0496b c0496b) {
            this.f477a = c0496b;
        }

        public void run() {
            if (this.f477a.f479b && this.f477a.f480c) {
                this.f477a.f479b = false;
                for (C0473a a : this.f477a.f482e) {
                    try {
                        a.mo4393a();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static C0496b m786a() {
        if (f478a == null) {
            try {
                throw new IllegalStateException("Foreground is not initialised - invoke at least once with parameterised init/get");
            } catch (IllegalStateException e) {
            }
        }
        return f478a;
    }

    @TargetApi(14)
    /* renamed from: a */
    public static C0496b m787a(Application application) {
        if (f478a == null) {
            f478a = new C0496b();
            application.registerActivityLifecycleCallbacks(f478a);
        }
        return f478a;
    }

    /* renamed from: a */
    public static C0496b m788a(Context context) {
        if (f478a == null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext instanceof Application) {
                C0496b.m787a((Application) applicationContext);
            }
            try {
                throw new IllegalStateException("Foreground is not initialised and cannot obtain the Application object");
            } catch (IllegalStateException e) {
            }
        }
        return f478a;
    }

    /* renamed from: a */
    public void m793a(C0473a c0473a) {
        this.f482e.add(c0473a);
    }

    /* renamed from: b */
    public boolean m794b() {
        return this.f479b;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        this.f480c = true;
        if (this.f483f != null) {
            this.f481d.removeCallbacks(this.f483f);
        }
        Handler handler = this.f481d;
        Runnable c04951 = new C04951(this);
        this.f483f = c04951;
        handler.postDelayed(c04951, 500);
    }

    public void onActivityResumed(Activity activity) {
        boolean z = false;
        this.f480c = false;
        if (!this.f479b) {
            z = true;
        }
        this.f479b = true;
        if (this.f483f != null) {
            this.f481d.removeCallbacks(this.f483f);
        }
        if (z) {
            for (C0473a a : this.f482e) {
                try {
                    a.mo4394a(activity);
                } catch (Exception e) {
                }
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
