package me.cheshmak.android.sdk.core.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.network.C0555a.C0461b;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p019a.C0478b;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.push.p030a.C0576b;

/* renamed from: me.cheshmak.android.sdk.core.network.i */
public class C0571i {
    /* renamed from: e */
    private static C0571i f710e;
    /* renamed from: f */
    private static String f711f = C0573k.f718b;
    /* renamed from: a */
    private final String f712a = "cldt";
    /* renamed from: b */
    private final String f713b = "Content-Encoding";
    /* renamed from: c */
    private final String f714c = "eflag";
    /* renamed from: d */
    private String f715d = "NetworkManager";

    private C0571i() {
    }

    /* renamed from: a */
    public static C0571i m1123a() {
        if (f710e == null) {
            synchronized (C0571i.class) {
                if (f710e == null) {
                    f710e = new C0571i();
                }
            }
        }
        return f710e;
    }

    /* renamed from: a */
    public void m1124a(int i, byte[] bArr) {
        String str = "";
        switch (i) {
            case 0:
                str = C0573k.f720d + "/b";
                break;
            case 1:
                str = C0573k.f720d + "/a";
                break;
            case 2:
                str = C0573k.f720d + "/c";
                break;
            case 3:
                str = C0573k.f720d + "/e";
                break;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("cldt", C0516a.m879a() + "");
        hashMap.put("Content-Encoding", "gzip");
        hashMap.put("eflag", "yes");
        new C0555a(str, 0, bArr, hashMap).m1086a();
    }

    /* renamed from: a */
    public void m1125a(Context context) {
        final WeakReference weakReference = new WeakReference(context);
        C0555a c0555a = new C0555a("https://sdk.cheshmak.me/availability", 1, "", null);
        c0555a.m1087a(new C0461b(this) {
            /* renamed from: b */
            final /* synthetic */ C0571i f698b;

            /* renamed from: a */
            public void mo4390a(int i, String str) {
                try {
                    C0559d c0559d = new C0559d(str);
                    if (c0559d.m1098d() != null && C0516a.m890a(c0559d.m1098d())) {
                        C0516a.m888a((Context) weakReference.get(), c0559d.m1098d());
                    }
                } catch (Throwable th) {
                }
            }

            /* renamed from: a */
            public void mo4391a(Exception exception) {
            }

            /* renamed from: a */
            public void mo4392a(String str) {
                try {
                    C0559d c0559d = new C0559d(str);
                    if (c0559d.m1098d() != null && C0516a.m890a(c0559d.m1098d())) {
                        C0516a.m888a((Context) weakReference.get(), c0559d.m1098d());
                    }
                    C0480a.m740b(Long.valueOf(C0477a.m656a().m706i()));
                    try {
                        if (C0477a.m656a().m720p() != c0559d.m1098d()[4] || C0477a.m656a().m718o() == null) {
                            C0552s.m1071a((Context) weakReference.get(), Long.valueOf(c0559d.m1098d()[4]));
                        }
                    } catch (Exception e) {
                    }
                    if (c0559d.mo4408a() != null) {
                        C0516a.m887a((Context) weakReference.get(), c0559d.mo4408a());
                    }
                    C0477a.m656a().m670a(C0516a.m879a());
                    C0516a.m880a(c0559d.m1098d()[7], c0559d.m1098d()[7]);
                } catch (Throwable th) {
                }
            }
        });
        c0555a.m1086a();
    }

    /* renamed from: a */
    public void m1126a(Context context, String str) {
        final WeakReference weakReference = new WeakReference(context);
        C0555a c0555a = new C0555a(C0573k.f717a, 0, str, null);
        c0555a.m1087a(new C0461b(this) {
            /* renamed from: b */
            final /* synthetic */ C0571i f701b;

            /* renamed from: me.cheshmak.android.sdk.core.network.i$2$1 */
            class C05651 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C05662 f699a;

                C05651(C05662 c05662) {
                    this.f699a = c05662;
                }

                public void run() {
                    C0477a.m656a().m716n();
                    new C0563h((Context) weakReference.get()).execute(new Void[0]);
                }
            }

            /* renamed from: a */
            public void mo4390a(int i, String str) {
                WeakHashMap weakHashMap;
                if (str != null) {
                    try {
                        C0559d c0559d = new C0559d(str);
                        if (c0559d.m1098d() != null && C0516a.m890a(c0559d.m1098d())) {
                            C0516a.m888a((Context) weakReference.get(), c0559d.m1098d());
                        }
                        if (c0559d.m1092c() != null && c0559d.m1092c().size() > 0) {
                            if (C0478b.f452d.equals(c0559d.m1092c().get(0))) {
                                C0477a.m656a().m674a(false);
                            } else if (C0478b.f450b.equals(c0559d.m1092c().get(0)) || C0478b.f449a.equals(c0559d.m1092c().get(0))) {
                                C0480a.m741b(C0477a.m656a().m722q());
                                C0477a.m656a().m672a(new HashSet());
                            }
                        }
                        try {
                            if ((C0477a.m656a().m720p() == 0 || C0477a.m656a().m720p() != c0559d.m1098d()[4] || C0477a.m656a().m718o() == null) && C0552s.m1072a((Context) weakReference.get())) {
                                C0552s.m1071a((Context) weakReference.get(), Long.valueOf(c0559d.m1098d()[4]));
                            }
                            if (c0559d.m1092c() != null && c0559d.m1092c().size() > 0 && C0478b.f452d.equals(c0559d.m1092c().get(0))) {
                                C0477a.m656a().m674a(false);
                            }
                        } catch (Throwable e) {
                            weakHashMap = new WeakHashMap();
                            weakHashMap.put("CLASS", "EventRequest");
                            weakHashMap.put("METHOD", "onErrorResponse:innerTry");
                            weakHashMap.put("MESSAGE", "event is broken");
                            C0545m.m1043a(1, weakHashMap, e);
                        }
                    } catch (Throwable e2) {
                        weakHashMap = new WeakHashMap();
                        weakHashMap.put("SECTION", "NETWORK");
                        weakHashMap.put("CLASS", "EventRequest");
                        weakHashMap.put("METHOD", "onErrorResponse");
                        weakHashMap.put("MESSAGE", "event is broken");
                        C0545m.m1043a(1, weakHashMap, e2);
                    }
                }
            }

            /* renamed from: a */
            public void mo4391a(Exception exception) {
            }

            /* renamed from: a */
            public void mo4392a(String str) {
                try {
                    C0559d c0559d = new C0559d(str);
                    if (c0559d.m1098d() != null && C0516a.m890a(c0559d.m1098d())) {
                        C0516a.m888a((Context) weakReference.get(), c0559d.m1098d());
                    }
                    C0480a.m740b(Long.valueOf(C0477a.m656a().m706i()));
                    C0477a.m656a().m672a(new HashSet());
                    long d = C0480a.m744d();
                    try {
                        if (C0477a.m656a().m720p() != c0559d.m1098d()[4] || C0477a.m656a().m718o() == null) {
                            C0552s.m1071a((Context) weakReference.get(), Long.valueOf(c0559d.m1098d()[4]));
                        }
                    } catch (Exception e) {
                    }
                    if (d > C0477a.m656a().m706i() && C0477a.m656a().m714m() > 0) {
                        new Handler(Looper.getMainLooper()).postDelayed(new C05651(this), C0477a.m656a().m712l());
                    }
                    if (c0559d.mo4408a() != null) {
                        C0516a.m887a((Context) weakReference.get(), c0559d.mo4408a());
                    }
                    C0477a.m656a().m670a(C0516a.m879a());
                    C0516a.m880a(c0559d.m1098d()[7], c0559d.m1098d()[6]);
                } catch (Throwable e2) {
                    WeakHashMap weakHashMap = new WeakHashMap();
                    weakHashMap.put("SECTION", "NETWORK");
                    weakHashMap.put("CLASS", "EventRequest");
                    weakHashMap.put("METHOD", "onResponse");
                    weakHashMap.put("MESSAGE", "event is broken");
                    C0545m.m1043a(1, weakHashMap, e2);
                }
            }
        });
        c0555a.m1086a();
    }

    /* renamed from: a */
    public void m1127a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("pakageName", Cheshmak.packageName);
        C0555a c0555a = new C0555a("https://sdk.cheshmak.me/initiate", 0, str, hashMap);
        c0555a.m1087a(new C0562g());
        c0555a.m1086a();
    }

    /* renamed from: a */
    public void m1128a(String str, String str2) {
        new C0555a(f711f + "/opened/gcm/" + str, 0, str2, null).m1086a();
    }

    /* renamed from: a */
    public void m1129a(String str, final C0576b c0576b) {
        C0555a c0555a = new C0555a(C0573k.f724h + "/" + str, 1, "", null);
        c0555a.m1087a(new C0461b(this) {
            /* renamed from: b */
            final /* synthetic */ C0571i f703b;

            /* renamed from: a */
            public void mo4390a(int i, String str) {
            }

            /* renamed from: a */
            public void mo4391a(Exception exception) {
                c0576b.mo4411a(exception);
            }

            /* renamed from: a */
            public void mo4392a(String str) {
                c0576b.mo4412a(str);
            }
        });
        c0555a.m1086a();
    }

    /* renamed from: a */
    public void m1130a(final SinglePushResponseListener singlePushResponseListener) {
        C0555a c0555a = new C0555a(C0573k.f725i, 1, "", null);
        c0555a.m1087a(new C0461b(this) {
            /* renamed from: b */
            final /* synthetic */ C0571i f709b;

            /* renamed from: a */
            public void mo4390a(int i, String str) {
            }

            /* renamed from: a */
            public void mo4391a(final Exception exception) {
                if (singlePushResponseListener != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        /* renamed from: b */
                        final /* synthetic */ C05704 f707b;

                        public void run() {
                            singlePushResponseListener.onError(exception);
                        }
                    });
                }
            }

            /* renamed from: a */
            public void mo4392a(String str) {
                try {
                    final String d = new C0572j(str).m1134d();
                    C0477a.m656a().m703g(d);
                    if (singlePushResponseListener != null) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            /* renamed from: b */
                            final /* synthetic */ C05704 f705b;

                            public void run() {
                                singlePushResponseListener.onUniqueIdReceived(d);
                            }
                        });
                        return;
                    }
                    return;
                } catch (Throwable th) {
                }
                if (singlePushResponseListener != null) {
                    singlePushResponseListener.onError(th);
                }
            }
        });
        c0555a.m1086a();
    }

    /* renamed from: b */
    public void m1131b(String str) {
        new C0555a(C0573k.f717a, 0, str, null).m1086a();
    }

    /* renamed from: b */
    public void m1132b(String str, String str2) {
        new C0555a(f711f + "/delivered/gcm/" + str, 0, str2, null).m1086a();
    }
}
