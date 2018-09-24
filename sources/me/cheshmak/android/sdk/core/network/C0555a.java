package me.cheshmak.android.sdk.core.network;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0517b;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0549p;

/* renamed from: me.cheshmak.android.sdk.core.network.a */
public class C0555a {
    /* renamed from: a */
    public static int f679a = 7000;
    /* renamed from: b */
    private byte[] f680b;
    /* renamed from: c */
    private String f681c;
    /* renamed from: d */
    private String f682d;
    /* renamed from: e */
    private HashMap<String, String> f683e;
    /* renamed from: f */
    private int f684f;
    /* renamed from: g */
    private C0461b f685g;

    /* renamed from: me.cheshmak.android.sdk.core.network.a$b */
    public interface C0461b {
        /* renamed from: a */
        void mo4390a(int i, String str);

        /* renamed from: a */
        void mo4391a(Exception exception);

        /* renamed from: a */
        void mo4392a(String str);
    }

    /* renamed from: me.cheshmak.android.sdk.core.network.a$a */
    private static class C0554a extends AsyncTask<Void, Void, Void> {
        /* renamed from: g */
        private static C0549p f670g = new C0549p(1);
        /* renamed from: h */
        private static AtomicBoolean f671h = new AtomicBoolean(true);
        /* renamed from: i */
        private static final ReentrantLock f672i = new ReentrantLock(true);
        /* renamed from: a */
        private byte[] f673a;
        /* renamed from: b */
        private String f674b;
        /* renamed from: c */
        private String f675c;
        /* renamed from: d */
        private Map<String, String> f676d;
        /* renamed from: e */
        private int f677e;
        /* renamed from: f */
        private C0461b f678f;

        public C0554a(byte[] bArr, String str, String str2, Map<String, String> map, int i, C0461b c0461b) {
            this.f673a = bArr;
            this.f674b = str;
            this.f675c = str2;
            this.f676d = map;
            this.f677e = i;
            this.f678f = c0461b;
        }

        /* renamed from: a */
        private HashMap<String, String> m1083a() {
            HashMap<String, String> hashMap = new HashMap();
            hashMap.put("pakageName", Cheshmak.packageName);
            return hashMap;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: a */
        private boolean m1084a(java.lang.String r9, byte[] r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, int r13, me.cheshmak.android.sdk.core.network.C0555a.C0461b r14) {
            /*
            r8 = this;
            r3 = 0;
            r5 = 0;
            r4 = 1;
            r0 = new java.net.URL;	 Catch:{ Exception -> 0x01b9, all -> 0x01a5 }
            r0.<init>(r9);	 Catch:{ Exception -> 0x01b9, all -> 0x01a5 }
            r0 = r0.openConnection();	 Catch:{ Exception -> 0x01b9, all -> 0x01a5 }
            r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x01b9, all -> 0x01a5 }
            if (r13 != r4) goto L_0x00fb;
        L_0x0010:
            r1 = "GET";
            r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
        L_0x0016:
            r1 = "Content-Type";
            r2 = "application/json;charset=UTF-8";
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "Accept";
            r2 = "application/json";
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "sdkVersion";
            r2 = "2.0.22";
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "apiVersion";
            r2 = "2";
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "appVersion";
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2.<init>();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r6 = "";
            r2 = r2.append(r6);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r6 = me.cheshmak.android.sdk.core.Cheshmak.appVersion;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.append(r6);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "googlePlayService";
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2.<init>();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r6 = me.cheshmak.android.sdk.core.Cheshmak.googlePlayServiceVersion;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.append(r6);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r6 = "";
            r2 = r2.append(r6);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "sdkVersionNumber";
            r2 = "52";
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "deviceId";
            r2 = me.cheshmak.android.sdk.core.p019a.C0477a.m656a();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.m684c();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = "appKey";
            r2 = me.cheshmak.android.sdk.core.p019a.C0477a.m656a();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.m677b();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = me.cheshmak.android.sdk.core.network.C0555a.f679a;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = me.cheshmak.android.sdk.core.p019a.C0477a.m656a();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = r1.m731z();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            if (r1 == 0) goto L_0x00b4;
        L_0x00a6:
            r1 = "org";
            r2 = me.cheshmak.android.sdk.core.p019a.C0477a.m656a();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r2.m731z();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
        L_0x00b4:
            if (r12 == 0) goto L_0x0117;
        L_0x00b6:
            r1 = r12.isEmpty();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            if (r1 != 0) goto L_0x0117;
        L_0x00bc:
            r1 = r12.entrySet();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r6 = r1.iterator();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
        L_0x00c4:
            r1 = r6.hasNext();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            if (r1 == 0) goto L_0x0117;
        L_0x00ca:
            r1 = r6.next();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = (java.util.Map.Entry) r1;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r1.getKey();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = r1.getValue();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r0.setRequestProperty(r2, r1);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            goto L_0x00c4;
        L_0x00e0:
            r2 = move-exception;
            r1 = r3;
            r4 = r5;
            r6 = r5;
            r7 = r0;
        L_0x00e5:
            if (r14 == 0) goto L_0x00ea;
        L_0x00e7:
            r14.mo4391a(r2);	 Catch:{ all -> 0x01b1 }
        L_0x00ea:
            if (r6 == 0) goto L_0x00ef;
        L_0x00ec:
            r6.close();	 Catch:{ Exception -> 0x01b6 }
        L_0x00ef:
            if (r4 == 0) goto L_0x00f4;
        L_0x00f1:
            r4.close();	 Catch:{ Exception -> 0x01b6 }
        L_0x00f4:
            if (r7 == 0) goto L_0x01ce;
        L_0x00f6:
            r7.disconnect();
            r0 = r1;
        L_0x00fa:
            return r0;
        L_0x00fb:
            r1 = "POST";
            r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            goto L_0x0016;
        L_0x0103:
            r1 = move-exception;
            r3 = r5;
            r6 = r5;
            r7 = r0;
        L_0x0107:
            if (r6 == 0) goto L_0x010c;
        L_0x0109:
            r6.close();	 Catch:{ Exception -> 0x01a2 }
        L_0x010c:
            if (r3 == 0) goto L_0x0111;
        L_0x010e:
            r3.close();	 Catch:{ Exception -> 0x01a2 }
        L_0x0111:
            if (r7 == 0) goto L_0x0116;
        L_0x0113:
            r7.disconnect();
        L_0x0116:
            throw r1;
        L_0x0117:
            if (r13 != r4) goto L_0x014c;
        L_0x0119:
            r0.connect();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
        L_0x011c:
            r7 = r0.getResponseCode();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r7 != r1) goto L_0x0172;
        L_0x0124:
            r1 = r4;
        L_0x0125:
            if (r1 == 0) goto L_0x0174;
        L_0x0127:
            r2 = r0.getInputStream();	 Catch:{ Exception -> 0x01c1, all -> 0x0103 }
        L_0x012b:
            r6 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x01c1, all -> 0x0103 }
            r6.<init>(r2);	 Catch:{ Exception -> 0x01c1, all -> 0x0103 }
            r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x01c7, all -> 0x01ac }
            r3.<init>(r6);	 Catch:{ Exception -> 0x01c7, all -> 0x01ac }
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            r4 = r3.readLine();	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            r2.<init>(r4);	 Catch:{ Exception -> 0x0148, all -> 0x019e }
        L_0x013e:
            r4 = r3.readLine();	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            if (r4 == 0) goto L_0x0179;
        L_0x0144:
            r2.append(r4);	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            goto L_0x013e;
        L_0x0148:
            r2 = move-exception;
            r4 = r3;
            r7 = r0;
            goto L_0x00e5;
        L_0x014c:
            if (r10 != 0) goto L_0x0150;
        L_0x014e:
            if (r11 == 0) goto L_0x011c;
        L_0x0150:
            r1 = 1;
            r0.setDoOutput(r1);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = 1;
            r0.setDoInput(r1);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1.<init>(r2);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            if (r10 == 0) goto L_0x0166;
        L_0x0163:
            r1.write(r10);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
        L_0x0166:
            if (r11 == 0) goto L_0x016b;
        L_0x0168:
            r1.writeBytes(r11);	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
        L_0x016b:
            r1.flush();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            r1.close();	 Catch:{ Exception -> 0x00e0, all -> 0x0103 }
            goto L_0x011c;
        L_0x0172:
            r1 = r3;
            goto L_0x0125;
        L_0x0174:
            r2 = r0.getErrorStream();	 Catch:{ Exception -> 0x01c1, all -> 0x0103 }
            goto L_0x012b;
        L_0x0179:
            if (r14 == 0) goto L_0x0184;
        L_0x017b:
            if (r1 == 0) goto L_0x0196;
        L_0x017d:
            r2 = r2.toString();	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            r14.mo4392a(r2);	 Catch:{ Exception -> 0x0148, all -> 0x019e }
        L_0x0184:
            if (r6 == 0) goto L_0x0189;
        L_0x0186:
            r6.close();	 Catch:{ Exception -> 0x01cc }
        L_0x0189:
            if (r3 == 0) goto L_0x018e;
        L_0x018b:
            r3.close();	 Catch:{ Exception -> 0x01cc }
        L_0x018e:
            if (r0 == 0) goto L_0x01ce;
        L_0x0190:
            r0.disconnect();
            r0 = r1;
            goto L_0x00fa;
        L_0x0196:
            r2 = r2.toString();	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            r14.mo4390a(r7, r2);	 Catch:{ Exception -> 0x0148, all -> 0x019e }
            goto L_0x0184;
        L_0x019e:
            r1 = move-exception;
            r7 = r0;
            goto L_0x0107;
        L_0x01a2:
            r0 = move-exception;
            goto L_0x0111;
        L_0x01a5:
            r0 = move-exception;
            r1 = r0;
            r3 = r5;
            r6 = r5;
            r7 = r5;
            goto L_0x0107;
        L_0x01ac:
            r1 = move-exception;
            r3 = r5;
            r7 = r0;
            goto L_0x0107;
        L_0x01b1:
            r0 = move-exception;
            r1 = r0;
            r3 = r4;
            goto L_0x0107;
        L_0x01b6:
            r0 = move-exception;
            goto L_0x00f4;
        L_0x01b9:
            r0 = move-exception;
            r2 = r0;
            r1 = r3;
            r4 = r5;
            r6 = r5;
            r7 = r5;
            goto L_0x00e5;
        L_0x01c1:
            r2 = move-exception;
            r4 = r5;
            r6 = r5;
            r7 = r0;
            goto L_0x00e5;
        L_0x01c7:
            r2 = move-exception;
            r4 = r5;
            r7 = r0;
            goto L_0x00e5;
        L_0x01cc:
            r2 = move-exception;
            goto L_0x018e;
        L_0x01ce:
            r0 = r1;
            goto L_0x00fa;
            */
            throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.network.a.a.a(java.lang.String, byte[], java.lang.String, java.util.Map, int, me.cheshmak.android.sdk.core.network.a$b):boolean");
        }

        /* renamed from: a */
        protected Void m1085a(Void... voidArr) {
            f672i.lock();
            try {
                if (!f671h.get()) {
                    try {
                        f670g.m1057a();
                    } catch (InterruptedException e) {
                    }
                }
                boolean f = C0477a.m656a().m700f();
                boolean equals = TextUtils.equals("https://sdk.cheshmak.me/initiate", this.f675c);
                if (!f) {
                    f671h.set(false);
                    f670g.m1058b();
                }
                f672i.unlock();
                if (!(f || equals)) {
                    try {
                        f = m1084a("https://sdk.cheshmak.me/initiate", null, C0520e.m912e(), m1083a(), 0, new C0562g());
                    } catch (Throwable th) {
                        f670g.m1059c();
                        f671h.set(f);
                    }
                }
                boolean z = (f || equals) ? m1084a(this.f675c, this.f673a, this.f674b, this.f676d, this.f677e, this.f678f) || f : f;
                f670g.m1059c();
                f671h.set(z);
                return null;
            } catch (Throwable th2) {
                f672i.unlock();
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1085a((Void[]) objArr);
        }
    }

    public C0555a(String str, int i, String str2, HashMap<String, String> hashMap) {
        this.f682d = str;
        this.f681c = str2;
        this.f683e = hashMap;
        this.f684f = i;
    }

    public C0555a(String str, int i, byte[] bArr, HashMap<String, String> hashMap) {
        this.f682d = str;
        this.f680b = bArr;
        this.f683e = hashMap;
        this.f684f = i;
    }

    /* renamed from: a */
    public void m1086a() {
        C0517b.m893a(new C0554a(this.f680b, this.f681c, this.f682d, this.f683e, this.f684f, this.f685g), new Void[0]);
    }

    /* renamed from: a */
    public void m1087a(C0461b c0461b) {
        this.f685g = c0461b;
    }
}
