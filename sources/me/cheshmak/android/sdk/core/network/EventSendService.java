package me.cheshmak.android.sdk.core.network;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import java.lang.ref.WeakReference;
import me.cheshmak.android.sdk.advertise.C0466d;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p028j.C0513a;

public class EventSendService extends Service {
    /* renamed from: a */
    private Context f669a;

    /* renamed from: me.cheshmak.android.sdk.core.network.EventSendService$a */
    private static class C0553a extends AsyncTask<Void, Void, Boolean> {
        /* renamed from: a */
        WeakReference<Context> f668a;

        public C0553a(Context context) {
            this.f668a = new WeakReference(context);
        }

        /* renamed from: a */
        protected Boolean m1081a(Void... voidArr) {
            Context context = (Context) this.f668a.get();
            if (context == null) {
                return Boolean.valueOf(false);
            }
            try {
                if (!C0480a.m738a()) {
                    C0480a.m736a(context);
                }
                return C0516a.m879a() - C0477a.m656a().m701g() < C0477a.m656a().m708j() / 1000 ? Boolean.valueOf(false) : Boolean.valueOf(true);
            } catch (Throwable th) {
            }
            return Boolean.valueOf(false);
        }

        /* renamed from: a */
        protected void m1082a(Boolean bool) {
            Context context = (Context) this.f668a.get();
            if (context != null) {
                try {
                    if (!bool.booleanValue()) {
                        return;
                    }
                    if (C0477a.m656a().m700f()) {
                        if (!C0480a.m738a()) {
                            C0480a.m736a(context);
                        }
                        C0477a.m656a().m702g(C0477a.m656a().m710k());
                        new C0563h(context).execute(new Void[0]);
                    } else if (!C0477a.m656a().m724s()) {
                    } else {
                        if (C0520e.m906b(context)) {
                            C0477a.m656a().m681b(C0520e.m901a(context));
                            new C0560e(context).m1101b();
                        } else if (!C0477a.m656a().m663F()) {
                            new C0513a(context).m867a();
                        } else if (C0516a.m879a() - C0477a.m656a().m664G() > 2) {
                            if (C0477a.m656a().m684c() == null) {
                                C0477a.m656a().m681b(C0520e.m901a(context));
                            }
                            new C0560e(context).m1101b();
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1081a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m1082a((Boolean) obj);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                if (intent.getAction() != null) {
                    if ("me.cheshmak.android.sdk.advertise.action.AdvertiseID".equals(intent.getAction())) {
                        new C0466d().execute(new Context[]{getApplicationContext()});
                    }
                    return 2;
                }
            } catch (Throwable th) {
            }
        }
        this.f669a = getApplicationContext();
        new C0553a(this).execute(new Void[0]);
        return 2;
    }
}
