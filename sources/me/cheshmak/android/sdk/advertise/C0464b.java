package me.cheshmak.android.sdk.advertise;

import android.net.Uri;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import me.cheshmak.android.sdk.core.network.C0555a;
import me.cheshmak.android.sdk.core.network.C0555a.C0461b;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;

/* renamed from: me.cheshmak.android.sdk.advertise.b */
public class C0464b {
    /* renamed from: b */
    private static C0464b f418b;
    /* renamed from: a */
    private String f419a = "Adv/NetworkManager";
    /* renamed from: c */
    private final Object f420c = new Object();

    private C0464b() {
    }

    /* renamed from: a */
    public static C0464b m625a() {
        if (f418b == null) {
            synchronized (C0464b.class) {
                if (f418b == null) {
                    f418b = new C0464b();
                }
            }
        }
        return f418b;
    }

    /* renamed from: a */
    public void m626a(String str, String str2, C0457j c0457j) {
        final WeakReference weakReference = new WeakReference(c0457j);
        HashMap hashMap = new HashMap();
        hashMap.put("time", String.valueOf(System.currentTimeMillis()));
        C0555a c0555a = new C0555a(str, 0, str2, hashMap);
        c0555a.m1087a(new C0461b(this) {
            /* renamed from: b */
            final /* synthetic */ C0464b f417b;

            /* renamed from: a */
            public void mo4390a(int i, String str) {
                synchronized (this.f417b.f420c) {
                    C0457j c0457j = (C0457j) weakReference.get();
                    if (c0457j != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("responseCode", i);
                        bundle.putString("response", str);
                        c0457j.mo4383a(bundle);
                        this.f417b.f420c.notify();
                    }
                }
            }

            /* renamed from: a */
            public void mo4391a(Exception exception) {
            }

            /* renamed from: a */
            public void mo4392a(String str) {
                synchronized (this.f417b.f420c) {
                    C0457j c0457j = (C0457j) weakReference.get();
                    if (c0457j != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("responseCode", Callback.DEFAULT_DRAG_ANIMATION_DURATION);
                        bundle.putString("response", str);
                        c0457j.mo4383a(bundle);
                        this.f417b.f420c.notify();
                    }
                }
            }
        });
        try {
            synchronized (this.f420c) {
                c0555a.m1086a();
                this.f420c.wait();
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* renamed from: a */
    public void m627a(String str, C0454e c0454e) {
        final WeakReference weakReference = new WeakReference(c0454e);
        String uri = Uri.parse(C0467f.f422a + C0467f.f424c).toString();
        HashMap hashMap = new HashMap();
        hashMap.put("time", String.valueOf(System.currentTimeMillis()));
        C0555a c0555a = new C0555a(uri, 0, str, hashMap);
        c0555a.m1087a(new C0461b(this) {
            /* renamed from: b */
            final /* synthetic */ C0464b f415b;

            /* renamed from: a */
            public void mo4390a(int i, String str) {
                C0454e c0454e = (C0454e) weakReference.get();
                if (c0454e != null) {
                    c0454e.onReadyResponse(str);
                }
            }

            /* renamed from: a */
            public void mo4391a(Exception exception) {
                C0454e c0454e = (C0454e) weakReference.get();
                if (c0454e != null) {
                    c0454e.onFailureResponse(exception);
                }
            }

            /* renamed from: a */
            public void mo4392a(String str) {
                C0454e c0454e = (C0454e) weakReference.get();
                if (c0454e != null) {
                    c0454e.onReadyResponse(str);
                }
            }
        });
        c0555a.m1086a();
    }
}
